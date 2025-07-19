"""
Testes parametrizados para validação de dados
"""

import pytest
from datetime import date, datetime
from pydantic import ValidationError

from app.schemas.paciente import PacienteCreate
from app.schemas.funcionario import FuncionarioCreate
from app.schemas.auth import LoginRequest
from app.models.pessoa import SexoEnum


class TestPacienteValidationParametrized:
    """Testes parametrizados para validação de pacientes"""
    
    @pytest.mark.parametrize("nome,expected_valid", [
        ("João Silva", True),
        ("Maria da Silva Santos", True),
        ("Ana", True),
        ("", False),  # Nome vazio
        ("J", False),  # Nome muito curto
        ("A" * 256, False),  # Nome muito longo
    ])
    def test_nome_validation(self, nome, expected_valid):
        """Testar validação de nome com diferentes valores"""
        data = {
            "nome": nome,
            "rg": "12.345.678-9",
            "cpf": "123.456.789-01",
            "sexo": SexoEnum.MASCULINO,
            "data_nascimento": date(1990, 1, 1)
        }
        
        if expected_valid:
            paciente = PacienteCreate(**data)
            assert paciente.nome == nome
        else:
            with pytest.raises((ValidationError, ValueError)):
                PacienteCreate(**data)
    
    @pytest.mark.parametrize("cpf,expected_valid", [
        ("123.456.789-01", True),
        ("abc.def.ghi-jk", False),  # Formato inválido
        ("", False),  # CPF vazio
        ("123", False),  # CPF muito curto
    ])
    def test_cpf_validation(self, cpf, expected_valid):
        """Testar validação de CPF com diferentes valores"""
        data = {
            "nome": "João Silva",
            "rg": "12.345.678-9",
            "cpf": cpf,
            "sexo": SexoEnum.MASCULINO,
            "data_nascimento": date(1990, 1, 1)
        }
        
        if expected_valid:
            paciente = PacienteCreate(**data)
            assert paciente.cpf == cpf
        else:
            with pytest.raises((ValidationError, ValueError)):
                PacienteCreate(**data)
    
    @pytest.mark.parametrize("email,expected_valid", [
        ("joao@example.com", True),
        ("maria.silva@hospital.org.br", True),
        ("test+tag@domain.co.uk", True),
        ("invalid-email", False),
        ("@domain.com", False),
        ("user@", False),
        (None, True),  # Email é opcional
    ])
    def test_email_validation(self, email, expected_valid):
        """Testar validação de email com diferentes valores"""
        data = {
            "nome": "João Silva",
            "rg": "12.345.678-9",
            "cpf": "123.456.789-01",
            "sexo": SexoEnum.MASCULINO,
            "data_nascimento": date(1990, 1, 1),
            "email": email
        }
        
        if expected_valid:
            paciente = PacienteCreate(**data)
            assert paciente.email == email
        else:
            with pytest.raises((ValidationError, ValueError)):
                PacienteCreate(**data)
    
    @pytest.mark.parametrize("data_nascimento,expected_valid", [
        (date(1990, 1, 1), True),
        (date(2000, 12, 31), True),
        (date(1920, 5, 15), True),
        (date(2024, 1, 1), True),  # Data atual
    ])
    def test_data_nascimento_validation(self, data_nascimento, expected_valid):
        """Testar validação de data de nascimento"""
        data = {
            "nome": "João Silva",
            "rg": "12.345.678-9",
            "cpf": "123.456.789-01",
            "sexo": SexoEnum.MASCULINO,
            "data_nascimento": data_nascimento
        }
        
        if expected_valid:
            paciente = PacienteCreate(**data)
            assert paciente.data_nascimento == data_nascimento
        else:
            with pytest.raises((ValidationError, ValueError)):
                PacienteCreate(**data)


class TestFuncionarioValidationParametrized:
    """Testes parametrizados para validação de funcionários"""
    
    @pytest.mark.parametrize("tipo,crm,coren,crf,expected_valid", [
        ("medico", "12345-SP", None, None, True),
        ("enfermeiro", None, "123456", None, True),
        ("farmaceutico", None, None, "12345", True),
        ("atendente", None, None, None, True),
        ("administrador", None, None, None, True),
        ("medico", None, None, None, False),  # Médico sem CRM
        ("enfermeiro", None, None, None, False),  # Enfermeiro sem COREN
        ("farmaceutico", None, None, None, False),  # Farmacêutico sem CRF
        ("tipo_invalido", None, None, None, False),  # Tipo inválido
    ])
    def test_funcionario_tipo_and_registro(self, tipo, crm, coren, crf, expected_valid):
        """Testar validação de tipo de funcionário e registros profissionais"""
        data = {
            "nome": "João Silva",
            "usuario": "joao123",
            "email": "joao@hospital.com",
            "senha": "senha123",
            "tipo": tipo,
            "crm": crm,
            "coren": coren,
            "crf": crf
        }
        
        if expected_valid:
            funcionario = FuncionarioCreate(**data)
            assert funcionario.tipo == tipo
            assert funcionario.crm == crm
            assert funcionario.coren == coren
            assert funcionario.crf == crf
        else:
            with pytest.raises((ValidationError, ValueError)):
                FuncionarioCreate(**data)
    
    @pytest.mark.parametrize("usuario,expected_valid", [
        ("joao123", True),
        ("maria_silva", True),
        ("admin01", True),
        ("user", True),
    ])
    def test_usuario_validation(self, usuario, expected_valid):
        """Testar validação de nome de usuário"""
        data = {
            "nome": "João Silva",
            "usuario": usuario,
            "email": "joao@hospital.com",
            "senha": "senha123",
            "tipo": "atendente"
        }
        
        if expected_valid:
            funcionario = FuncionarioCreate(**data)
            assert funcionario.usuario == usuario
        else:
            with pytest.raises((ValidationError, ValueError)):
                FuncionarioCreate(**data)


class TestAuthValidationParametrized:
    """Testes parametrizados para validação de autenticação"""
    
    @pytest.mark.parametrize("usuario,senha,expected_valid", [
        ("medico01", "senha123", True),
        ("enfermeiro", "password", True),
        ("admin", "123456", True),
        ("user", "longpassword123", True),
        ("ab", "senha123", False),  # Usuário muito curto
        ("medico01", "12345", False),  # Senha muito curta
        ("", "senha123", False),  # Usuário vazio
        ("medico01", "", False),  # Senha vazia
        ("a" * 51, "senha123", False),  # Usuário muito longo
    ])
    def test_login_request_validation(self, usuario, senha, expected_valid):
        """Testar validação de requisição de login"""
        data = {
            "usuario": usuario,
            "senha": senha
        }
        
        if expected_valid:
            login_request = LoginRequest(**data)
            assert login_request.usuario == usuario
            assert login_request.senha == senha
        else:
            with pytest.raises((ValidationError, ValueError)):
                LoginRequest(**data)


class TestBusinessRulesParametrized:
    """Testes parametrizados para regras de negócio"""
    
    @pytest.mark.parametrize("tipo_funcionario,permissoes_esperadas", [
        ("administrador", ["CREATE", "READ", "UPDATE", "DELETE"]),
        ("medico", ["READ", "UPDATE"]),
        ("enfermeiro", ["READ", "UPDATE"]),
        ("atendente", ["READ", "CREATE"]),
        ("farmaceutico", ["READ"]),
    ])
    def test_permissoes_por_tipo_funcionario(self, tipo_funcionario, permissoes_esperadas):
        """Testar permissões baseadas no tipo de funcionário"""
        # Este é um exemplo de como testar regras de negócio de permissões
        # Implementação real dependeria do sistema de permissões
        
        def get_permissoes(tipo):
            """Função simulada para obter permissões por tipo"""
            permissoes_map = {
                "administrador": ["CREATE", "READ", "UPDATE", "DELETE"],
                "medico": ["READ", "UPDATE"],
                "enfermeiro": ["READ", "UPDATE"],
                "atendente": ["READ", "CREATE"],
                "farmaceutico": ["READ"],
            }
            return permissoes_map.get(tipo, [])
        
        permissoes = get_permissoes(tipo_funcionario)
        assert permissoes == permissoes_esperadas
    
    @pytest.mark.parametrize("idade,categoria_paciente", [
        (0, "recém-nascido"),
        (1, "bebê"),
        (12, "criança"),
        (17, "adolescente"),
        (18, "adulto"),
        (65, "idoso"),
        (90, "idoso"),
    ])
    def test_categoria_paciente_por_idade(self, idade, categoria_paciente):
        """Testar categorização de paciente por idade"""
        def calcular_categoria_paciente(idade):
            """Função simulada para categorizar paciente por idade"""
            if idade < 1:
                return "recém-nascido"
            elif idade < 2:
                return "bebê"
            elif idade < 13:
                return "criança"
            elif idade < 18:
                return "adolescente"
            elif idade < 65:
                return "adulto"
            else:
                return "idoso"
        
        categoria = calcular_categoria_paciente(idade)
        assert categoria == categoria_paciente


# Fixtures parametrizadas
@pytest.fixture(params=[
    {"nome": "João Silva", "cpf": "123.456.789-01", "sexo": SexoEnum.MASCULINO},
    {"nome": "Maria Santos", "cpf": "987.654.321-00", "sexo": SexoEnum.FEMININO},
    {"nome": "Pedro Costa", "cpf": "456.789.123-45", "sexo": SexoEnum.MASCULINO},
])
def pacientes_validos(request):
    """Fixture parametrizada com dados válidos de pacientes"""
    data = request.param.copy()
    data.update({
        "rg": "12.345.678-9",
        "data_nascimento": date(1990, 1, 1)
    })
    return data


def test_criacao_multiplos_pacientes(pacientes_validos):
    """Testar criação de pacientes com dados variados"""
    paciente = PacienteCreate(**pacientes_validos)
    assert paciente.nome == pacientes_validos["nome"]
    assert paciente.cpf == pacientes_validos["cpf"]
    assert paciente.sexo == pacientes_validos["sexo"] 