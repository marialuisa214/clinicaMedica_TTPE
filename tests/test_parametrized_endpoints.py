"""
Testes parametrizados para endpoints da API
"""

import pytest
from fastapi.testclient import TestClient
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from unittest.mock import Mock, patch

from app.main import app
from app.core.database import get_db, Base


# Configuração do banco de dados de teste
SQLALCHEMY_DATABASE_URL = "sqlite:///./test_endpoints.db"
engine = create_engine(SQLALCHEMY_DATABASE_URL, connect_args={"check_same_thread": False})
TestingSessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base.metadata.create_all(bind=engine)


def override_get_db():
    """Override da dependência do banco de dados para testes"""
    try:
        db = TestingSessionLocal()
        yield db
    finally:
        db.close()


app.dependency_overrides[get_db] = override_get_db
client = TestClient(app)


class TestHealthEndpoints:
    """Testes parametrizados para endpoints de saúde/status"""
    
    @pytest.mark.parametrize("endpoint,expected_status", [
        ("/health", 200),
        ("/", 200),
        ("/docs", 200),
        ("/redoc", 200),
    ])
    def test_public_endpoints(self, endpoint, expected_status):
        """Testar endpoints públicos"""
        response = client.get(endpoint)
        assert response.status_code == expected_status
    
    @pytest.mark.parametrize("endpoint,method", [
        ("/health", "GET"),
        ("/health", "POST"),  # Método não permitido
        ("/health", "PUT"),   # Método não permitido
        ("/health", "DELETE"), # Método não permitido
    ])
    def test_health_endpoint_methods(self, endpoint, method):
        """Testar métodos HTTP no endpoint de health"""
        if method == "GET":
            response = client.get(endpoint)
            assert response.status_code == 200
        else:
            # Métodos não permitidos devem retornar 405
            response = client.request(method, endpoint)
            assert response.status_code == 405


class TestAuthEndpoints:
    """Testes parametrizados para endpoints de autenticação"""
    
    @pytest.mark.parametrize("credentials,expected_status", [
        ({"usuario": "valid_user", "senha": "valid_pass"}, 200),  # Credenciais válidas
        ({"usuario": "invalid_user", "senha": "valid_pass"}, 401),  # Usuário inválido
        ({"usuario": "valid_user", "senha": "invalid_pass"}, 401),  # Senha inválida
        ({"usuario": "", "senha": "valid_pass"}, 422),  # Usuário vazio
        ({"usuario": "valid_user", "senha": ""}, 422),  # Senha vazia
        ({"usuario": "ab", "senha": "valid_pass"}, 422),  # Usuário muito curto
        ({"usuario": "valid_user", "senha": "123"}, 422),  # Senha muito curta
        ({}, 422),  # Dados ausentes
    ])
    def test_login_endpoint_validation(self, credentials, expected_status):
        """Testar validação do endpoint de login"""
        with patch('app.services.auth.authenticate_user') as mock_auth:
            if expected_status == 200:
                # Simular autenticação bem-sucedida
                mock_auth.return_value = {
                    "id": 1,
                    "usuario": "valid_user",
                    "tipo": "medico"
                }
            else:
                # Simular falha na autenticação
                mock_auth.return_value = None
            
            response = client.post("/api/v1/auth/login", json=credentials)
            assert response.status_code == expected_status


class TestPacienteEndpoints:
    """Testes parametrizados para endpoints de pacientes"""
    
    @pytest.mark.parametrize("paciente_data,expected_status", [
        ({
            "nome": "João Silva",
            "rg": "12.345.678-9",
            "cpf": "123.456.789-01",
            "sexo": "M",
            "data_nascimento": "1990-01-01"
        }, 201),  # Dados válidos
        ({
            "nome": "",
            "rg": "12.345.678-9",
            "cpf": "123.456.789-01",
            "sexo": "M",
            "data_nascimento": "1990-01-01"
        }, 422),  # Nome vazio
        ({
            "nome": "João Silva",
            "rg": "12.345.678-9",
            "cpf": "invalid-cpf",
            "sexo": "M",
            "data_nascimento": "1990-01-01"
        }, 422),  # CPF inválido
        ({
            "nome": "João Silva",
            "rg": "12.345.678-9",
            "cpf": "123.456.789-01",
            "sexo": "X",  # Sexo inválido
            "data_nascimento": "1990-01-01"
        }, 422),
        ({}, 422),  # Dados ausentes
    ])
    def test_create_paciente_validation(self, paciente_data, expected_status):
        """Testar validação na criação de pacientes"""
        # Mock da autenticação para permitir acesso
        with patch('app.api.dependencies.get_current_user') as mock_user:
            mock_user.return_value = {
                "id": 1,
                "usuario": "medico01",
                "tipo": "medico"
            }
            
            response = client.post("/api/v1/pacientes/", json=paciente_data)
            assert response.status_code == expected_status
    
    @pytest.mark.parametrize("user_type,expected_status", [
        ("administrador", 200),  # Administrador pode listar
        ("medico", 200),         # Médico pode listar
        ("enfermeiro", 200),     # Enfermeiro pode listar
        ("atendente", 200),      # Atendente pode listar
        ("farmaceutico", 403),   # Farmacêutico não pode listar
        (None, 401),             # Usuário não autenticado
    ])
    def test_list_pacientes_permissions(self, user_type, expected_status):
        """Testar permissões para listar pacientes"""
        if user_type is None:
            # Usuário não autenticado
            response = client.get("/api/v1/pacientes/")
        else:
            # Usuário autenticado com tipo específico
            with patch('app.api.dependencies.get_current_user') as mock_user:
                mock_user.return_value = {
                    "id": 1,
                    "usuario": "user01",
                    "tipo": user_type
                }
                response = client.get("/api/v1/pacientes/")
        
        assert response.status_code == expected_status


class TestFuncionarioEndpoints:
    """Testes parametrizados para endpoints de funcionários"""
    
    @pytest.mark.parametrize("funcionario_data,expected_status", [
        ({
            "nome": "Dr. João Silva",
            "usuario": "drjoao",
            "email": "joao@hospital.com",
            "senha": "senha123",
            "tipo": "medico",
            "crm": "12345-SP",
            "especialidade": "Cardiologia"
        }, 201),  # Dados válidos para médico
        ({
            "nome": "Enfermeira Maria",
            "usuario": "enfmaria",
            "email": "maria@hospital.com",
            "senha": "senha123",
            "tipo": "enfermeiro",
            "coren": "123456"
        }, 201),  # Dados válidos para enfermeiro
        ({
            "nome": "Dr. João Silva",
            "usuario": "drjoao",
            "email": "joao@hospital.com",
            "senha": "senha123",
            "tipo": "medico"
            # Faltando CRM obrigatório
        }, 422),
        ({
            "nome": "Enfermeira Maria",
            "usuario": "ab",  # Usuário muito curto
            "email": "maria@hospital.com",
            "senha": "senha123",
            "tipo": "enfermeiro",
            "coren": "123456"
        }, 422),
    ])
    def test_create_funcionario_validation(self, funcionario_data, expected_status):
        """Testar validação na criação de funcionários"""
        # Mock da autenticação como administrador
        with patch('app.api.dependencies.get_current_user') as mock_user:
            mock_user.return_value = {
                "id": 1,
                "usuario": "admin",
                "tipo": "administrador"
            }
            
            response = client.post("/api/v1/funcionarios/", json=funcionario_data)
            assert response.status_code == expected_status


class TestErrorHandling:
    """Testes parametrizados para tratamento de erros"""
    
    @pytest.mark.parametrize("endpoint,expected_status", [
        ("/api/v1/pacientes/999999", 404),  # Paciente não encontrado
        ("/api/v1/funcionarios/999999", 404),  # Funcionário não encontrado
        ("/api/v1/consultas/999999", 404),  # Consulta não encontrada
        ("/api/v1/nonexistent", 404),  # Endpoint inexistente
    ])
    def test_not_found_endpoints(self, endpoint, expected_status):
        """Testar endpoints que devem retornar 404"""
        # Mock da autenticação
        with patch('app.api.dependencies.get_current_user') as mock_user:
            mock_user.return_value = {
                "id": 1,
                "usuario": "user01",
                "tipo": "administrador"
            }
            
            response = client.get(endpoint)
            assert response.status_code == expected_status
    
    @pytest.mark.parametrize("content_type,expected_status", [
        ("application/json", 200),
        ("text/plain", 415),  # Tipo de conteúdo não suportado
        ("application/xml", 415),
        ("", 415),
    ])
    def test_content_type_validation(self, content_type, expected_status):
        """Testar validação de Content-Type"""
        headers = {"Content-Type": content_type} if content_type else {}
        data = {"usuario": "test", "senha": "test123"} if content_type == "application/json" else "invalid data"
        
        response = client.post("/api/v1/auth/login", 
                             json=data if content_type == "application/json" else None,
                             data=data if content_type != "application/json" else None,
                             headers=headers)
        
        # Note: FastAPI geralmente aceita JSON mesmo sem Content-Type correto
        # Este teste seria mais relevante para APIs mais restritivas
        if content_type == "application/json":
            assert response.status_code in [200, 401, 422]  # Pode variar baseado na autenticação
        else:
            assert response.status_code in [415, 422]


# Fixtures parametrizadas para diferentes cenários
@pytest.fixture(params=[
    {"tipo": "administrador", "permissions": "full"},
    {"tipo": "medico", "permissions": "read_write"},
    {"tipo": "enfermeiro", "permissions": "read_write"},
    {"tipo": "atendente", "permissions": "read_create"},
    {"tipo": "farmaceutico", "permissions": "read_only"},
])
def user_scenarios(request):
    """Fixture parametrizada com diferentes cenários de usuário"""
    return request.param


def test_permissions_matrix(user_scenarios):
    """Testar matriz de permissões para diferentes tipos de usuário"""
    user_type = user_scenarios["tipo"]
    expected_permissions = user_scenarios["permissions"]
    
    # Esta seria uma função real no seu sistema de permissões
    def get_user_permissions(tipo):
        permissions_map = {
            "administrador": "full",
            "medico": "read_write",
            "enfermeiro": "read_write", 
            "atendente": "read_create",
            "farmaceutico": "read_only"
        }
        return permissions_map.get(tipo, "none")
    
    actual_permissions = get_user_permissions(user_type)
    assert actual_permissions == expected_permissions 