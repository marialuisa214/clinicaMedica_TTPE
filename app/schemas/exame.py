"""
Schemas Pydantic para Exames
"""

from datetime import datetime
from typing import Optional, List
from pydantic import BaseModel, Field

from app.models.exame import TipoExame, StatusExame


class ExameBase(BaseModel):
    """Schema base para Exame"""
    nome_exame: str = Field(..., min_length=2, max_length=255, description="Nome do exame")
    tipo_exame: TipoExame = Field(default=TipoExame.LABORATORIAL, description="Tipo do exame")
    descricao: Optional[str] = Field(None, description="Descrição detalhada do exame")
    data_agendamento: datetime = Field(..., description="Data e hora do agendamento")
    observacoes: Optional[str] = Field(None, description="Observações gerais")
    preparo_necessario: Optional[str] = Field(None, description="Instruções de preparo para o paciente")
    valor_exame: Optional[str] = Field(None, description="Valor do exame")
    convenio: Optional[str] = Field(None, description="Convênio responsável")


class ExameCreate(ExameBase):
    """Schema para criação de Exame"""
    paciente_id: int = Field(..., description="ID do paciente")
    medico_responsavel_id: int = Field(..., description="ID do médico responsável")
    enfermeiro_responsavel_id: Optional[int] = Field(None, description="ID do enfermeiro responsável")


class ExameUpdate(BaseModel):
    """Schema para atualização de Exame"""
    nome_exame: Optional[str] = Field(None, min_length=2, max_length=255)
    tipo_exame: Optional[TipoExame] = None
    descricao: Optional[str] = None
    data_agendamento: Optional[datetime] = None
    data_execucao: Optional[datetime] = None
    data_resultado: Optional[datetime] = None
    status: Optional[StatusExame] = None
    observacoes: Optional[str] = None
    preparo_necessario: Optional[str] = None
    resultado: Optional[str] = None
    laudo_medico: Optional[str] = None
    arquivo_resultado: Optional[str] = None
    valor_exame: Optional[str] = None
    convenio: Optional[str] = None
    enfermeiro_responsavel_id: Optional[int] = None


class ExameResponse(ExameBase):
    """Schema de resposta para Exame"""
    id: int
    paciente_id: int
    medico_responsavel_id: int
    enfermeiro_responsavel_id: Optional[int]
    data_execucao: Optional[datetime]
    data_resultado: Optional[datetime]
    status: StatusExame
    resultado: Optional[str]
    laudo_medico: Optional[str]
    arquivo_resultado: Optional[str]
    created_at: datetime
    updated_at: datetime
    
    # Campos calculados (opcional)
    paciente_nome: Optional[str] = None
    medico_nome: Optional[str] = None
    enfermeiro_nome: Optional[str] = None

    class Config:
        from_attributes = True


class ExameListResponse(BaseModel):
    """Schema para listagem paginada de exames"""
    exames: List[ExameResponse]
    total: int
    page: int
    size: int


class ExameAgendamentoCreate(BaseModel):
    """Schema simplificado para agendamento rápido de exame"""
    paciente_id: int
    medico_responsavel_id: int
    nome_exame: str
    tipo_exame: TipoExame = TipoExame.LABORATORIAL
    data_agendamento: datetime
    preparo_necessario: Optional[str] = None
    observacoes: Optional[str] = None


class ExameResultadoUpdate(BaseModel):
    """Schema para atualização apenas dos resultados do exame"""
    data_execucao: Optional[datetime] = None
    data_resultado: Optional[datetime] = None
    resultado: Optional[str] = None
    laudo_medico: Optional[str] = None
    arquivo_resultado: Optional[str] = None
    status: StatusExame = StatusExame.RESULTADO_DISPONIVEL
    observacoes: Optional[str] = None 