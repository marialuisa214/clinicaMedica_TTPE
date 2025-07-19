"""
Modelos de Receita e Medicamento
"""

from datetime import date
from sqlalchemy import Column, String, Integer, Date, ForeignKey, Table
from sqlalchemy.orm import relationship
from .base import BaseModel


# Tabela de associação many-to-many entre Receita e Medicamento
receita_medicamento = Table(
    'receita_medicamento',
    BaseModel.metadata,
    Column('receita_id', Integer, ForeignKey('receitas.id'), primary_key=True),
    Column('medicamento_id', Integer, ForeignKey('medicamentos.id'), primary_key=True)
)


class Medicamento(BaseModel):
    """Modelo para Medicamento"""
    
    __tablename__ = "medicamentos"
    
    nome = Column(String(255), nullable=False)
    codigo_medicamento = Column(String(50), nullable=False, unique=True)
    laboratorio = Column(String(255))
    
    # Relacionamentos
    receitas = relationship("Receita", secondary=receita_medicamento, back_populates="medicamentos")


class Receita(BaseModel):
    """Modelo para Receita"""
    
    __tablename__ = "receitas"
    
    data_pedido = Column(Date, default=date.today)
    data_entrega = Column(Date)
    unidade = Column(String(50))
    descricao_medicamento = Column(String(1000))
    posologia = Column(String(500))
    assinatura = Column(String(255))
    
    # Chaves estrangeiras
    paciente_id = Column(Integer, ForeignKey('pacientes.id'), nullable=False)
    farmaceutico_id = Column(Integer, ForeignKey('funcionarios.id'))
    
    # Relacionamentos
    paciente = relationship("Paciente", back_populates="receitas")
    farmaceutico = relationship("Funcionario", foreign_keys=[farmaceutico_id])
    medicamentos = relationship("Medicamento", secondary=receita_medicamento, back_populates="receitas") 