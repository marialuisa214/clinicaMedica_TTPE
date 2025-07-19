"""
Repository para Exames
"""

from typing import List, Optional
from sqlalchemy.orm import Session, joinedload
from sqlalchemy import and_, or_, desc, asc
from datetime import datetime, date

from app.models.exame import Exame, StatusExame, TipoExame
from app.schemas.exame import ExameCreate, ExameUpdate


class ExameRepository:
    """Repository para operações com Exames"""

    def get_by_id(self, db: Session, exame_id: int) -> Optional[Exame]:
        """Buscar exame por ID com relacionamentos carregados"""
        return db.query(Exame).options(
            joinedload(Exame.paciente),
            joinedload(Exame.medico_responsavel),
            joinedload(Exame.enfermeiro_responsavel)
        ).filter(Exame.id == exame_id).first()

    def get_all(
        self,
        db: Session,
        skip: int = 0,
        limit: int = 100,
        paciente_id: Optional[int] = None,
        medico_id: Optional[int] = None,
        enfermeiro_id: Optional[int] = None,
        status: Optional[StatusExame] = None,
        tipo: Optional[TipoExame] = None,
        data_inicio: Optional[date] = None,
        data_fim: Optional[date] = None,
        search: Optional[str] = None
    ) -> List[Exame]:
        """Listar exames com filtros"""
        query = db.query(Exame).options(
            joinedload(Exame.paciente),
            joinedload(Exame.medico_responsavel),
            joinedload(Exame.enfermeiro_responsavel)
        )

        # Aplicar filtros
        if paciente_id:
            query = query.filter(Exame.paciente_id == paciente_id)
        
        if medico_id:
            query = query.filter(Exame.medico_responsavel_id == medico_id)
            
        if enfermeiro_id:
            query = query.filter(Exame.enfermeiro_responsavel_id == enfermeiro_id)
        
        if status:
            query = query.filter(Exame.status == status)
            
        if tipo:
            query = query.filter(Exame.tipo_exame == tipo)
        
        if data_inicio:
            query = query.filter(Exame.data_agendamento >= data_inicio)
            
        if data_fim:
            query = query.filter(Exame.data_agendamento <= data_fim)
        
        if search:
            query = query.join(Exame.paciente).filter(
                or_(
                    Exame.nome_exame.ilike(f"%{search}%"),
                    Exame.observacoes.ilike(f"%{search}%"),
                    Exame.paciente.has(nome=search)
                )
            )

        return query.order_by(desc(Exame.data_agendamento)).offset(skip).limit(limit).all()

    def count(
        self,
        db: Session,
        paciente_id: Optional[int] = None,
        medico_id: Optional[int] = None,
        enfermeiro_id: Optional[int] = None,
        status: Optional[StatusExame] = None,
        tipo: Optional[TipoExame] = None,
        data_inicio: Optional[date] = None,
        data_fim: Optional[date] = None,
        search: Optional[str] = None
    ) -> int:
        """Contar exames com filtros"""
        query = db.query(Exame)

        # Aplicar os mesmos filtros da consulta principal
        if paciente_id:
            query = query.filter(Exame.paciente_id == paciente_id)
        if medico_id:
            query = query.filter(Exame.medico_responsavel_id == medico_id)
        if enfermeiro_id:
            query = query.filter(Exame.enfermeiro_responsavel_id == enfermeiro_id)
        if status:
            query = query.filter(Exame.status == status)
        if tipo:
            query = query.filter(Exame.tipo_exame == tipo)
        if data_inicio:
            query = query.filter(Exame.data_agendamento >= data_inicio)
        if data_fim:
            query = query.filter(Exame.data_agendamento <= data_fim)
        if search:
            query = query.join(Exame.paciente).filter(
                or_(
                    Exame.nome_exame.ilike(f"%{search}%"),
                    Exame.observacoes.ilike(f"%{search}%"),
                    Exame.paciente.has(nome=search)
                )
            )

        return query.count()

    def create(self, db: Session, exame_data: ExameCreate) -> Exame:
        """Criar novo exame"""
        db_exame = Exame(**exame_data.model_dump())
        db.add(db_exame)
        db.commit()
        db.refresh(db_exame)
        return self.get_by_id(db, db_exame.id)

    def update(self, db: Session, exame_id: int, exame_data: ExameUpdate) -> Optional[Exame]:
        """Atualizar exame existente"""
        db_exame = self.get_by_id(db, exame_id)
        if not db_exame:
            return None

        update_data = exame_data.model_dump(exclude_unset=True)
        for field, value in update_data.items():
            if hasattr(db_exame, field):
                setattr(db_exame, field, value)

        db.commit()
        db.refresh(db_exame)
        return db_exame

    def delete(self, db: Session, exame_id: int) -> bool:
        """Deletar exame"""
        db_exame = self.get_by_id(db, exame_id)
        if not db_exame:
            return False

        db.delete(db_exame)
        db.commit()
        return True

    def get_by_paciente(self, db: Session, paciente_id: int, limit: int = 50) -> List[Exame]:
        """Buscar exames de um paciente específico"""
        return db.query(Exame).options(
            joinedload(Exame.medico_responsavel),
            joinedload(Exame.enfermeiro_responsavel)
        ).filter(Exame.paciente_id == paciente_id).order_by(desc(Exame.data_agendamento)).limit(limit).all()

    def get_by_medico(self, db: Session, medico_id: int, data: Optional[date] = None) -> List[Exame]:
        """Buscar exames de um médico específico"""
        query = db.query(Exame).options(
            joinedload(Exame.paciente),
            joinedload(Exame.enfermeiro_responsavel)
        ).filter(Exame.medico_responsavel_id == medico_id)
        
        if data:
            query = query.filter(Exame.data_agendamento.between(
                datetime.combine(data, datetime.min.time()),
                datetime.combine(data, datetime.max.time())
            ))
        
        return query.order_by(asc(Exame.data_agendamento)).all()

    def get_by_enfermeiro(self, db: Session, enfermeiro_id: int, data: Optional[date] = None) -> List[Exame]:
        """Buscar exames de um enfermeiro específico"""
        query = db.query(Exame).options(
            joinedload(Exame.paciente),
            joinedload(Exame.medico_responsavel)
        ).filter(Exame.enfermeiro_responsavel_id == enfermeiro_id)
        
        if data:
            query = query.filter(Exame.data_agendamento.between(
                datetime.combine(data, datetime.min.time()),
                datetime.combine(data, datetime.max.time())
            ))
        
        return query.order_by(asc(Exame.data_agendamento)).all()

    def get_agenda_dia(self, db: Session, data: date, setor: Optional[str] = None) -> List[Exame]:
        """Buscar todos os exames agendados para um dia específico"""
        query = db.query(Exame).options(
            joinedload(Exame.paciente),
            joinedload(Exame.medico_responsavel),
            joinedload(Exame.enfermeiro_responsavel)
        ).filter(
            and_(
                Exame.data_agendamento >= datetime.combine(data, datetime.min.time()),
                Exame.data_agendamento <= datetime.combine(data, datetime.max.time()),
                Exame.status.in_([StatusExame.AGENDADO, StatusExame.EM_PREPARO, StatusExame.EM_EXECUCAO])
            )
        )
        
        return query.order_by(asc(Exame.data_agendamento)).all()

    def update_status(self, db: Session, exame_id: int, novo_status: StatusExame) -> Optional[Exame]:
        """Atualizar apenas o status do exame"""
        db_exame = self.get_by_id(db, exame_id)
        if not db_exame:
            return None

        db_exame.status = novo_status
        
        # Atualizar timestamps relevantes
        if novo_status == StatusExame.EM_EXECUCAO and not db_exame.data_execucao:
            db_exame.data_execucao = datetime.utcnow()
        elif novo_status == StatusExame.RESULTADO_DISPONIVEL and not db_exame.data_resultado:
            db_exame.data_resultado = datetime.utcnow()

        db.commit()
        db.refresh(db_exame)
        return db_exame


# Instância global do repository
exame_repository = ExameRepository() 