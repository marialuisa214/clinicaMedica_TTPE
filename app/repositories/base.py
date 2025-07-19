"""
Repository base seguindo princípios SOLID
"""

from abc import ABC, abstractmethod
from typing import Generic, TypeVar, List, Optional, Any, Dict
from sqlalchemy.orm import Session
from sqlalchemy.exc import SQLAlchemyError

from app.core.database import Base

ModelType = TypeVar("ModelType", bound=Base)
CreateSchemaType = TypeVar("CreateSchemaType")
UpdateSchemaType = TypeVar("UpdateSchemaType")


class IBaseRepository(ABC, Generic[ModelType, CreateSchemaType, UpdateSchemaType]):
    """Interface do Repository base - Interface Segregation Principle"""
    
    @abstractmethod
    def create(self, db: Session, *, obj_in: CreateSchemaType) -> ModelType:
        """Criar novo registro"""
        pass
    
    @abstractmethod
    def get(self, db: Session, id: int) -> Optional[ModelType]:
        """Buscar por ID"""
        pass
    
    @abstractmethod
    def get_multi(self, db: Session, *, skip: int = 0, limit: int = 100) -> List[ModelType]:
        """Buscar múltiplos registros"""
        pass
    
    @abstractmethod
    def update(self, db: Session, *, db_obj: ModelType, obj_in: UpdateSchemaType) -> ModelType:
        """Atualizar registro"""
        pass
    
    @abstractmethod
    def delete(self, db: Session, *, id: int) -> ModelType:
        """Deletar registro"""
        pass


class BaseRepository(IBaseRepository[ModelType, CreateSchemaType, UpdateSchemaType]):
    """
    Repository base implementando operações CRUD
    Single Responsibility Principle: Responsável apenas por operações de dados
    """
    
    def __init__(self, model: type[ModelType]):
        """
        Dependency Inversion Principle: Depende da abstração (model), não da implementação
        """
        self.model = model
    
    def create(self, db: Session, *, obj_in: CreateSchemaType) -> ModelType:
        """Criar novo registro"""
        try:
            obj_data = obj_in.model_dump() if hasattr(obj_in, 'model_dump') else obj_in.dict()
            db_obj = self.model(**obj_data)
            db.add(db_obj)
            db.commit()
            db.refresh(db_obj)
            return db_obj
        except SQLAlchemyError as e:
            db.rollback()
            raise e
    
    def get(self, db: Session, id: int) -> Optional[ModelType]:
        """Buscar por ID"""
        return db.query(self.model).filter(self.model.id == id).first()
    
    def get_multi(self, db: Session, *, skip: int = 0, limit: int = 100) -> List[ModelType]:
        """Buscar múltiplos registros"""
        return db.query(self.model).offset(skip).limit(limit).all()
    
    def update(self, db: Session, *, db_obj: ModelType, obj_in: UpdateSchemaType) -> ModelType:
        """Atualizar registro"""
        try:
            obj_data = obj_in.model_dump(exclude_unset=True) if hasattr(obj_in, 'model_dump') else obj_in.dict(exclude_unset=True)
            
            for field, value in obj_data.items():
                if hasattr(db_obj, field):
                    setattr(db_obj, field, value)
            
            db.add(db_obj)
            db.commit()
            db.refresh(db_obj)
            return db_obj
        except SQLAlchemyError as e:
            db.rollback()
            raise e
    
    def delete(self, db: Session, *, id: int) -> ModelType:
        """Deletar registro"""
        try:
            obj = db.query(self.model).get(id)
            if obj:
                db.delete(obj)
                db.commit()
            return obj
        except SQLAlchemyError as e:
            db.rollback()
            raise e
    
    def get_by_field(self, db: Session, field: str, value: Any) -> Optional[ModelType]:
        """Buscar por campo específico"""
        return db.query(self.model).filter(getattr(self.model, field) == value).first()
    
    def get_by_fields(self, db: Session, filters: Dict[str, Any]) -> List[ModelType]:
        """Buscar por múltiplos campos"""
        query = db.query(self.model)
        for field, value in filters.items():
            if hasattr(self.model, field):
                query = query.filter(getattr(self.model, field) == value)
        return query.all() 