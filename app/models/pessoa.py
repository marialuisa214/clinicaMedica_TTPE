"""
Modelo da entidade Pessoa
"""

from datetime import date, datetime
from sqlalchemy import Column, String, Date, Enum
from sqlalchemy.ext.hybrid import hybrid_property
from .base import BaseModel
import enum


class SexoEnum(str, enum.Enum):
    """Enum para sexo"""
    MASCULINO = "M"
    FEMININO = "F"


class Pessoa(BaseModel):
    """Classe base para Pessoa seguindo o padrão do sistema Java"""
    
    __abstract__ = True
    
    nome = Column(String(255), nullable=False)
    rg = Column(String(20), nullable=False)
    cpf = Column(String(14), nullable=False, unique=True)
    sexo = Column(Enum(SexoEnum), nullable=False)
    data_nascimento = Column(Date, nullable=False)
    telefone = Column(String(20))
    email = Column(String(255))
    cidade_estado = Column(String(100))
    endereco = Column(String(255))
    
    @hybrid_property
    def idade(self) -> int:
        """Calcula a idade baseada na data de nascimento"""
        if self.data_nascimento:
            today = date.today()
            return today.year - self.data_nascimento.year - (
                (today.month, today.day) < (self.data_nascimento.month, self.data_nascimento.day)
            )
        return 0 