"""
Repositório para funcionários
"""

from typing import List, Optional
from sqlalchemy.orm import Session
from sqlalchemy import or_, and_

from app.models.funcionario import Funcionario, Administrador, Medico, Enfermeiro, Atendente, Farmaceutico
from app.schemas.funcionario import FuncionarioCreate, FuncionarioUpdate
from app.utils.security import get_password_hash


class FuncionarioRepository:
    """Repositório para operações de funcionários"""
    
    def get_by_id(self, db: Session, funcionario_id: int) -> Optional[Funcionario]:
        """Buscar funcionário por ID"""
        return db.query(Funcionario).filter(Funcionario.id == funcionario_id).first()
    
    def get_by_usuario(self, db: Session, usuario: str) -> Optional[Funcionario]:
        """Buscar funcionário por usuário"""
        return db.query(Funcionario).filter(Funcionario.usuario == usuario).first()
    
    def get_by_email(self, db: Session, email: str) -> Optional[Funcionario]:
        """Buscar funcionário por email"""
        return db.query(Funcionario).filter(Funcionario.email == email).first()
    
    def get_all(
        self, 
        db: Session, 
        skip: int = 0, 
        limit: int = 100,
        tipo: Optional[str] = None,
        search: Optional[str] = None
    ) -> tuple[List[Funcionario], int]:
        """Listar funcionários com filtros"""
        query = db.query(Funcionario)
        
        # Filtro por tipo
        if tipo:
            query = query.filter(Funcionario.tipo == tipo)
        
        # Busca por nome, usuário ou email
        if search:
            query = query.filter(
                or_(
                    Funcionario.nome.ilike(f"%{search}%"),
                    Funcionario.usuario.ilike(f"%{search}%"),
                    Funcionario.email.ilike(f"%{search}%")
                )
            )
        
        total = query.count()
        funcionarios = query.offset(skip).limit(limit).all()
        
        return funcionarios, total
    
    def create(self, db: Session, funcionario_data: FuncionarioCreate) -> Funcionario:
        """Criar novo funcionário"""
        # Hash da senha
        senha_hash = get_password_hash(funcionario_data.senha)
        
        # Criar instância baseada no tipo
        if funcionario_data.tipo == "administrador":
            funcionario = Administrador()
        elif funcionario_data.tipo == "medico":
            funcionario = Medico()
        elif funcionario_data.tipo == "enfermeiro":
            funcionario = Enfermeiro()
        elif funcionario_data.tipo == "atendente":
            funcionario = Atendente()
        elif funcionario_data.tipo == "farmaceutico":
            funcionario = Farmaceutico()
        else:
            funcionario = Funcionario()
        
        # Definir atributos
        for field, value in funcionario_data.model_dump(exclude={'senha'}).items():
            if value is not None:
                setattr(funcionario, field, value)
        
        funcionario.senha_hash = senha_hash
        
        db.add(funcionario)
        db.commit()
        db.refresh(funcionario)
        
        return funcionario
    
    def update(self, db: Session, funcionario_id: int, funcionario_data: FuncionarioUpdate) -> Optional[Funcionario]:
        """Atualizar funcionário"""
        funcionario = self.get_by_id(db, funcionario_id)
        if not funcionario:
            return None
        
        update_data = funcionario_data.model_dump(exclude_unset=True)
        
        # Hash da nova senha se fornecida
        if 'senha' in update_data:
            update_data['senha_hash'] = get_password_hash(update_data.pop('senha'))
        
        for field, value in update_data.items():
            if hasattr(funcionario, field):
                setattr(funcionario, field, value)
        
        db.commit()
        db.refresh(funcionario)
        
        return funcionario
    
    def delete(self, db: Session, funcionario_id: int) -> bool:
        """Deletar funcionário"""
        funcionario = self.get_by_id(db, funcionario_id)
        if not funcionario:
            return False
        
        db.delete(funcionario)
        db.commit()
        
        return True
    
    def get_medicos(self, db: Session) -> List[Funcionario]:
        """Buscar todos os médicos"""
        return db.query(Funcionario).filter(Funcionario.tipo == "medico").all()
    
    def get_by_crm(self, db: Session, crm: str) -> Optional[Funcionario]:
        """Buscar médico por CRM"""
        return db.query(Funcionario).filter(
            and_(Funcionario.tipo == "medico", Funcionario.crm == crm)
        ).first()
    
    def get_by_coren(self, db: Session, coren: str) -> Optional[Funcionario]:
        """Buscar enfermeiro por COREN"""
        return db.query(Funcionario).filter(
            and_(Funcionario.tipo == "enfermeiro", Funcionario.coren == coren)
        ).first()
    
    def get_by_crf(self, db: Session, crf: str) -> Optional[Funcionario]:
        """Buscar farmacêutico por CRF"""
        return db.query(Funcionario).filter(
            and_(Funcionario.tipo == "farmaceutico", Funcionario.crf == crf)
        ).first()


# Instância global do repositório
funcionario_repository = FuncionarioRepository() 