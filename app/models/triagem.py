"""
Modelo da entidade Triagem
"""

from sqlalchemy import Column, String, Integer, ForeignKey
from sqlalchemy.orm import relationship
from .base import BaseModel
import enum


class GravidadeEnum(str, enum.Enum):
    """Enum para gravidade da triagem"""
    BAIXA = "baixa"
    MEDIA = "media"
    ALTA = "alta"
    CRITICA = "critica"


class Triagem(BaseModel):
    """Modelo para Triagem"""
    
    __tablename__ = "triagens"
    
    descricao = Column(String(1000))
    gravidade = Column(String(20))  # Enum será tratado na validação
    
    # Chaves estrangeiras
    entrada_id = Column(Integer, ForeignKey('entradas.id'), nullable=False)
    enfermeiro_id = Column(Integer, ForeignKey('funcionarios.id'), nullable=False)
    
    # Relacionamentos
    entrada = relationship("Entrada", back_populates="triagem")
    enfermeiro = relationship("Funcionario", foreign_keys=[enfermeiro_id])
    consulta_emergencia = relationship("ConsultaEmergencia", back_populates="triagem", uselist=False) 