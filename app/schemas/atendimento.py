"""
Schemas Pydantic para Atendimentos
"""

from datetime import datetime
from typing import Optional, List
from pydantic import BaseModel, Field

from app.models.atendimento import TipoAtendimento, StatusAtendimento


class AtendimentoBase(BaseModel):
    """Schema base para Atendimento"""
    tipo_atendimento: TipoAtendimento = Field(default=TipoAtendimento.AMBULATORIAL, description="Tipo do atendimento")
    motivo_atendimento: str = Field(..., min_length=5, max_length=500, description="Motivo do atendimento")
    setor_atendimento: Optional[str] = Field(None, max_length=100, description="Setor onde será realizado")
    leito: Optional[str] = Field(None, max_length=20, description="Número do leito")


class AtendimentoCreate(AtendimentoBase):
    """Schema para criação de Atendimento"""
    paciente_id: int = Field(..., description="ID do paciente")
    enfermeiro_responsavel_id: int = Field(..., description="ID do enfermeiro responsável")
    medico_supervisor_id: Optional[int] = Field(None, description="ID do médico supervisor")
    data_inicio: Optional[datetime] = Field(None, description="Data e hora de início (padrão: agora)")


class AtendimentoUpdate(BaseModel):
    """Schema para atualização de Atendimento"""
    tipo_atendimento: Optional[TipoAtendimento] = None
    motivo_atendimento: Optional[str] = Field(None, min_length=5, max_length=500)
    data_fim: Optional[datetime] = None
    status: Optional[StatusAtendimento] = None
    
    # Sinais vitais
    pressao_arterial: Optional[str] = Field(None, max_length=20)
    temperatura: Optional[float] = Field(None, ge=30.0, le=45.0)  # Celsius
    frequencia_cardiaca: Optional[int] = Field(None, ge=30, le=200)  # BPM
    frequencia_respiratoria: Optional[int] = Field(None, ge=8, le=40)  # IRPM
    saturacao_oxigenio: Optional[float] = Field(None, ge=70.0, le=100.0)  # %
    peso: Optional[float] = Field(None, ge=0.5, le=300.0)  # Kg
    altura: Optional[float] = Field(None, ge=30.0, le=250.0)  # cm
    dor_escala: Optional[int] = Field(None, ge=0, le=10)  # Escala 0-10
    
    # Observações clínicas
    queixas_principais: Optional[str] = None
    historico_atual: Optional[str] = None
    procedimentos_realizados: Optional[str] = None
    medicamentos_administrados: Optional[str] = None
    observacoes_enfermagem: Optional[str] = None
    orientacoes_paciente: Optional[str] = None
    
    # Resultado
    condicoes_alta: Optional[str] = None
    encaminhamentos: Optional[str] = None
    retorno_necessario: Optional[str] = None
    
    # Local
    setor_atendimento: Optional[str] = Field(None, max_length=100)
    leito: Optional[str] = Field(None, max_length=20)
    
    medico_supervisor_id: Optional[int] = None


class AtendimentoResponse(AtendimentoBase):
    """Schema de resposta para Atendimento"""
    id: int
    paciente_id: int
    enfermeiro_responsavel_id: int
    medico_supervisor_id: Optional[int]
    
    data_inicio: datetime
    data_fim: Optional[datetime]
    duracao_minutos: Optional[int]
    status: StatusAtendimento
    
    # Sinais vitais
    pressao_arterial: Optional[str]
    temperatura: Optional[float]
    frequencia_cardiaca: Optional[int]
    frequencia_respiratoria: Optional[int]
    saturacao_oxigenio: Optional[float]
    peso: Optional[float]
    altura: Optional[float]
    dor_escala: Optional[int]
    
    # Observações clínicas
    queixas_principais: Optional[str]
    historico_atual: Optional[str]
    procedimentos_realizados: Optional[str]
    medicamentos_administrados: Optional[str]
    observacoes_enfermagem: Optional[str]
    orientacoes_paciente: Optional[str]
    
    # Resultado
    condicoes_alta: Optional[str]
    encaminhamentos: Optional[str]
    retorno_necessario: Optional[str]
    
    created_at: datetime
    updated_at: datetime
    
    # Campos calculados (opcional)
    paciente_nome: Optional[str] = None
    enfermeiro_nome: Optional[str] = None
    medico_supervisor_nome: Optional[str] = None
    imc: Optional[float] = None

    class Config:
        from_attributes = True


class AtendimentoListResponse(BaseModel):
    """Schema para listagem paginada de atendimentos"""
    atendimentos: List[AtendimentoResponse]
    total: int
    page: int
    size: int


class TriagemCreate(BaseModel):
    """Schema simplificado para criação de triagem"""
    paciente_id: int
    enfermeiro_responsavel_id: int
    motivo_atendimento: str = Field(..., min_length=5, max_length=500)
    
    # Sinais vitais obrigatórios para triagem
    pressao_arterial: str = Field(..., max_length=20)
    temperatura: float = Field(..., ge=30.0, le=45.0)
    frequencia_cardiaca: int = Field(..., ge=30, le=200)
    saturacao_oxigenio: float = Field(..., ge=70.0, le=100.0)
    peso: Optional[float] = Field(None, ge=0.5, le=300.0)
    altura: Optional[float] = Field(None, ge=30.0, le=250.0)
    dor_escala: int = Field(..., ge=0, le=10)
    
    queixas_principais: str = Field(..., min_length=10)
    observacoes_enfermagem: Optional[str] = None


class SinaisVitaisUpdate(BaseModel):
    """Schema para atualização apenas dos sinais vitais"""
    pressao_arterial: Optional[str] = Field(None, max_length=20)
    temperatura: Optional[float] = Field(None, ge=30.0, le=45.0)
    frequencia_cardiaca: Optional[int] = Field(None, ge=30, le=200)
    frequencia_respiratoria: Optional[int] = Field(None, ge=8, le=40)
    saturacao_oxigenio: Optional[float] = Field(None, ge=70.0, le=100.0)
    peso: Optional[float] = Field(None, ge=0.5, le=300.0)
    altura: Optional[float] = Field(None, ge=30.0, le=250.0)
    dor_escala: Optional[int] = Field(None, ge=0, le=10)
    observacoes_enfermagem: Optional[str] = None 