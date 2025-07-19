"""
Endpoints da API para Exames
"""

from typing import List, Optional
from datetime import date
from fastapi import APIRouter, Depends, HTTPException, Query, status
from sqlalchemy.orm import Session

from app.core.database import get_db
from app.models.funcionario import Funcionario
from app.models.exame import StatusExame, TipoExame
from app.schemas.exame import (
    ExameCreate, ExameUpdate, ExameResponse, ExameListResponse,
    ExameAgendamentoCreate, ExameResultadoUpdate
)
from app.repositories.exame import exame_repository
from app.api.dependencies import (
    get_current_user, require_admin, require_admin_or_atendente,
    require_medico, require_enfermeiro, require_medico_or_atendente
)

router = APIRouter()


@router.get("/", response_model=ExameListResponse)
async def listar_exames(
    skip: int = Query(0, ge=0, description="Número de registros para pular"),
    limit: int = Query(100, ge=1, le=1000, description="Número máximo de registros"),
    paciente_id: Optional[int] = Query(None, description="Filtrar por paciente"),
    medico_id: Optional[int] = Query(None, description="Filtrar por médico responsável"),
    enfermeiro_id: Optional[int] = Query(None, description="Filtrar por enfermeiro"),
    status: Optional[StatusExame] = Query(None, description="Filtrar por status"),
    tipo: Optional[TipoExame] = Query(None, description="Filtrar por tipo"),
    data_inicio: Optional[date] = Query(None, description="Data de início (YYYY-MM-DD)"),
    data_fim: Optional[date] = Query(None, description="Data de fim (YYYY-MM-DD)"),
    search: Optional[str] = Query(None, description="Buscar por nome do exame ou paciente"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Listar exames com filtros e paginação.
    
    Permissões:
    - Admin: Pode ver todos os exames
    - Médicos: Podem ver todos os exames
    - Atendentes: Podem ver todos os exames
    - Enfermeiros: Podem ver exames onde são responsáveis
    """
    
    # Enfermeiros só veem exames onde são responsáveis
    if current_user.tipo == "enfermeiro":
        enfermeiro_id = current_user.id
    
    exames = exame_repository.get_all(
        db=db,
        skip=skip,
        limit=limit,
        paciente_id=paciente_id,
        medico_id=medico_id,
        enfermeiro_id=enfermeiro_id,
        status=status,
        tipo=tipo,
        data_inicio=data_inicio,
        data_fim=data_fim,
        search=search
    )
    
    total = exame_repository.count(
        db=db,
        paciente_id=paciente_id,
        medico_id=medico_id,
        enfermeiro_id=enfermeiro_id,
        status=status,
        tipo=tipo,
        data_inicio=data_inicio,
        data_fim=data_fim,
        search=search
    )
    
    # Enriquecer dados com nomes dos relacionamentos
    exames_response = []
    for exame in exames:
        exame_dict = {
            **exame.__dict__,
            "paciente_nome": exame.paciente.nome if exame.paciente else None,
            "medico_nome": exame.medico_responsavel.nome if exame.medico_responsavel else None,
            "enfermeiro_nome": exame.enfermeiro_responsavel.nome if exame.enfermeiro_responsavel else None
        }
        exames_response.append(ExameResponse.model_validate(exame_dict))
    
    return ExameListResponse(
        exames=exames_response,
        total=total,
        page=(skip // limit) + 1,
        size=limit
    )


@router.post("/", response_model=ExameResponse, status_code=status.HTTP_201_CREATED)
async def criar_exame(
    exame_data: ExameCreate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_medico_or_atendente)
):
    """
    Criar novo exame.
    Apenas médicos e atendentes podem agendar exames.
    """
    exame = exame_repository.create(db=db, exame_data=exame_data)
    
    return ExameResponse(
        **exame.__dict__,
        paciente_nome=exame.paciente.nome if exame.paciente else None,
        medico_nome=exame.medico_responsavel.nome if exame.medico_responsavel else None,
        enfermeiro_nome=exame.enfermeiro_responsavel.nome if exame.enfermeiro_responsavel else None
    )


@router.post("/agendamento", response_model=ExameResponse, status_code=status.HTTP_201_CREATED)
async def agendar_exame_rapido(
    exame_data: ExameAgendamentoCreate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_medico_or_atendente)
):
    """
    Agendamento rápido de exame com dados essenciais.
    """
    create_data = ExameCreate(**exame_data.model_dump())
    exame = exame_repository.create(db=db, exame_data=create_data)
    
    return ExameResponse(
        **exame.__dict__,
        paciente_nome=exame.paciente.nome if exame.paciente else None,
        medico_nome=exame.medico_responsavel.nome if exame.medico_responsavel else None,
        enfermeiro_nome=exame.enfermeiro_responsavel.nome if exame.enfermeiro_responsavel else None
    )


@router.get("/{exame_id}", response_model=ExameResponse)
async def buscar_exame(
    exame_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar exame por ID.
    """
    exame = exame_repository.get_by_id(db=db, exame_id=exame_id)
    if not exame:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Exame não encontrado"
        )
    
    # Verificar permissões: enfermeiros só veem exames onde são responsáveis
    if current_user.tipo == "enfermeiro" and exame.enfermeiro_responsavel_id != current_user.id:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Sem permissão para acessar este exame"
        )
    
    return ExameResponse(
        **exame.__dict__,
        paciente_nome=exame.paciente.nome if exame.paciente else None,
        medico_nome=exame.medico_responsavel.nome if exame.medico_responsavel else None,
        enfermeiro_nome=exame.enfermeiro_responsavel.nome if exame.enfermeiro_responsavel else None
    )


@router.put("/{exame_id}", response_model=ExameResponse)
async def atualizar_exame(
    exame_id: int,
    exame_data: ExameUpdate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Atualizar exame.
    
    Permissões:
    - Admin: Pode atualizar qualquer exame
    - Médicos: Podem atualizar exames onde são responsáveis + resultados
    - Atendentes: Podem reagendar exames
    - Enfermeiros: Podem atualizar dados de execução dos seus exames
    """
    exame = exame_repository.get_by_id(db=db, exame_id=exame_id)
    if not exame:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Exame não encontrado"
        )
    
    # Verificar permissões específicas
    if current_user.tipo == "enfermeiro":
        if exame.enfermeiro_responsavel_id != current_user.id:
            raise HTTPException(
                status_code=status.HTTP_403_FORBIDDEN,
                detail="Enfermeiros só podem atualizar seus próprios exames"
            )
    elif current_user.tipo == "medico":
        if exame.medico_responsavel_id != current_user.id:
            raise HTTPException(
                status_code=status.HTTP_403_FORBIDDEN,
                detail="Médicos só podem atualizar exames onde são responsáveis"
            )
    # Admin e atendente podem atualizar qualquer exame
    
    exame_atualizado = exame_repository.update(db=db, exame_id=exame_id, exame_data=exame_data)
    
    return ExameResponse(
        **exame_atualizado.__dict__,
        paciente_nome=exame_atualizado.paciente.nome if exame_atualizado.paciente else None,
        medico_nome=exame_atualizado.medico_responsavel.nome if exame_atualizado.medico_responsavel else None,
        enfermeiro_nome=exame_atualizado.enfermeiro_responsavel.nome if exame_atualizado.enfermeiro_responsavel else None
    )


@router.put("/{exame_id}/resultado", response_model=ExameResponse)
async def atualizar_resultado(
    exame_id: int,
    resultado_data: ExameResultadoUpdate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_medico)
):
    """
    Atualizar resultado do exame.
    Apenas médicos podem inserir/atualizar resultados e laudos.
    """
    exame = exame_repository.get_by_id(db=db, exame_id=exame_id)
    if not exame:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Exame não encontrado"
        )
    
    # Verificar se o médico é o responsável pelo exame
    if exame.medico_responsavel_id != current_user.id:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Apenas o médico responsável pode atualizar o resultado"
        )
    
    update_data = ExameUpdate(**resultado_data.model_dump())
    exame_atualizado = exame_repository.update(db=db, exame_id=exame_id, exame_data=update_data)
    
    return ExameResponse(
        **exame_atualizado.__dict__,
        paciente_nome=exame_atualizado.paciente.nome if exame_atualizado.paciente else None,
        medico_nome=exame_atualizado.medico_responsavel.nome if exame_atualizado.medico_responsavel else None,
        enfermeiro_nome=exame_atualizado.enfermeiro_responsavel.nome if exame_atualizado.enfermeiro_responsavel else None
    )


@router.put("/{exame_id}/status/{novo_status}", response_model=ExameResponse)
async def atualizar_status(
    exame_id: int,
    novo_status: StatusExame,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Atualizar status do exame.
    """
    exame = exame_repository.get_by_id(db=db, exame_id=exame_id)
    if not exame:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Exame não encontrado"
        )
    
    # Verificar permissões por tipo de usuário
    if current_user.tipo == "enfermeiro":
        if exame.enfermeiro_responsavel_id != current_user.id:
            raise HTTPException(
                status_code=status.HTTP_403_FORBIDDEN,
                detail="Sem permissão para atualizar este exame"
            )
    
    exame_atualizado = exame_repository.update_status(db=db, exame_id=exame_id, novo_status=novo_status)
    
    return ExameResponse(
        **exame_atualizado.__dict__,
        paciente_nome=exame_atualizado.paciente.nome if exame_atualizado.paciente else None,
        medico_nome=exame_atualizado.medico_responsavel.nome if exame_atualizado.medico_responsavel else None,
        enfermeiro_nome=exame_atualizado.enfermeiro_responsavel.nome if exame_atualizado.enfermeiro_responsavel else None
    )


@router.delete("/{exame_id}", status_code=status.HTTP_204_NO_CONTENT)
async def cancelar_exame(
    exame_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_admin)
):
    """
    Cancelar/deletar exame.
    Apenas administradores podem deletar exames.
    """
    sucesso = exame_repository.delete(db=db, exame_id=exame_id)
    if not sucesso:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Exame não encontrado"
        )


@router.get("/paciente/{paciente_id}", response_model=List[ExameResponse])
async def exames_do_paciente(
    paciente_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar todos os exames de um paciente.
    """
    exames = exame_repository.get_by_paciente(db=db, paciente_id=paciente_id)
    
    return [
        ExameResponse(
            **exame.__dict__,
            paciente_nome=exame.paciente.nome if exame.paciente else None,
            medico_nome=exame.medico_responsavel.nome if exame.medico_responsavel else None,
            enfermeiro_nome=exame.enfermeiro_responsavel.nome if exame.enfermeiro_responsavel else None
        )
        for exame in exames
    ]


@router.get("/medico/meus-exames", response_model=List[ExameResponse])
async def meus_exames_medico(
    data: Optional[date] = Query(None, description="Data específica (YYYY-MM-DD)"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_medico)
):
    """
    Buscar exames do médico logado.
    """
    exames = exame_repository.get_by_medico(db=db, medico_id=current_user.id, data=data)
    
    return [
        ExameResponse(
            **exame.__dict__,
            paciente_nome=exame.paciente.nome if exame.paciente else None,
            medico_nome=exame.medico_responsavel.nome if exame.medico_responsavel else None,
            enfermeiro_nome=exame.enfermeiro_responsavel.nome if exame.enfermeiro_responsavel else None
        )
        for exame in exames
    ]


@router.get("/enfermeiro/meus-exames", response_model=List[ExameResponse])
async def meus_exames_enfermeiro(
    data: Optional[date] = Query(None, description="Data específica (YYYY-MM-DD)"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_enfermeiro)
):
    """
    Buscar exames do enfermeiro logado.
    """
    exames = exame_repository.get_by_enfermeiro(db=db, enfermeiro_id=current_user.id, data=data)
    
    return [
        ExameResponse(
            **exame.__dict__,
            paciente_nome=exame.paciente.nome if exame.paciente else None,
            medico_nome=exame.medico_responsavel.nome if exame.medico_responsavel else None,
            enfermeiro_nome=exame.enfermeiro_responsavel.nome if exame.enfermeiro_responsavel else None
        )
        for exame in exames
    ]


@router.get("/agenda/{data}", response_model=List[ExameResponse])
async def agenda_exames_dia(
    data: date,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar todos os exames agendados para um dia específico.
    """
    exames = exame_repository.get_agenda_dia(db=db, data=data)
    
    return [
        ExameResponse(
            **exame.__dict__,
            paciente_nome=exame.paciente.nome if exame.paciente else None,
            medico_nome=exame.medico_responsavel.nome if exame.medico_responsavel else None,
            enfermeiro_nome=exame.enfermeiro_responsavel.nome if exame.enfermeiro_responsavel else None
        )
        for exame in exames
    ] 