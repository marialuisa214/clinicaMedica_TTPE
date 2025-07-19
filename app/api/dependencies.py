"""
Dependências da API
"""

from typing import Optional, List
from fastapi import Depends, HTTPException, status
from fastapi.security import HTTPBearer, HTTPAuthorizationCredentials
from sqlalchemy.orm import Session
from functools import wraps

from app.core.database import get_db
from app.utils.security import verify_token
from app.repositories.funcionario import funcionario_repository
from app.models.funcionario import Funcionario
from app.schemas.auth import TokenData

# Security scheme
security = HTTPBearer()


def get_current_user(
    credentials: HTTPAuthorizationCredentials = Depends(security),
    db: Session = Depends(get_db)
) -> Funcionario:
    """
    Dependency para obter o usuário atual baseado no token JWT
    """
    credentials_exception = HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Não foi possível validar as credenciais",
        headers={"WWW-Authenticate": "Bearer"},
    )
    
    # Verificar token
    payload = verify_token(credentials.credentials)
    if payload is None:
        raise credentials_exception
    
    usuario: str = payload.get("sub")
    if usuario is None:
        raise credentials_exception
    
    # Buscar usuário no banco
    user = funcionario_repository.get_by_usuario(db, usuario=usuario)
    if user is None:
        raise credentials_exception
        
    return user


def require_roles(allowed_roles: List[str]):
    """
    Dependency factory para verificar se o usuário tem uma das roles permitidas
    """
    def role_checker(current_user: Funcionario = Depends(get_current_user)) -> Funcionario:
        if current_user.tipo not in allowed_roles:
            raise HTTPException(
                status_code=status.HTTP_403_FORBIDDEN,
                detail=f"Acesso negado. Roles necessárias: {', '.join(allowed_roles)}"
            )
        return current_user
    
    return role_checker


def require_admin(current_user: Funcionario = Depends(get_current_user)) -> Funcionario:
    """
    Dependency para verificar se o usuário é administrador
    """
    if current_user.tipo != "administrador":
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Apenas administradores podem realizar esta ação."
        )
    return current_user


def require_medico(current_user: Funcionario = Depends(get_current_user)) -> Funcionario:
    """
    Dependency para verificar se o usuário é médico
    """
    if current_user.tipo != "medico":
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Apenas médicos podem realizar esta ação."
        )
    return current_user


def require_enfermeiro(current_user: Funcionario = Depends(get_current_user)) -> Funcionario:
    """
    Dependency para verificar se o usuário é enfermeiro
    """
    if current_user.tipo != "enfermeiro":
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Apenas enfermeiros podem realizar esta ação."
        )
    return current_user


def require_atendente(current_user: Funcionario = Depends(get_current_user)) -> Funcionario:
    """
    Dependency para verificar se o usuário é atendente
    """
    if current_user.tipo != "atendente":
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Apenas atendentes podem realizar esta ação."
        )
    return current_user


def require_medico_or_atendente(current_user: Funcionario = Depends(get_current_user)) -> Funcionario:
    """
    Dependency para verificar se o usuário é médico ou atendente
    """
    if current_user.tipo not in ["medico", "atendente"]:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Apenas médicos e atendentes podem realizar esta ação."
        )
    return current_user


def require_admin_or_atendente(current_user: Funcionario = Depends(get_current_user)) -> Funcionario:
    """
    Dependency para verificar se o usuário é administrador ou atendente
    """
    if current_user.tipo not in ["administrador", "atendente"]:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Apenas administradores e atendentes podem realizar esta ação."
        )
    return current_user


def require_atendente_or_admin(current_user: Funcionario = Depends(get_current_user)) -> Funcionario:
    """
    Dependency para verificar se o usuário é atendente ou admin
    """
    if current_user.tipo not in ["atendente", "administrador"]:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Apenas atendentes e administradores podem realizar esta ação."
        )
    return current_user


def require_enfermeiro_or_medico(current_user: Funcionario = Depends(get_current_user)) -> Funcionario:
    """
    Dependency para verificar se o usuário é enfermeiro ou médico
    """
    if current_user.tipo not in ["enfermeiro", "medico"]:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Apenas enfermeiros e médicos podem realizar esta ação."
        )
    return current_user


def require_admin_or_enfermeiro(current_user: Funcionario = Depends(get_current_user)) -> Funcionario:
    """
    Dependency para verificar se o usuário é administrador ou enfermeiro
    """
    if current_user.tipo not in ["administrador", "enfermeiro"]:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="Acesso negado. Apenas administradores e enfermeiros podem realizar esta ação."
        )
    return current_user


def get_optional_user(
    credentials: Optional[HTTPAuthorizationCredentials] = Depends(security),
    db: Session = Depends(get_db)
) -> Optional[Funcionario]:
    """
    Dependency para obter o usuário atual opcionalmente (não obrigatório)
    """
    if not credentials:
        return None
    
    payload = verify_token(credentials.credentials)
    if payload is None:
        return None
    
    usuario = payload.get("sub")
    if usuario is None:
        return None
    
    return funcionario_repository.get_by_usuario(db, usuario=usuario) 