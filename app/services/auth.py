"""
Serviços de autenticação
"""

from datetime import datetime, timedelta
from sqlalchemy.orm import Session

from app.repositories.funcionario import funcionario_repository
from app.utils.security import verify_password, create_access_token
from app.schemas.auth import LoginRequest, TokenResponse, UserInfo
from app.core.config import settings


class AuthService:
    """Serviço de autenticação"""
    
    def authenticate_user(self, db: Session, login_data: LoginRequest):
        """Autenticar usuário"""
        funcionario = funcionario_repository.get_by_usuario(db, login_data.usuario)
        
        if not funcionario:
            return None
            
        if not verify_password(login_data.senha, funcionario.senha_hash):
            return None
            
        return funcionario
    
    def login(self, db: Session, login_data: LoginRequest) -> TokenResponse:
        """Realizar login e retornar token"""
        funcionario = self.authenticate_user(db, login_data)
        
        if not funcionario:
            return None
        
        # Criar token de acesso
        access_token = create_access_token(
            data={
                "sub": funcionario.usuario,
                "funcionario_id": funcionario.id,
                "tipo_funcionario": funcionario.tipo
            }
        )
        
        return TokenResponse(
            access_token=access_token,
            token_type="bearer",
            expires_in=settings.access_token_expire_minutes * 60
        )
    
    def get_user_info(self, funcionario) -> UserInfo:
        """Obter informações do usuário"""
        return UserInfo(
            id=funcionario.id,
            nome=funcionario.nome,
            usuario=funcionario.usuario,
            tipo=funcionario.tipo,
            email=funcionario.email
        )


# Instância global do serviço
auth_service = AuthService() 