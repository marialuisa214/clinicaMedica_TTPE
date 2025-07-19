"""
Schemas Pydantic da aplicação
"""

from app.schemas.auth import LoginRequest, TokenResponse, UserInfo
from app.schemas.paciente import PacienteBase, PacienteCreate, PacienteUpdate, PacienteResponse
from app.schemas.funcionario import (
    FuncionarioBase, FuncionarioCreate, FuncionarioUpdate, FuncionarioResponse,
    FuncionarioListResponse, MedicoResponse
)
from app.schemas.consulta import (
    ConsultaBase, ConsultaCreate, ConsultaUpdate, ConsultaResponse, 
    ConsultaListResponse, AgendaMedicoResponse, HorarioDisponivelResponse
)
from app.schemas.exame import (
    ExameBase, ExameCreate, ExameUpdate, ExameResponse, ExameListResponse,
    ExameAgendamentoCreate, ExameResultadoUpdate
)
from app.schemas.atendimento import (
    AtendimentoBase, AtendimentoCreate, AtendimentoUpdate, AtendimentoResponse,
    AtendimentoListResponse, TriagemCreate, SinaisVitaisUpdate
)

__all__ = [
    # Auth
    "LoginRequest", "TokenResponse", "UserInfo",
    
    # Paciente
    "PacienteBase", "PacienteCreate", "PacienteUpdate", "PacienteResponse",
    
    # Funcionario
    "FuncionarioBase", "FuncionarioCreate", "FuncionarioUpdate", "FuncionarioResponse",
    "FuncionarioListResponse", "MedicoResponse",
    
    # Consulta
    "ConsultaBase", "ConsultaCreate", "ConsultaUpdate", "ConsultaResponse",
    "ConsultaListResponse", "AgendaMedicoResponse", "HorarioDisponivelResponse",
    
    # Exame
    "ExameBase", "ExameCreate", "ExameUpdate", "ExameResponse", "ExameListResponse",
    "ExameAgendamentoCreate", "ExameResultadoUpdate",
    
    # Atendimento
    "AtendimentoBase", "AtendimentoCreate", "AtendimentoUpdate", "AtendimentoResponse",
    "AtendimentoListResponse", "TriagemCreate", "SinaisVitaisUpdate"
] 