"""
Modelos para funcionários e especialidades
"""

from sqlalchemy import Column, Integer, String, Text
from sqlalchemy.orm import relationship
from sqlalchemy.sql import func
from datetime import datetime

from app.core.database import Base


class Funcionario(Base):
    """
    Modelo base para funcionários com herança polimórfica
    """
    __tablename__ = "funcionarios"
    
    id = Column(Integer, primary_key=True, index=True)
    nome = Column(String(100), nullable=False)
    usuario = Column(String(50), unique=True, nullable=False, index=True)
    senha_hash = Column(String(100), nullable=False)
    email = Column(String(100), unique=True, nullable=False)
    telefone = Column(String(15), nullable=True)
    
    # Tipo de funcionário para polimorfismo
    tipo = Column(String(20), nullable=False)
    
    # Campos específicos por tipo (nullable para permitir polimorfismo)
    crm = Column(String(20), nullable=True)          # Médico
    especialidade = Column(String(100), nullable=True)  # Médico
    coren = Column(String(20), nullable=True)        # Enfermeiro
    crf = Column(String(20), nullable=True)          # Farmacêutico
    setor = Column(String(100), nullable=True)       # Atendente/Admin
    
    # Metadados
    created_at = Column(String(19), default=lambda: datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
    updated_at = Column(String(19), default=lambda: datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
    
    # Configuração polimórfica
    __mapper_args__ = {
        'polymorphic_identity': 'funcionario',
        'polymorphic_on': tipo
    }
    
    # Relacionamentos
    consultas = relationship("Consulta", back_populates="medico")
    agenda = relationship("AgendaMedico", back_populates="medico")
    
    # Relacionamentos com Exames
    exames_medico = relationship(
        "Exame", 
        foreign_keys="Exame.medico_responsavel_id",
        back_populates="medico_responsavel"
    )
    exames_enfermeiro = relationship(
        "Exame",
        foreign_keys="Exame.enfermeiro_responsavel_id", 
        back_populates="enfermeiro_responsavel"
    )
    
    # Relacionamentos com Atendimentos
    atendimentos_enfermeiro = relationship(
        "Atendimento",
        foreign_keys="Atendimento.enfermeiro_responsavel_id",
        back_populates="enfermeiro_responsavel"
    )
    atendimentos_supervisionados = relationship(
        "Atendimento",
        foreign_keys="Atendimento.medico_supervisor_id",
        back_populates="medico_supervisor"
    )


class Administrador(Funcionario):
    """
    Classe específica para administradores
    """
    __mapper_args__ = {
        'polymorphic_identity': 'administrador'
    }


class Medico(Funcionario):
    """
    Classe específica para médicos
    """
    __mapper_args__ = {
        'polymorphic_identity': 'medico'
    }


class Enfermeiro(Funcionario):
    """
    Classe específica para enfermeiros
    """
    __mapper_args__ = {
        'polymorphic_identity': 'enfermeiro'
    }


class Atendente(Funcionario):
    """
    Classe específica para atendentes
    """
    __mapper_args__ = {
        'polymorphic_identity': 'atendente'
    }


class Farmaceutico(Funcionario):
    """
    Classe específica para farmacêuticos
    """
    __mapper_args__ = {
        'polymorphic_identity': 'farmaceutico'
    } 