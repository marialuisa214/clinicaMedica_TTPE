"""
Endpoints para consultas e agenda médica
"""

from typing import List, Optional
from fastapi import APIRouter, Depends, HTTPException, status, Query
from sqlalchemy.orm import Session
from datetime import date, datetime

from app.core.database import get_db
from app.repositories.consulta import consulta_repository, agenda_medico_repository
from app.repositories.paciente import paciente_repository
from app.repositories.funcionario import funcionario_repository
from app.schemas.consulta import (
    ConsultaCreate, ConsultaUpdate, ConsultaResponse, ConsultaListResponse,
    AgendaMedicoCreate, AgendaMedicoUpdate, AgendaMedicoResponse, 
    HorarioDisponivelResponse, EstatisticasConsultas
)
from app.api.dependencies import (
    get_current_user, require_medico_or_atendente, require_medico, require_admin
)
from app.models.funcionario import Funcionario
from app.models.consulta import StatusConsulta, TipoConsulta

router = APIRouter()


# ENDPOINTS DE CONSULTAS

@router.get("/", response_model=ConsultaListResponse)
def listar_consultas(
    skip: int = Query(0, ge=0, description="Número de registros para pular"),
    limit: int = Query(100, ge=1, le=1000, description="Número máximo de registros"),
    medico_id: Optional[int] = Query(None, description="Filtrar por médico"),
    paciente_id: Optional[int] = Query(None, description="Filtrar por paciente"),
    status: Optional[StatusConsulta] = Query(None, description="Filtrar por status"),
    data_inicio: Optional[date] = Query(None, description="Data de início (YYYY-MM-DD)"),
    data_fim: Optional[date] = Query(None, description="Data de fim (YYYY-MM-DD)"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Listar consultas com filtros e paginação.
    Médicos veem apenas suas consultas, outros tipos veem todas (se autorizados).
    """
    # Se for médico, filtrar apenas suas consultas
    if current_user.tipo == "medico":
        medico_id = current_user.id
    
    consultas, total = consulta_repository.get_all(
        db=db,
        skip=skip,
        limit=limit,
        medico_id=medico_id,
        paciente_id=paciente_id,
        status=status,
        data_inicio=data_inicio,
        data_fim=data_fim
    )
    
    # Enriquecer com dados relacionados
    consultas_response = []
    for consulta in consultas:
        consulta_dict = {
            "id": consulta.id,
            "paciente_id": consulta.paciente_id,
            "medico_id": consulta.medico_id,
            "data_hora": consulta.data_hora,
            "tipo": consulta.tipo,
            "status": consulta.status,
            "motivo": consulta.motivo,
            "observacoes": consulta.observacoes,
            "diagnostico": consulta.diagnostico,
            "prescricao": consulta.prescricao,
            "atendente_id": consulta.atendente_id,
            "created_at": consulta.created_at,
            "updated_at": consulta.updated_at,
            "paciente_nome": consulta.paciente.nome if consulta.paciente else None,
            "medico_nome": consulta.medico.nome if consulta.medico else None,
            "atendente_nome": consulta.atendente.nome if consulta.atendente else None,
        }
        consultas_response.append(ConsultaResponse(**consulta_dict))
    
    return ConsultaListResponse(
        consultas=consultas_response,
        total=total,
        page=skip // limit + 1,
        size=limit
    )


@router.get("/medico/hoje", response_model=List[ConsultaResponse])
def consultas_medico_hoje(
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_medico)
):
    """
    Buscar consultas do médico para hoje.
    Apenas médicos podem acessar.
    """
    consultas = consulta_repository.get_consultas_medico_hoje(db, current_user.id)
    
    consultas_response = []
    for consulta in consultas:
        consulta_dict = {
            "id": consulta.id,
            "paciente_id": consulta.paciente_id,
            "medico_id": consulta.medico_id,
            "data_hora": consulta.data_hora,
            "tipo": consulta.tipo,
            "status": consulta.status,
            "motivo": consulta.motivo,
            "observacoes": consulta.observacoes,
            "diagnostico": consulta.diagnostico,
            "prescricao": consulta.prescricao,
            "atendente_id": consulta.atendente_id,
            "created_at": consulta.created_at,
            "updated_at": consulta.updated_at,
            "paciente_nome": consulta.paciente.nome if consulta.paciente else None,
            "medico_nome": consulta.medico.nome if consulta.medico else None,
            "atendente_nome": consulta.atendente.nome if consulta.atendente else None,
        }
        consultas_response.append(ConsultaResponse(**consulta_dict))
    
    return consultas_response


@router.get("/{consulta_id}", response_model=ConsultaResponse)
def buscar_consulta(
    consulta_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar consulta por ID.
    """
    consulta = consulta_repository.get_by_id(db, consulta_id)
    
    if not consulta:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Consulta não encontrada"
        )
    
    # Verificar permissões
    if (current_user.tipo == "medico" and consulta.medico_id != current_user.id):
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Você só pode visualizar suas próprias consultas."
        )
    
    consulta_dict = {
        "id": consulta.id,
        "paciente_id": consulta.paciente_id,
        "medico_id": consulta.medico_id,
        "data_hora": consulta.data_hora,
        "tipo": consulta.tipo,
        "status": consulta.status,
        "motivo": consulta.motivo,
        "observacoes": consulta.observacoes,
        "diagnostico": consulta.diagnostico,
        "prescricao": consulta.prescricao,
        "atendente_id": consulta.atendente_id,
        "created_at": consulta.created_at,
        "updated_at": consulta.updated_at,
        "paciente_nome": consulta.paciente.nome if consulta.paciente else None,
        "medico_nome": consulta.medico.nome if consulta.medico else None,
        "atendente_nome": consulta.atendente.nome if consulta.atendente else None,
    }
    
    return ConsultaResponse(**consulta_dict)


@router.post("/", response_model=ConsultaResponse, status_code=status.HTTP_201_CREATED)
def criar_consulta(
    consulta_data: ConsultaCreate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_medico_or_atendente)
):
    """
    Criar nova consulta.
    Apenas médicos e atendentes podem criar consultas.
    """
    # Verificar se paciente existe
    paciente = paciente_repository.get_by_id(db, consulta_data.paciente_id)
    if not paciente:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Paciente não encontrado"
        )
    
    # Verificar se médico existe
    medico = funcionario_repository.get_by_id(db, consulta_data.medico_id)
    if not medico or medico.tipo != "medico":
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Médico não encontrado"
        )
    
    # Verificar se já existe consulta no mesmo horário para o médico
    consultas_existentes, _ = consulta_repository.get_all(
        db=db,
        medico_id=consulta_data.medico_id,
        data_inicio=consulta_data.data_hora.date(),
        data_fim=consulta_data.data_hora.date()
    )
    
    for consulta_existente in consultas_existentes:
        if (consulta_existente.data_hora == consulta_data.data_hora and 
            consulta_existente.status != StatusConsulta.CANCELADA):
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail="Já existe uma consulta agendada para este horário"
            )
    
    # Definir atendente se for atendente quem está criando
    atendente_id = current_user.id if current_user.tipo == "atendente" else None
    
    consulta = consulta_repository.create(db, consulta_data, atendente_id)
    
    # Recarregar com relacionamentos
    consulta = consulta_repository.get_by_id(db, consulta.id)
    
    consulta_dict = {
        "id": consulta.id,
        "paciente_id": consulta.paciente_id,
        "medico_id": consulta.medico_id,
        "data_hora": consulta.data_hora,
        "tipo": consulta.tipo,
        "status": consulta.status,
        "motivo": consulta.motivo,
        "observacoes": consulta.observacoes,
        "diagnostico": consulta.diagnostico,
        "prescricao": consulta.prescricao,
        "atendente_id": consulta.atendente_id,
        "created_at": consulta.created_at,
        "updated_at": consulta.updated_at,
        "paciente_nome": consulta.paciente.nome if consulta.paciente else None,
        "medico_nome": consulta.medico.nome if consulta.medico else None,
        "atendente_nome": consulta.atendente.nome if consulta.atendente else None,
    }
    
    return ConsultaResponse(**consulta_dict)


@router.put("/{consulta_id}", response_model=ConsultaResponse)
def atualizar_consulta(
    consulta_id: int,
    consulta_data: ConsultaUpdate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Atualizar consulta.
    Médicos podem atualizar suas consultas, atendentes podem reagendar.
    """
    consulta = consulta_repository.get_by_id(db, consulta_id)
    
    if not consulta:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Consulta não encontrada"
        )
    
    # Verificar permissões
    can_update = (
        current_user.tipo == "administrador" or
        (current_user.tipo == "medico" and consulta.medico_id == current_user.id) or
        (current_user.tipo == "atendente")
    )
    
    if not can_update:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado para atualizar esta consulta"
        )
    
    # Atendentes não podem alterar diagnóstico/prescrição
    if current_user.tipo == "atendente":
        consulta_data.diagnostico = None
        consulta_data.prescricao = None
    
    consulta_atualizada = consulta_repository.update(db, consulta_id, consulta_data)
    
    consulta_dict = {
        "id": consulta_atualizada.id,
        "paciente_id": consulta_atualizada.paciente_id,
        "medico_id": consulta_atualizada.medico_id,
        "data_hora": consulta_atualizada.data_hora,
        "tipo": consulta_atualizada.tipo,
        "status": consulta_atualizada.status,
        "motivo": consulta_atualizada.motivo,
        "observacoes": consulta_atualizada.observacoes,
        "diagnostico": consulta_atualizada.diagnostico,
        "prescricao": consulta_atualizada.prescricao,
        "atendente_id": consulta_atualizada.atendente_id,
        "created_at": consulta_atualizada.created_at,
        "updated_at": consulta_atualizada.updated_at,
        "paciente_nome": consulta_atualizada.paciente.nome if consulta_atualizada.paciente else None,
        "medico_nome": consulta_atualizada.medico.nome if consulta_atualizada.medico else None,
        "atendente_nome": consulta_atualizada.atendente.nome if consulta_atualizada.atendente else None,
    }
    
    return ConsultaResponse(**consulta_dict)


@router.delete("/{consulta_id}", status_code=status.HTTP_204_NO_CONTENT)
def cancelar_consulta(
    consulta_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_medico_or_atendente)
):
    """
    Cancelar consulta (marcar como cancelada).
    Médicos e atendentes podem cancelar consultas.
    """
    consulta = consulta_repository.get_by_id(db, consulta_id)
    
    if not consulta:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Consulta não encontrada"
        )
    
    # Verificar permissões
    if (current_user.tipo == "medico" and consulta.medico_id != current_user.id):
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Você só pode cancelar suas próprias consultas"
        )
    
    # Marcar como cancelada
    consulta_data = ConsultaUpdate(status=StatusConsulta.CANCELADA)
    consulta_repository.update(db, consulta_id, consulta_data)


# ENDPOINTS DE AGENDA MÉDICA

@router.get("/agenda/medico/{medico_id}/horarios-disponiveis")
def horarios_disponiveis(
    medico_id: int,
    data: date = Query(..., description="Data para verificar disponibilidade (YYYY-MM-DD)"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar horários disponíveis de um médico em uma data específica.
    """
    # Verificar se médico existe
    medico = funcionario_repository.get_by_id(db, medico_id)
    if not medico or medico.tipo != "medico":
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Médico não encontrado"
        )
    
    horarios = agenda_medico_repository.get_horarios_disponiveis(db, medico_id, data)
    
    return HorarioDisponivelResponse(
        data=data,
        horarios=horarios
    )


@router.get("/agenda/medico/{medico_id}")
def agenda_medico(
    medico_id: int,
    data_inicio: Optional[date] = Query(None, description="Data de início (YYYY-MM-DD)"),
    data_fim: Optional[date] = Query(None, description="Data de fim (YYYY-MM-DD)"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar agenda do médico por período.
    """
    # Verificar se médico existe
    medico = funcionario_repository.get_by_id(db, medico_id)
    if not medico or medico.tipo != "medico":
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Médico não encontrado"
        )
    
    # Médicos só podem ver sua própria agenda
    if current_user.tipo == "medico" and current_user.id != medico_id:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Você só pode visualizar sua própria agenda"
        )
    
    agenda = agenda_medico_repository.get_agenda_medico(
        db, medico_id, data_inicio, data_fim
    )
    
    return [AgendaMedicoResponse(
        id=item.id,
        medico_id=item.medico_id,
        data=item.data,
        hora_inicio=item.hora_inicio,
        hora_fim=item.hora_fim,
        disponivel=item.disponivel,
        motivo_indisponibilidade=item.motivo_indisponibilidade,
        created_at=item.created_at,
        updated_at=item.updated_at,
        medico_nome=item.medico.nome if item.medico else None
    ) for item in agenda] 