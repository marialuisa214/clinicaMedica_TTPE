"""
Repository para entidade Paciente
"""

from typing import Optional, List
from sqlalchemy.orm import Session

from app.models.paciente import Paciente
from app.schemas.paciente import PacienteCreate, PacienteUpdate
from .base import BaseRepository


class PacienteRepository(BaseRepository[Paciente, PacienteCreate, PacienteUpdate]):
    """
    Repository específico para Paciente
    Open/Closed Principle: Estende BaseRepository sem modificá-lo
    """
    
    def __init__(self):
        super().__init__(Paciente)
    
    def get_by_cpf(self, db: Session, cpf: str) -> Optional[Paciente]:
        """Buscar paciente por CPF"""
        return self.get_by_field(db, "cpf", cpf)
    
    def get_by_name(self, db: Session, nome: str) -> List[Paciente]:
        """Buscar pacientes por nome (busca parcial)"""
        return db.query(self.model).filter(
            self.model.nome.ilike(f"%{nome}%")
        ).all()
    
    def get_by_rg(self, db: Session, rg: str) -> Optional[Paciente]:
        """Buscar paciente por RG"""
        return self.get_by_field(db, "rg", rg)
    
    def get_with_patologia(self, db: Session) -> List[Paciente]:
        """Buscar pacientes que possuem patologias"""
        return db.query(self.model).filter(
            self.model.patologia.isnot(None),
            self.model.patologia != ""
        ).all()
    
    def count_total(self, db: Session) -> int:
        """Contar total de pacientes"""
        return db.query(self.model).count()


# Instância do repository seguindo Dependency Inversion Principle
paciente_repository = PacienteRepository() 