"""
Modelos para pacientes
"""

from sqlalchemy import Column, Integer, String, Date, Text
from sqlalchemy.orm import relationship
from sqlalchemy.sql import func
from datetime import datetime

from app.core.database import Base


class Paciente(Base):
    """
    Modelo para pacientes
    """
    __tablename__ = "pacientes"
    
    id = Column(Integer, primary_key=True, index=True)
    nome = Column(String(100), nullable=False)
    rg = Column(String(20), unique=True, nullable=False)
    cpf = Column(String(14), unique=True, nullable=False, index=True)
    sexo = Column(String(1), nullable=False)  # M ou F
    data_nascimento = Column(Date, nullable=False)
    telefone = Column(String(15), nullable=True)
    email = Column(String(100), nullable=True)
    cidade_estado = Column(String(100), nullable=True)
    endereco = Column(Text, nullable=True)
    patologia = Column(Text, nullable=True)
    
    # Metadados
    created_at = Column(String(19), default=lambda: datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
    updated_at = Column(String(19), default=lambda: datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
    
    # Relacionamentos
    consultas = relationship("Consulta", back_populates="paciente")
    exames = relationship("Exame", back_populates="paciente", cascade="all, delete-orphan")
    atendimentos = relationship("Atendimento", back_populates="paciente", cascade="all, delete-orphan") 