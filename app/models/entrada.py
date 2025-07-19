"""
Modelo da entidade Entrada
"""

from datetime import datetime
from sqlalchemy import Column, String, Integer, DateTime, ForeignKey
from sqlalchemy.orm import relationship
from .base import BaseModel


class Entrada(BaseModel):
    """Modelo para Entrada (registro de chegada do paciente)"""
    
    __tablename__ = "entradas"
    
    data_entrada = Column(DateTime, default=datetime.now)
    situacao_paciente = Column(String(500))  # Descrição da situação do paciente
    
    # Chaves estrangeiras
    paciente_id = Column(Integer, ForeignKey('pacientes.id'), nullable=False)
    atendente_id = Column(Integer, ForeignKey('funcionarios.id'), nullable=False)
    
    # Relacionamentos
    paciente = relationship("Paciente", back_populates="entradas")
    atendente = relationship("Funcionario", foreign_keys=[atendente_id])
    triagem = relationship("Triagem", back_populates="entrada", uselist=False) 