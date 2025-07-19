"""
Modelos para consultas e agenda médica
"""

from sqlalchemy import Column, Integer, String, DateTime, Text, ForeignKey, Enum, Boolean
from sqlalchemy.orm import relationship
from sqlalchemy.sql import func
import enum
from datetime import datetime

from app.core.database import Base


class StatusConsulta(str, enum.Enum):
    AGENDADA = "agendada"
    CONFIRMADA = "confirmada" 
    EM_ANDAMENTO = "em_andamento"
    FINALIZADA = "finalizada"
    CANCELADA = "cancelada"


class TipoConsulta(str, enum.Enum):
    CONSULTA_NORMAL = "consulta_normal"
    RETORNO = "retorno"
    EMERGENCIA = "emergencia"
    EXAME = "exame"


class Consulta(Base):
    """
    Modelo para consultas médicas
    """
    __tablename__ = "consultas"
    
    id = Column(Integer, primary_key=True, index=True)
    
    # Relacionamentos
    paciente_id = Column(Integer, ForeignKey("pacientes.id"), nullable=False)
    medico_id = Column(Integer, ForeignKey("funcionarios.id"), nullable=False)
    atendente_id = Column(Integer, ForeignKey("funcionarios.id"), nullable=True)  # Quem agendou
    
    # Dados da consulta
    data_hora = Column(DateTime, nullable=False)
    tipo = Column(Enum(TipoConsulta), default=TipoConsulta.CONSULTA_NORMAL)
    status = Column(Enum(StatusConsulta), default=StatusConsulta.AGENDADA)
    
    # Informações clínicas
    motivo = Column(Text, nullable=True)
    observacoes = Column(Text, nullable=True)
    diagnostico = Column(Text, nullable=True)
    prescricao = Column(Text, nullable=True)
    
    # Metadados
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
    
    # Relacionamentos
    paciente = relationship("Paciente", back_populates="consultas")
    medico = relationship("Funcionario", foreign_keys=[medico_id], back_populates="consultas_medico")
    atendente = relationship("Funcionario", foreign_keys=[atendente_id], back_populates="consultas_agendadas")


class AgendaMedico(Base):
    """
    Modelo para agenda dos médicos - disponibilidade
    """
    __tablename__ = "agenda_medicos"
    
    id = Column(Integer, primary_key=True, index=True)
    
    # Relacionamentos
    medico_id = Column(Integer, ForeignKey("funcionarios.id"), nullable=False)
    
    # Horários
    data = Column(DateTime, nullable=False)
    hora_inicio = Column(String(5), nullable=False)  # HH:MM
    hora_fim = Column(String(5), nullable=False)     # HH:MM
    
    # Disponibilidade
    disponivel = Column(Boolean, default=True)
    motivo_indisponibilidade = Column(String(200), nullable=True)
    
    # Metadados
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
    
    # Relacionamentos
    medico = relationship("Funcionario", back_populates="agenda") 