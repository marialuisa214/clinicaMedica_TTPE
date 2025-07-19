"""
Schemas para consultas e agenda médica
"""

from pydantic import BaseModel, validator
from typing import Optional, List
from datetime import datetime, date, time
from app.models.consulta import StatusConsulta, TipoConsulta


class ConsultaBase(BaseModel):
    """Schema base para consulta"""
    paciente_id: int
    medico_id: int
    data_hora: datetime
    tipo: TipoConsulta = TipoConsulta.CONSULTA_NORMAL
    motivo: Optional[str] = None
    observacoes: Optional[str] = None


class ConsultaCreate(ConsultaBase):
    """Schema para criação de consulta"""
    pass


class ConsultaUpdate(BaseModel):
    """Schema para atualização de consulta"""
    data_hora: Optional[datetime] = None
    tipo: Optional[TipoConsulta] = None
    status: Optional[StatusConsulta] = None
    motivo: Optional[str] = None
    observacoes: Optional[str] = None
    diagnostico: Optional[str] = None
    prescricao: Optional[str] = None


class ConsultaResponse(ConsultaBase):
    """Schema para resposta de consulta"""
    id: int
    status: StatusConsulta
    diagnostico: Optional[str] = None
    prescricao: Optional[str] = None
    atendente_id: Optional[int] = None
    created_at: datetime
    updated_at: datetime
    
    # Dados relacionados
    paciente_nome: Optional[str] = None
    medico_nome: Optional[str] = None
    atendente_nome: Optional[str] = None
    
    class Config:
        from_attributes = True


class ConsultaListResponse(BaseModel):
    """Schema para listagem de consultas"""
    consultas: List[ConsultaResponse]
    total: int
    page: int
    size: int


class AgendaMedicoBase(BaseModel):
    """Schema base para agenda do médico"""
    medico_id: int
    data: date
    hora_inicio: str  # HH:MM
    hora_fim: str     # HH:MM
    disponivel: bool = True
    motivo_indisponibilidade: Optional[str] = None
    
    @validator('hora_inicio', 'hora_fim')
    def validate_time_format(cls, v):
        try:
            time.fromisoformat(v)
        except ValueError:
            raise ValueError('Formato de hora deve ser HH:MM')
        return v


class AgendaMedicoCreate(AgendaMedicoBase):
    """Schema para criação de agenda do médico"""
    pass


class AgendaMedicoUpdate(BaseModel):
    """Schema para atualização de agenda do médico"""
    disponivel: Optional[bool] = None
    motivo_indisponibilidade: Optional[str] = None


class AgendaMedicoResponse(AgendaMedicoBase):
    """Schema para resposta de agenda do médico"""
    id: int
    created_at: datetime
    updated_at: datetime
    medico_nome: Optional[str] = None
    
    class Config:
        from_attributes = True


class HorarioDisponivelResponse(BaseModel):
    """Schema para horários disponíveis"""
    data: date
    horarios: List[str]  # Lista de horários no formato HH:MM


class AgendaMedicoListResponse(BaseModel):
    """Schema para listagem de agenda"""
    agenda: List[AgendaMedicoResponse]
    total: int


# Schemas para relatórios e dashboards
class ConsultasPorMedico(BaseModel):
    """Schema para consultas por médico"""
    medico_id: int
    medico_nome: str
    total_consultas: int
    consultas_hoje: int
    consultas_pendentes: int


class EstatisticasConsultas(BaseModel):
    """Schema para estatísticas de consultas"""
    total_consultas: int
    consultas_hoje: int
    consultas_semana: int
    consultas_mes: int
    consultas_por_status: dict
    consultas_por_tipo: dict 