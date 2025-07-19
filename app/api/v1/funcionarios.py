"""
Endpoints para funcionários
"""

from typing import List, Optional
from fastapi import APIRouter, Depends, HTTPException, status, Query
from sqlalchemy.orm import Session

from app.core.database import get_db
from app.repositories.funcionario import funcionario_repository
from app.schemas.funcionario import (
    FuncionarioCreate, FuncionarioUpdate, FuncionarioResponse, 
    FuncionarioListResponse, MedicoCreate, MedicoResponse
)
from app.api.dependencies import get_current_user, require_admin, require_roles
from app.models.funcionario import Funcionario

router = APIRouter()


@router.get("/", response_model=FuncionarioListResponse)
def listar_funcionarios(
    skip: int = Query(0, ge=0, description="Número de registros para pular"),
    limit: int = Query(100, ge=1, le=1000, description="Número máximo de registros"),
    tipo: Optional[str] = Query(None, description="Filtrar por tipo de funcionário"),
    search: Optional[str] = Query(None, description="Buscar por nome, usuário ou email"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_roles(["administrador", "atendente"]))
):
    """
    Listar funcionários com filtros e paginação.
    Apenas administradores e atendentes podem listar funcionários.
    """
    funcionarios, total = funcionario_repository.get_all(
        db=db, 
        skip=skip, 
        limit=limit, 
        tipo=tipo, 
        search=search
    )
    
    return FuncionarioListResponse(
        funcionarios=funcionarios,
        total=total,
        page=skip // limit + 1,
        size=limit
    )


@router.get("/medicos", response_model=List[MedicoResponse])
def listar_medicos(
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Listar todos os médicos.
    Disponível para todos os funcionários autenticados.
    """
    medicos = funcionario_repository.get_medicos(db)
    return medicos


@router.get("/{funcionario_id}", response_model=FuncionarioResponse)
def buscar_funcionario(
    funcionario_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar funcionário por ID.
    Usuários podem ver seus próprios dados ou administradores podem ver todos.
    """
    funcionario = funcionario_repository.get_by_id(db, funcionario_id)
    
    if not funcionario:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Funcionário não encontrado"
        )
    
    # Verificar permissões
    if current_user.id != funcionario_id and current_user.tipo != "administrador":
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Você só pode visualizar seus próprios dados."
        )
    
    return funcionario


@router.post("/", response_model=FuncionarioResponse, status_code=status.HTTP_201_CREATED)
def criar_funcionario(
    funcionario_data: FuncionarioCreate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_admin)
):
    """
    Criar novo funcionário.
    Apenas administradores podem criar funcionários.
    """
    # Verificar se usuário já existe
    existing_user = funcionario_repository.get_by_usuario(db, funcionario_data.usuario)
    if existing_user:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="Nome de usuário já existe"
        )
    
    # Verificar se email já existe
    existing_email = funcionario_repository.get_by_email(db, funcionario_data.email)
    if existing_email:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="Email já está em uso"
        )
    
    # Verificações específicas por tipo
    if funcionario_data.tipo == "medico" and funcionario_data.crm:
        existing_crm = funcionario_repository.get_by_crm(db, funcionario_data.crm)
        if existing_crm:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail="CRM já está em uso"
            )
    
    if funcionario_data.tipo == "enfermeiro" and funcionario_data.coren:
        existing_coren = funcionario_repository.get_by_coren(db, funcionario_data.coren)
        if existing_coren:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail="COREN já está em uso"
            )
    
    if funcionario_data.tipo == "farmaceutico" and funcionario_data.crf:
        existing_crf = funcionario_repository.get_by_crf(db, funcionario_data.crf)
        if existing_crf:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail="CRF já está em uso"
            )
    
    funcionario = funcionario_repository.create(db, funcionario_data)
    return funcionario


@router.put("/{funcionario_id}", response_model=FuncionarioResponse)
def atualizar_funcionario(
    funcionario_id: int,
    funcionario_data: FuncionarioUpdate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Atualizar funcionário.
    Usuários podem atualizar seus próprios dados ou administradores podem atualizar todos.
    """
    funcionario = funcionario_repository.get_by_id(db, funcionario_id)
    
    if not funcionario:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Funcionário não encontrado"
        )
    
    # Verificar permissões
    is_self_update = current_user.id == funcionario_id
    is_admin = current_user.tipo == "administrador"
    
    if not (is_self_update or is_admin):
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Você só pode atualizar seus próprios dados."
        )
    
    # Usuários normais não podem alterar certos campos
    if is_self_update and not is_admin:
        # Remover campos que apenas admin pode alterar
        restricted_fields = ['crm', 'coren', 'crf', 'especialidade']
        for field in restricted_fields:
            if hasattr(funcionario_data, field):
                setattr(funcionario_data, field, None)
    
    # Verificar email único
    if funcionario_data.email:
        existing_email = funcionario_repository.get_by_email(db, funcionario_data.email)
        if existing_email and existing_email.id != funcionario_id:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail="Email já está em uso"
            )
    
    funcionario_atualizado = funcionario_repository.update(db, funcionario_id, funcionario_data)
    return funcionario_atualizado


@router.delete("/{funcionario_id}", status_code=status.HTTP_204_NO_CONTENT)
def deletar_funcionario(
    funcionario_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_admin)
):
    """
    Deletar funcionário.
    Apenas administradores podem deletar funcionários.
    """
    # Verificar se funcionário existe
    funcionario = funcionario_repository.get_by_id(db, funcionario_id)
    
    if not funcionario:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Funcionário não encontrado"
        )
    
    # Não permitir que admin delete a si mesmo
    if funcionario_id == current_user.id:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="Você não pode deletar sua própria conta"
        )
    
    success = funcionario_repository.delete(db, funcionario_id)
    
    if not success:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail="Erro ao deletar funcionário"
        )


@router.get("/search/usuario/{usuario}", response_model=FuncionarioResponse)
def buscar_por_usuario(
    usuario: str,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_roles(["administrador", "atendente"]))
):
    """
    Buscar funcionário por nome de usuário.
    Apenas administradores e atendentes podem buscar por usuário.
    """
    funcionario = funcionario_repository.get_by_usuario(db, usuario)
    
    if not funcionario:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Funcionário não encontrado"
        )
    
    return funcionario 