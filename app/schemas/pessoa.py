"""
Schemas para entidade Pessoa
"""

from datetime import date
from typing import Optional
from pydantic import EmailStr, Field
from app.models.pessoa import SexoEnum
from .base import BaseSchema, BaseResponseSchema


class PessoaBase(BaseSchema):
    """Schema base para Pessoa"""
    
    nome: str = Field(..., min_length=2, max_length=255)
    rg: str = Field(..., min_length=5, max_length=20)
    cpf: str = Field(..., pattern=r'^\d{3}\.\d{3}\.\d{3}-\d{2}$')
    sexo: SexoEnum
    data_nascimento: date
    telefone: Optional[str] = Field(None, max_length=20)
    email: Optional[EmailStr] = None
    cidade_estado: Optional[str] = Field(None, max_length=100)
    endereco: Optional[str] = Field(None, max_length=255)


class PessoaResponse(PessoaBase, BaseResponseSchema):
    """Schema de resposta para Pessoa"""
    
    idade: int 