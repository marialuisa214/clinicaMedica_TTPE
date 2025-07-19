"""
Endpoints de autenticação
"""

from fastapi import APIRouter, Depends, HTTPException, status
from sqlalchemy.orm import Session

from app.core.database import get_db
from app.services.auth import auth_service
from app.schemas.auth import LoginRequest, TokenResponse, UserInfo
from app.api.dependencies import get_current_user
from app.models.funcionario import Funcionario

router = APIRouter()


@router.post("/login", response_model=TokenResponse)
def login(
    login_data: LoginRequest,
    db: Session = Depends(get_db)
):
    """
    Autenticar usuário e retornar token JWT
    """
    token = auth_service.login(db, login_data)
    
    if not token:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Usuário ou senha incorretos",
            headers={"WWW-Authenticate": "Bearer"},
        )
    
    return token


@router.get("/me", response_model=UserInfo)
def get_current_user_info(
    current_user: Funcionario = Depends(get_current_user)
):
    """
    Obter informações do usuário atual
    """
    return auth_service.get_user_info(current_user)


@router.post("/logout")
def logout():
    """
    Logout (invalidação do token seria feita no cliente)
    """
    return {"message": "Logout realizado com sucesso"} 