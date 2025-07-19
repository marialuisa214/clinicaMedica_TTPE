"""
Service para Paciente
"""

from typing import List, Optional
from sqlalchemy.orm import Session
from fastapi import HTTPException, status

from app.repositories.paciente import paciente_repository
from app.schemas.paciente import (
    PacienteCreate, PacienteUpdate, PacienteResponse, PacienteListResponse
)
from app.models.paciente import Paciente


class PacienteService:
    """
    Service para Paciente
    Single Responsibility Principle: Responsável pela lógica de negócio de pacientes
    """
    
    def __init__(self):
        self.repository = paciente_repository
    
    def create_paciente(self, db: Session, paciente_data: PacienteCreate) -> PacienteResponse:
        """
        Criar novo paciente
        Valida se CPF e RG já existem
        """
        # Verificar se CPF já existe
        existing_paciente = self.repository.get_by_cpf(db, paciente_data.cpf)
        if existing_paciente:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail="CPF já cadastrado"
            )
        
        # Verificar se RG já existe
        existing_rg = self.repository.get_by_rg(db, paciente_data.rg)
        if existing_rg:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail="RG já cadastrado"
            )
        
        # Criar paciente
        paciente = self.repository.create(db, obj_in=paciente_data)
        return PacienteResponse.model_validate(paciente)
    
    def get_paciente(self, db: Session, paciente_id: int) -> PacienteResponse:
        """Buscar paciente por ID"""
        paciente = self.repository.get(db, paciente_id)
        if not paciente:
            raise HTTPException(
                status_code=status.HTTP_404_NOT_FOUND,
                detail="Paciente não encontrado"
            )
        return PacienteResponse.model_validate(paciente)
    
    def get_paciente_by_cpf(self, db: Session, cpf: str) -> Optional[PacienteResponse]:
        """Buscar paciente por CPF"""
        paciente = self.repository.get_by_cpf(db, cpf)
        if paciente:
            return PacienteResponse.model_validate(paciente)
        return None
    
    def search_pacientes_by_name(self, db: Session, nome: str) -> List[PacienteResponse]:
        """Buscar pacientes por nome"""
        pacientes = self.repository.get_by_name(db, nome)
        return [PacienteResponse.model_validate(p) for p in pacientes]
    
    def get_all_pacientes(self, db: Session, skip: int = 0, limit: int = 100) -> PacienteListResponse:
        """Listar todos os pacientes"""
        pacientes = self.repository.get_multi(db, skip=skip, limit=limit)
        total = self.repository.count_total(db)
        
        return PacienteListResponse(
            pacientes=[PacienteResponse.model_validate(p) for p in pacientes],
            total=total
        )
    
    def update_paciente(self, db: Session, paciente_id: int, paciente_data: PacienteUpdate) -> PacienteResponse:
        """Atualizar paciente"""
        paciente = self.repository.get(db, paciente_id)
        if not paciente:
            raise HTTPException(
                status_code=status.HTTP_404_NOT_FOUND,
                detail="Paciente não encontrado"
            )
        
        updated_paciente = self.repository.update(db, db_obj=paciente, obj_in=paciente_data)
        return PacienteResponse.model_validate(updated_paciente)
    
    def delete_paciente(self, db: Session, paciente_id: int) -> bool:
        """Deletar paciente"""
        paciente = self.repository.get(db, paciente_id)
        if not paciente:
            raise HTTPException(
                status_code=status.HTTP_404_NOT_FOUND,
                detail="Paciente não encontrado"
            )
        
        self.repository.delete(db, id=paciente_id)
        return True
    
    def get_pacientes_with_patologia(self, db: Session) -> List[PacienteResponse]:
        """Buscar pacientes com patologias"""
        pacientes = self.repository.get_with_patologia(db)
        return [PacienteResponse.model_validate(p) for p in pacientes]


# Instância do service
paciente_service = PacienteService() 