"""
Testes para módulo de Pacientes
"""

import pytest
from fastapi.testclient import TestClient
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from datetime import date

from app.main import app
from app.core.database import get_db, Base
from app.schemas.paciente import PacienteCreate
from app.models.pessoa import SexoEnum

# Banco de dados em memória para testes
SQLALCHEMY_DATABASE_URL = "sqlite:///./test.db"
engine = create_engine(SQLALCHEMY_DATABASE_URL, connect_args={"check_same_thread": False})
TestingSessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

# Criar tabelas de teste
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


class TestPacientes:
    """Classe de testes para Pacientes"""
    
    def test_create_paciente_valid_data(self):
        """Testar criação de paciente com dados válidos"""
        # Primeiro, precisamos estar autenticados
        # Por simplicidade, vamos mockar a autenticação nos testes
        
        paciente_data = {
            "nome": "João Silva",
            "rg": "12.345.678-9",
            "cpf": "123.456.789-00",
            "sexo": "M",
            "data_nascimento": "1990-01-01",
            "telefone": "(11) 99999-9999",
            "email": "joao@example.com",
            "cidade_estado": "São Paulo-SP",
            "endereco": "Rua A, 123",
            "patologia": "Nenhuma"
        }
        
        # Note: Em testes reais, seria necessário implementar autenticação mock
        # response = client.post("/api/v1/pacientes/", json=paciente_data)
        # assert response.status_code == 201
    
    def test_paciente_schema_validation(self):
        """Testar validação do schema de paciente"""
        
        # Dados válidos
        valid_data = {
            "nome": "Maria Santos",
            "rg": "98.765.432-1",
            "cpf": "987.654.321-00",
            "sexo": SexoEnum.FEMININO,
            "data_nascimento": date(1985, 5, 15),
            "telefone": "(11) 88888-8888",
            "email": "maria@example.com",
            "cidade_estado": "Rio de Janeiro-RJ",
            "endereco": "Avenida B, 456"
        }
        
        paciente_schema = PacienteCreate(**valid_data)
        assert paciente_schema.nome == "Maria Santos"
        assert paciente_schema.cpf == "987.654.321-00"
        assert paciente_schema.sexo == SexoEnum.FEMININO
    
    def test_paciente_schema_invalid_cpf(self):
        """Testar validação de CPF inválido"""
        
        invalid_data = {
            "nome": "Pedro Costa",
            "rg": "11.111.111-1",
            "cpf": "invalid-cpf",  # CPF inválido
            "sexo": SexoEnum.MASCULINO,
            "data_nascimento": date(1995, 3, 20),
        }
        
        with pytest.raises(ValueError):
            PacienteCreate(**invalid_data)
    
    def test_paciente_schema_required_fields(self):
        """Testar campos obrigatórios do schema"""
        
        # Dados incompletos (faltando campos obrigatórios)
        incomplete_data = {
            "nome": "Ana Oliveira",
            # faltando rg, cpf, sexo, data_nascimento
        }
        
        with pytest.raises(ValueError):
            PacienteCreate(**incomplete_data)


@pytest.fixture
def sample_paciente_data():
    """Fixture com dados de exemplo para paciente"""
    return {
        "nome": "Teste Silva",
        "rg": "99.999.999-9",
        "cpf": "999.999.999-99",
        "sexo": "M",
        "data_nascimento": "1992-12-25",
        "telefone": "(11) 77777-7777",
        "email": "teste@example.com",
        "cidade_estado": "Brasília-DF",
        "endereco": "SQN 123, Bloco A",
        "patologia": "Hipertensão"
    }


def test_paciente_creation_business_logic(sample_paciente_data):
    """Testar lógica de negócio na criação de paciente"""
    paciente = PacienteCreate(**sample_paciente_data)
    
    # Verificar se os dados foram processados corretamente
    assert paciente.nome == "Teste Silva"
    assert paciente.cpf == "999.999.999-99"
    assert paciente.patologia == "Hipertensão" 