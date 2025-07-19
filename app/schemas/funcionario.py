"""
Schemas para funcionários
"""

from pydantic import BaseModel, EmailStr, validator
from typing import Optional, List
from datetime import datetime


class FuncionarioBase(BaseModel):
    """Schema base para funcionário"""
    nome: str
    usuario: str
    email: EmailStr
    telefone: Optional[str] = None
    tipo: str
    
    # Campos específicos por tipo
    crm: Optional[str] = None
    especialidade: Optional[str] = None
    coren: Optional[str] = None
    crf: Optional[str] = None
    setor: Optional[str] = None


class FuncionarioCreate(FuncionarioBase):
    """Schema para criação de funcionário"""
    senha: str
    
    @validator('tipo')
    def validate_tipo(cls, v):
        tipos_validos = ['administrador', 'medico', 'enfermeiro', 'atendente', 'farmaceutico']
        if v not in tipos_validos:
            raise ValueError(f'Tipo deve ser um de: {", ".join(tipos_validos)}')
        return v
    
    @validator('crm')
    def validate_crm(cls, v, values):
        if values.get('tipo') == 'medico' and not v:
            raise ValueError('CRM é obrigatório para médicos')
        return v
    
    @validator('coren')
    def validate_coren(cls, v, values):
        if values.get('tipo') == 'enfermeiro' and not v:
            raise ValueError('COREN é obrigatório para enfermeiros')
        return v
    
    @validator('crf')
    def validate_crf(cls, v, values):
        if values.get('tipo') == 'farmaceutico' and not v:
            raise ValueError('CRF é obrigatório para farmacêuticos')
        return v


class FuncionarioUpdate(BaseModel):
    """Schema para atualização de funcionário"""
    nome: Optional[str] = None
    email: Optional[EmailStr] = None
    telefone: Optional[str] = None
    crm: Optional[str] = None
    especialidade: Optional[str] = None
    coren: Optional[str] = None
    crf: Optional[str] = None
    setor: Optional[str] = None
    senha: Optional[str] = None


class FuncionarioResponse(FuncionarioBase):
    """Schema para resposta de funcionário"""
    id: int
    created_at: str
    updated_at: str
    
    class Config:
        from_attributes = True


class FuncionarioListResponse(BaseModel):
    """Schema para listagem de funcionários"""
    funcionarios: List[FuncionarioResponse]
    total: int
    page: int
    size: int


# Schemas específicos por tipo
class MedicoCreate(FuncionarioCreate):
    """Schema específico para criação de médico"""
    tipo: str = "medico"
    crm: str
    especialidade: str


class MedicoResponse(FuncionarioResponse):
    """Schema específico para resposta de médico"""
    crm: str
    especialidade: str


class EnfermeiroCreate(FuncionarioCreate):
    """Schema específico para criação de enfermeiro"""
    tipo: str = "enfermeiro"
    coren: str


class AtendenteCreate(FuncionarioCreate):
    """Schema específico para criação de atendente"""
    tipo: str = "atendente"
    setor: Optional[str] = None


class FarmaceuticoCreate(FuncionarioCreate):
    """Schema específico para criação de farmacêutico"""
    tipo: str = "farmaceutico"
    crf: str


class AdministradorCreate(FuncionarioCreate):
    """Schema específico para criação de administrador"""
    tipo: str = "administrador"
    setor: Optional[str] = None 