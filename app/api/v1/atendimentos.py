"""
Endpoints da API para Atendimentos
"""

from typing import List, Optional
from datetime import date
from fastapi import APIRouter, Depends, HTTPException, Query, status
from sqlalchemy.orm import Session

from app.core.database import get_db
from app.models.funcionario import Funcionario
from app.models.atendimento import StatusAtendimento, TipoAtendimento
from app.schemas.atendimento import (
    AtendimentoCreate, AtendimentoUpdate, AtendimentoResponse, AtendimentoListResponse,
    TriagemCreate, SinaisVitaisUpdate
)
from app.repositories.atendimento import atendimento_repository
from app.api.dependencies import (
    get_current_user, require_admin, require_enfermeiro,
    require_medico, require_enfermeiro_or_medico, require_admin_or_enfermeiro
)

router = APIRouter()


@router.get("/", response_model=AtendimentoListResponse)
async def listar_atendimentos(
    skip: int = Query(0, ge=0, description="Número de registros para pular"),
    limit: int = Query(100, ge=1, le=1000, description="Número máximo de registros"),
    paciente_id: Optional[int] = Query(None, description="Filtrar por paciente"),
    enfermeiro_id: Optional[int] = Query(None, description="Filtrar por enfermeiro"),
    medico_supervisor_id: Optional[int] = Query(None, description="Filtrar por médico supervisor"),
    status: Optional[StatusAtendimento] = Query(None, description="Filtrar por status"),
    tipo: Optional[TipoAtendimento] = Query(None, description="Filtrar por tipo"),
    data_inicio: Optional[date] = Query(None, description="Data de início (YYYY-MM-DD)"),
    data_fim: Optional[date] = Query(None, description="Data de fim (YYYY-MM-DD)"),
    setor: Optional[str] = Query(None, description="Filtrar por setor"),
    search: Optional[str] = Query(None, description="Buscar por motivo ou paciente"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Listar atendimentos com filtros e paginação.
    
    Permissões:
    - Admin: Pode ver todos os atendimentos
    - Médicos: Podem ver todos os atendimentos
    - Enfermeiros: Podem ver atendimentos onde são responsáveis ou todos (dependendo da configuração)
    """
    
    # Enfermeiros podem ver todos os atendimentos (para triagem e coordenação)
    # Mas podem ser limitados aos seus próprios se necessário
    if current_user.tipo == "enfermeiro":
        # Opcional: limitar aos atendimentos do enfermeiro
        # enfermeiro_id = current_user.id
        pass
    
    atendimentos = atendimento_repository.get_all(
        db=db,
        skip=skip,
        limit=limit,
        paciente_id=paciente_id,
        enfermeiro_id=enfermeiro_id,
        medico_supervisor_id=medico_supervisor_id,
        status=status,
        tipo=tipo,
        data_inicio=data_inicio,
        data_fim=data_fim,
        setor=setor,
        search=search
    )
    
    total = atendimento_repository.count(
        db=db,
        paciente_id=paciente_id,
        enfermeiro_id=enfermeiro_id,
        medico_supervisor_id=medico_supervisor_id,
        status=status,
        tipo=tipo,
        data_inicio=data_inicio,
        data_fim=data_fim,
        setor=setor,
        search=search
    )
    
    # Enriquecer dados com nomes dos relacionamentos
    atendimentos_response = []
    for atendimento in atendimentos:
        atendimento_dict = {
            **atendimento.__dict__,
            "paciente_nome": atendimento.paciente.nome if atendimento.paciente else None,
            "enfermeiro_nome": atendimento.enfermeiro_responsavel.nome if atendimento.enfermeiro_responsavel else None,
            "medico_supervisor_nome": atendimento.medico_supervisor.nome if atendimento.medico_supervisor else None,
            "imc": atendimento.imc
        }
        atendimentos_response.append(AtendimentoResponse.model_validate(atendimento_dict))
    
    return AtendimentoListResponse(
        atendimentos=atendimentos_response,
        total=total,
        page=(skip // limit) + 1,
        size=limit
    )


@router.post("/", response_model=AtendimentoResponse, status_code=status.HTTP_201_CREATED)
async def criar_atendimento(
    atendimento_data: AtendimentoCreate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_enfermeiro)
):
    """
    Criar novo atendimento.
    Apenas enfermeiros podem criar atendimentos.
    """
    atendimento = atendimento_repository.create(db=db, atendimento_data=atendimento_data)
    
    return AtendimentoResponse(
        **atendimento.__dict__,
        paciente_nome=atendimento.paciente.nome if atendimento.paciente else None,
        enfermeiro_nome=atendimento.enfermeiro_responsavel.nome if atendimento.enfermeiro_responsavel else None,
        medico_supervisor_nome=atendimento.medico_supervisor.nome if atendimento.medico_supervisor else None,
        imc=atendimento.imc
    )


@router.post("/triagem", response_model=AtendimentoResponse, status_code=status.HTTP_201_CREATED)
async def criar_triagem(
    triagem_data: TriagemCreate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_enfermeiro)
):
    """
    Criar triagem com dados essenciais.
    """
    # Converter dados de triagem para atendimento completo
    atendimento_data = AtendimentoCreate(
        paciente_id=triagem_data.paciente_id,
        enfermeiro_responsavel_id=triagem_data.enfermeiro_responsavel_id,
        tipo_atendimento=TipoAtendimento.TRIAGEM,
        motivo_atendimento=triagem_data.motivo_atendimento,
        setor_atendimento="Triagem"
    )
    
    atendimento = atendimento_repository.create(db=db, atendimento_data=atendimento_data)
    
    # Atualizar com dados da triagem
    update_data = AtendimentoUpdate(
        pressao_arterial=triagem_data.pressao_arterial,
        temperatura=triagem_data.temperatura,
        frequencia_cardiaca=triagem_data.frequencia_cardiaca,
        saturacao_oxigenio=triagem_data.saturacao_oxigenio,
        peso=triagem_data.peso,
        altura=triagem_data.altura,
        dor_escala=triagem_data.dor_escala,
        queixas_principais=triagem_data.queixas_principais,
        observacoes_enfermagem=triagem_data.observacoes_enfermagem,
        status=StatusAtendimento.EM_ATENDIMENTO
    )
    
    atendimento_atualizado = atendimento_repository.update(
        db=db, 
        atendimento_id=atendimento.id, 
        atendimento_data=update_data
    )
    
    return AtendimentoResponse(
        **atendimento_atualizado.__dict__,
        paciente_nome=atendimento_atualizado.paciente.nome if atendimento_atualizado.paciente else None,
        enfermeiro_nome=atendimento_atualizado.enfermeiro_responsavel.nome if atendimento_atualizado.enfermeiro_responsavel else None,
        medico_supervisor_nome=atendimento_atualizado.medico_supervisor.nome if atendimento_atualizado.medico_supervisor else None,
        imc=atendimento_atualizado.imc
    )


@router.get("/{atendimento_id}", response_model=AtendimentoResponse)
async def buscar_atendimento(
    atendimento_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar atendimento por ID.
    """
    atendimento = atendimento_repository.get_by_id(db=db, atendimento_id=atendimento_id)
    if not atendimento:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Atendimento não encontrado"
        )
    
    return AtendimentoResponse(
        **atendimento.__dict__,
        paciente_nome=atendimento.paciente.nome if atendimento.paciente else None,
        enfermeiro_nome=atendimento.enfermeiro_responsavel.nome if atendimento.enfermeiro_responsavel else None,
        medico_supervisor_nome=atendimento.medico_supervisor.nome if atendimento.medico_supervisor else None,
        imc=atendimento.imc
    )


@router.put("/{atendimento_id}", response_model=AtendimentoResponse)
async def atualizar_atendimento(
    atendimento_id: int,
    atendimento_data: AtendimentoUpdate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Atualizar atendimento.
    
    Permissões:
    - Admin: Pode atualizar qualquer atendimento
    - Enfermeiros: Podem atualizar atendimentos onde são responsáveis
    - Médicos: Podem atualizar atendimentos onde são supervisores
    """
    atendimento = atendimento_repository.get_by_id(db=db, atendimento_id=atendimento_id)
    if not atendimento:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Atendimento não encontrado"
        )
    
    # Verificar permissões específicas
    if current_user.tipo == "enfermeiro":
        if atendimento.enfermeiro_responsavel_id != current_user.id:
            raise HTTPException(
                status_code=status.HTTP_403_FORBIDDEN,
                detail="Enfermeiros só podem atualizar seus próprios atendimentos"
            )
    elif current_user.tipo == "medico":
        if atendimento.medico_supervisor_id != current_user.id:
            raise HTTPException(
                status_code=status.HTTP_403_FORBIDDEN,
                detail="Médicos só podem atualizar atendimentos onde são supervisores"
            )
    # Admin pode atualizar qualquer atendimento
    
    atendimento_atualizado = atendimento_repository.update(
        db=db, 
        atendimento_id=atendimento_id, 
        atendimento_data=atendimento_data
    )
    
    return AtendimentoResponse(
        **atendimento_atualizado.__dict__,
        paciente_nome=atendimento_atualizado.paciente.nome if atendimento_atualizado.paciente else None,
        enfermeiro_nome=atendimento_atualizado.enfermeiro_responsavel.nome if atendimento_atualizado.enfermeiro_responsavel else None,
        medico_supervisor_nome=atendimento_atualizado.medico_supervisor.nome if atendimento_atualizado.medico_supervisor else None,
        imc=atendimento_atualizado.imc
    )


@router.put("/{atendimento_id}/sinais-vitais", response_model=AtendimentoResponse)
async def atualizar_sinais_vitais(
    atendimento_id: int,
    sinais_data: SinaisVitaisUpdate,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_enfermeiro)
):
    """
    Atualizar apenas os sinais vitais do atendimento.
    """
    atendimento = atendimento_repository.get_by_id(db=db, atendimento_id=atendimento_id)
    if not atendimento:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Atendimento não encontrado"
        )
    
    update_data = AtendimentoUpdate(**sinais_data.model_dump(exclude_unset=True))
    atendimento_atualizado = atendimento_repository.update(
        db=db, 
        atendimento_id=atendimento_id, 
        atendimento_data=update_data
    )
    
    return AtendimentoResponse(
        **atendimento_atualizado.__dict__,
        paciente_nome=atendimento_atualizado.paciente.nome if atendimento_atualizado.paciente else None,
        enfermeiro_nome=atendimento_atualizado.enfermeiro_responsavel.nome if atendimento_atualizado.enfermeiro_responsavel else None,
        medico_supervisor_nome=atendimento_atualizado.medico_supervisor.nome if atendimento_atualizado.medico_supervisor else None,
        imc=atendimento_atualizado.imc
    )


@router.put("/{atendimento_id}/iniciar", response_model=AtendimentoResponse)
async def iniciar_atendimento(
    atendimento_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_enfermeiro)
):
    """
    Marcar atendimento como em andamento.
    """
    atendimento = atendimento_repository.iniciar_atendimento(db=db, atendimento_id=atendimento_id)
    if not atendimento:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Atendimento não encontrado"
        )
    
    return AtendimentoResponse(
        **atendimento.__dict__,
        paciente_nome=atendimento.paciente.nome if atendimento.paciente else None,
        enfermeiro_nome=atendimento.enfermeiro_responsavel.nome if atendimento.enfermeiro_responsavel else None,
        medico_supervisor_nome=atendimento.medico_supervisor.nome if atendimento.medico_supervisor else None,
        imc=atendimento.imc
    )


@router.put("/{atendimento_id}/finalizar", response_model=AtendimentoResponse)
async def finalizar_atendimento(
    atendimento_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_enfermeiro_or_medico)
):
    """
    Finalizar atendimento automaticamente.
    """
    atendimento = atendimento_repository.finalizar_atendimento(db=db, atendimento_id=atendimento_id)
    if not atendimento:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Atendimento não encontrado"
        )
    
    return AtendimentoResponse(
        **atendimento.__dict__,
        paciente_nome=atendimento.paciente.nome if atendimento.paciente else None,
        enfermeiro_nome=atendimento.enfermeiro_responsavel.nome if atendimento.enfermeiro_responsavel else None,
        medico_supervisor_nome=atendimento.medico_supervisor.nome if atendimento.medico_supervisor else None,
        imc=atendimento.imc
    )


@router.delete("/{atendimento_id}", status_code=status.HTTP_204_NO_CONTENT)
async def deletar_atendimento(
    atendimento_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_admin)
):
    """
    Deletar atendimento.
    Apenas administradores podem deletar atendimentos.
    """
    sucesso = atendimento_repository.delete(db=db, atendimento_id=atendimento_id)
    if not sucesso:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Atendimento não encontrado"
        )


@router.get("/paciente/{paciente_id}", response_model=List[AtendimentoResponse])
async def atendimentos_do_paciente(
    paciente_id: int,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar todos os atendimentos de um paciente.
    """
    atendimentos = atendimento_repository.get_by_paciente(db=db, paciente_id=paciente_id)
    
    return [
        AtendimentoResponse(
            **atendimento.__dict__,
            paciente_nome=atendimento.paciente.nome if atendimento.paciente else None,
            enfermeiro_nome=atendimento.enfermeiro_responsavel.nome if atendimento.enfermeiro_responsavel else None,
            medico_supervisor_nome=atendimento.medico_supervisor.nome if atendimento.medico_supervisor else None,
            imc=atendimento.imc
        )
        for atendimento in atendimentos
    ]


@router.get("/enfermeiro/meus-atendimentos", response_model=List[AtendimentoResponse])
async def meus_atendimentos(
    data: Optional[date] = Query(None, description="Data específica (YYYY-MM-DD)"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_enfermeiro)
):
    """
    Buscar atendimentos do enfermeiro logado.
    """
    atendimentos = atendimento_repository.get_by_enfermeiro(
        db=db, 
        enfermeiro_id=current_user.id, 
        data=data
    )
    
    return [
        AtendimentoResponse(
            **atendimento.__dict__,
            paciente_nome=atendimento.paciente.nome if atendimento.paciente else None,
            enfermeiro_nome=atendimento.enfermeiro_responsavel.nome if atendimento.enfermeiro_responsavel else None,
            medico_supervisor_nome=atendimento.medico_supervisor.nome if atendimento.medico_supervisor else None,
            imc=atendimento.imc
        )
        for atendimento in atendimentos
    ]


@router.get("/em-andamento", response_model=List[AtendimentoResponse])
async def atendimentos_em_andamento(
    enfermeiro_id: Optional[int] = Query(None, description="Filtrar por enfermeiro"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Buscar atendimentos em andamento.
    """
    atendimentos = atendimento_repository.get_atendimentos_em_andamento(
        db=db, 
        enfermeiro_id=enfermeiro_id
    )
    
    return [
        AtendimentoResponse(
            **atendimento.__dict__,
            paciente_nome=atendimento.paciente.nome if atendimento.paciente else None,
            enfermeiro_nome=atendimento.enfermeiro_responsavel.nome if atendimento.enfermeiro_responsavel else None,
            medico_supervisor_nome=atendimento.medico_supervisor.nome if atendimento.medico_supervisor else None,
            imc=atendimento.imc
        )
        for atendimento in atendimentos
    ]


@router.get("/triagens/pendentes", response_model=List[AtendimentoResponse])
async def triagens_pendentes(
    setor: Optional[str] = Query(None, description="Filtrar por setor"),
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_enfermeiro)
):
    """
    Buscar triagens pendentes.
    """
    atendimentos = atendimento_repository.get_triagens_pendentes(db=db, setor=setor)
    
    return [
        AtendimentoResponse(
            **atendimento.__dict__,
            paciente_nome=atendimento.paciente.nome if atendimento.paciente else None,
            enfermeiro_nome=atendimento.enfermeiro_responsavel.nome if atendimento.enfermeiro_responsavel else None,
            medico_supervisor_nome=atendimento.medico_supervisor.nome if atendimento.medico_supervisor else None,
            imc=atendimento.imc
        )
        for atendimento in atendimentos
    ]


@router.get("/enfermeiro/estatisticas/{data}", response_model=dict)
async def estatisticas_enfermeiro(
    data: date,
    db: Session = Depends(get_db),
    current_user: Funcionario = Depends(require_enfermeiro)
):
    """
    Obter estatísticas de atendimentos do enfermeiro para uma data.
    """
    return atendimento_repository.get_estatisticas_enfermeiro(
        db=db, 
        enfermeiro_id=current_user.id, 
        data=data
    ) 