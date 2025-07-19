"""
Endpoints para gerenciamento de pacientes
"""

from typing import List, Optional
from fastapi import APIRouter, Depends, HTTPException, status, Query
from sqlalchemy.orm import Session

from app.core.database import get_db
from app.repositories.paciente import paciente_repository
from app.schemas.paciente import PacienteCreate, PacienteUpdate, PacienteResponse
from app.api.dependencies import get_current_user, require_atendente_or_admin
from app.models.funcionario import Funcionario

router = APIRouter()


@router.get("/", response_model=List[PacienteResponse])
def listar_pacientes(
    skip: int = Query(0, ge=0, description="Número de registros para pular"),
    limit: int = Query(100, ge=1, le=1000, description="Número máximo de registros"),
    search: Optional[str] = Query(None, description="Buscar por nome ou CPF"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Listar pacientes com paginação e busca.
    Disponível para todos os funcionários autenticados.
    """
    pacientes = paciente_repository.get_all(db, skip=skip, limit=limit, search=search)
    return pacientes


@router.get("/{paciente_id}", response_model=PacienteResponse)
def buscar_paciente(
    paciente_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar paciente por ID.
    Disponível para todos os funcionários autenticados.
    """
    paciente = paciente_repository.get_by_id(db, paciente_id)
    
    if not paciente:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Paciente não encontrado"
        )
    
    return paciente


@router.post("/", response_model=PacienteResponse, status_code=status.HTTP_201_CREATED)
def criar_paciente(
    paciente_data: PacienteCreate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_atendente_or_admin)
):
    """
    Criar novo paciente.
    Apenas atendentes e administradores podem criar pacientes.
    """
    # Verificar se CPF já existe
    existing_paciente = paciente_repository.get_by_cpf(db, paciente_data.cpf)
    if existing_paciente:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="Já existe um paciente com este CPF"
        )
    
    # Verificar se RG já existe
    existing_rg = paciente_repository.get_by_rg(db, paciente_data.rg)
    if existing_rg:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="Já existe um paciente com este RG"
        )
    
    paciente = paciente_repository.create(db, paciente_data)
    return paciente


@router.put("/{paciente_id}", response_model=PacienteResponse)
def atualizar_paciente(
    paciente_id: int,
    paciente_data: PacienteUpdate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_atendente_or_admin)
):
    """
    Atualizar paciente.
    Apenas atendentes e administradores podem atualizar pacientes.
    """
    paciente = paciente_repository.get_by_id(db, paciente_id)
    
    if not paciente:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Paciente não encontrado"
        )
    
    # Verificar CPF único se estiver sendo alterado
    if paciente_data.cpf and paciente_data.cpf != paciente.cpf:
        existing_cpf = paciente_repository.get_by_cpf(db, paciente_data.cpf)
        if existing_cpf:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail="Já existe um paciente com este CPF"
            )
    
    # Verificar RG único se estiver sendo alterado
    if paciente_data.rg and paciente_data.rg != paciente.rg:
        existing_rg = paciente_repository.get_by_rg(db, paciente_data.rg)
        if existing_rg:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail="Já existe um paciente com este RG"
            )
    
    paciente_atualizado = paciente_repository.update(db, paciente_id, paciente_data)
    return paciente_atualizado


@router.delete("/{paciente_id}", status_code=status.HTTP_204_NO_CONTENT)
def deletar_paciente(
    paciente_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_atendente_or_admin)
):
    """
    Deletar paciente.
    Apenas atendentes e administradores podem deletar pacientes.
    """
    paciente = paciente_repository.get_by_id(db, paciente_id)
    
    if not paciente:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Paciente não encontrado"
        )
    
    success = paciente_repository.delete(db, paciente_id)
    
    if not success:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail="Erro ao deletar paciente"
        )


@router.get("/search/cpf/{cpf}", response_model=List[PacienteResponse])
def buscar_por_cpf(
    cpf: str,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar pacientes por CPF.
    Disponível para todos os funcionários autenticados.
    """
    pacientes = paciente_repository.search_by_cpf(db, cpf)
    return pacientes


@router.get("/search/nome", response_model=List[PacienteResponse])
def buscar_por_nome(
    nome: str = Query(..., description="Nome do paciente para busca"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar pacientes por nome.
    Disponível para todos os funcionários autenticados.
    """
    pacientes = paciente_repository.search_by_name(db, nome)
    return pacientes 