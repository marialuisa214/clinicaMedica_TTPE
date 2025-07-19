"""
Testes para módulo de Autenticação
"""

import pytest
from datetime import timedelta
from app.utils.security import (
    create_access_token, verify_password, get_password_hash, verify_token
)
from app.schemas.auth import LoginRequest, TokenData


class TestSecurity:
    """Classe de testes para funções de segurança"""
    
    def test_password_hashing(self):
        """Testar hash e verificação de senha"""
        password = "minha_senha_secreta"
        
        # Gerar hash
        hashed = get_password_hash(password)
        
        # Verificar que o hash é diferente da senha original
        assert hashed != password
        
        # Verificar que a verificação funciona
        assert verify_password(password, hashed) is True
        
        # Verificar que senha errada falha
        assert verify_password("senha_errada", hashed) is False
    
    def test_jwt_token_creation_and_verification(self):
        """Testar criação e verificação de tokens JWT"""
        data = {
            "sub": "usuario_teste",
            "funcionario_id": 1,
            "tipo_funcionario": "medico"
        }
        
        # Criar token
        token = create_access_token(data)
        assert isinstance(token, str)
        assert len(token) > 0
        
        # Verificar token
        payload = verify_token(token)
        assert payload is not None
        assert payload["sub"] == "usuario_teste"
        assert payload["funcionario_id"] == 1
        assert payload["tipo_funcionario"] == "medico"
    
    def test_jwt_token_expiration(self):
        """Testar expiração de token"""
        data = {"sub": "usuario_teste"}
        
        # Criar token com expiração muito curta (negativa para simular expirado)
        expired_token = create_access_token(data, expires_delta=timedelta(seconds=-1))
        
        # Verificar que token expirado é inválido
        payload = verify_token(expired_token)
        assert payload is None
    
    def test_invalid_token(self):
        """Testar token inválido"""
        invalid_token = "token_completamente_invalido"
        
        payload = verify_token(invalid_token)
        assert payload is None


class TestAuthSchemas:
    """Classe de testes para schemas de autenticação"""
    
    def test_login_request_valid(self):
        """Testar schema de requisição de login válido"""
        login_data = {
            "usuario": "medico01",
            "senha": "senha123"
        }
        
        login_request = LoginRequest(**login_data)
        assert login_request.usuario == "medico01"
        assert login_request.senha == "senha123"
    
    def test_login_request_invalid_short_username(self):
        """Testar validação de usuário muito curto"""
        login_data = {
            "usuario": "ab",  # Muito curto (mínimo 3)
            "senha": "senha123"
        }
        
        with pytest.raises(ValueError):
            LoginRequest(**login_data)
    
    def test_login_request_invalid_short_password(self):
        """Testar validação de senha muito curta"""
        login_data = {
            "usuario": "medico01",
            "senha": "123"  # Muito curta (mínimo 6)
        }
        
        with pytest.raises(ValueError):
            LoginRequest(**login_data)
    
    def test_token_data_schema(self):
        """Testar schema de dados do token"""
        token_data = TokenData(
            usuario="medico01",
            funcionario_id=1,
            tipo_funcionario="medico"
        )
        
        assert token_data.usuario == "medico01"
        assert token_data.funcionario_id == 1
        assert token_data.tipo_funcionario == "medico"
    
    def test_token_data_optional_fields(self):
        """Testar campos opcionais do schema TokenData"""
        # Todos os campos são opcionais
        token_data = TokenData()
        
        assert token_data.usuario is None
        assert token_data.funcionario_id is None
        assert token_data.tipo_funcionario is None


@pytest.fixture
def sample_user_data():
    """Fixture com dados de usuário para testes"""
    return {
        "usuario": "enfermeiro01",
        "senha": "senha_segura_123",
        "funcionario_id": 2,
        "tipo": "enfermeiro"
    }


def test_complete_auth_flow(sample_user_data):
    """Testar fluxo completo de autenticação"""
    # 1. Hash da senha
    hashed_password = get_password_hash(sample_user_data["senha"])
    
    # 2. Criar dados para o token
    token_data = {
        "sub": sample_user_data["usuario"],
        "funcionario_id": sample_user_data["funcionario_id"],
        "tipo_funcionario": sample_user_data["tipo"]
    }
    
    # 3. Criar token
    token = create_access_token(token_data)
    
    # 4. Verificar token
    payload = verify_token(token)
    
    # 5. Validações
    assert payload["sub"] == sample_user_data["usuario"]
    assert payload["funcionario_id"] == sample_user_data["funcionario_id"]
    assert payload["tipo_funcionario"] == sample_user_data["tipo"]
    
    # 6. Verificar senha
    assert verify_password(sample_user_data["senha"], hashed_password) 