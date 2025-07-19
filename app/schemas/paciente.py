"""
Schemas para entidade Paciente
"""

from typing import Optional, List
from pydantic import Field
from .base import BaseSchema
from .pessoa import PessoaBase, PessoaResponse


class PacienteBase(PessoaBase):
    """Schema base para Paciente"""
    
    patologia: Optional[str] = Field(None, max_length=500)


class PacienteCreate(PacienteBase):
    """Schema para criação de Paciente"""
    pass


class PacienteUpdate(BaseSchema):
    """Schema para atualização de Paciente"""
    
    nome: Optional[str] = Field(None, min_length=2, max_length=255)
    telefone: Optional[str] = Field(None, max_length=20)
    email: Optional[str] = None
    cidade_estado: Optional[str] = Field(None, max_length=100)
    endereco: Optional[str] = Field(None, max_length=255)
    patologia: Optional[str] = Field(None, max_length=500)


class PacienteResponse(PacienteBase, PessoaResponse):
    """Schema de resposta para Paciente"""
    
    id: int


class PacienteListResponse(BaseSchema):
    """Schema para lista de pacientes"""
    
    pacientes: List[PacienteResponse]
    total: int 