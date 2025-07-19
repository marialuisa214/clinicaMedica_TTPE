"""
Modelo da entidade Prontuário
"""

from sqlalchemy import Column, String, Integer, Boolean, Float, ForeignKey
from sqlalchemy.orm import relationship
from .base import BaseModel


class Prontuario(BaseModel):
    """Modelo para Prontuário"""
    
    __tablename__ = "prontuarios"
    
    acompanhamento = Column(String(2000))  # Descrição médica
    peso = Column(Float)
    altura = Column(Float)
    validado = Column(Boolean, default=False)
    
    # Chave estrangeira
    paciente_id = Column(Integer, ForeignKey('pacientes.id'), nullable=False, unique=True)
    
    # Relacionamentos
    paciente = relationship("Paciente", back_populates="prontuario") 