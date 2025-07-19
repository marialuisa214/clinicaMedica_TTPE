"""
Schemas para autenticação
"""

from typing import Optional
from pydantic import Field
from .base import BaseSchema


class LoginRequest(BaseSchema):
    """Schema para requisição de login"""
    
    usuario: str = Field(..., min_length=3, max_length=50)
    senha: str = Field(..., min_length=6, max_length=255)


class TokenResponse(BaseSchema):
    """Schema para resposta de token"""
    
    access_token: str
    token_type: str = "bearer"
    expires_in: int


class TokenData(BaseSchema):
    """Schema para dados do token"""
    
    usuario: Optional[str] = None
    funcionario_id: Optional[int] = None
    tipo_funcionario: Optional[str] = None


class UserInfo(BaseSchema):
    """Schema para informações do usuário logado"""
    
    id: int
    nome: str
    usuario: str
    tipo: str
    email: Optional[str] = None 