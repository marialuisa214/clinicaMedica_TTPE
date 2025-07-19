"""
Repository para Atendimentos
"""

from typing import List, Optional
from sqlalchemy.orm import Session, joinedload
from sqlalchemy import and_, or_, desc, asc
from datetime import datetime, date

from app.models.atendimento import Atendimento, StatusAtendimento, TipoAtendimento
from app.schemas.atendimento import AtendimentoCreate, AtendimentoUpdate


class AtendimentoRepository:
    """Repository para operações com Atendimentos"""

    def get_by_id(self, db: Session, atendimento_id: int) -> Optional[Atendimento]:
        """Buscar atendimento por ID com relacionamentos carregados"""
        return db.query(Atendimento).options(
            joinedload(Atendimento.paciente),
            joinedload(Atendimento.enfermeiro_responsavel),
            joinedload(Atendimento.medico_supervisor)
        ).filter(Atendimento.id == atendimento_id).first()

    def get_all(
        self,
        db: Session,
        skip: int = 0,
        limit: int = 100,
        paciente_id: Optional[int] = None,
        enfermeiro_id: Optional[int] = None,
        medico_supervisor_id: Optional[int] = None,
        status: Optional[StatusAtendimento] = None,
        tipo: Optional[TipoAtendimento] = None,
        data_inicio: Optional[date] = None,
        data_fim: Optional[date] = None,
        setor: Optional[str] = None,
        search: Optional[str] = None
    ) -> List[Atendimento]:
        """Listar atendimentos com filtros"""
        query = db.query(Atendimento).options(
            joinedload(Atendimento.paciente),
            joinedload(Atendimento.enfermeiro_responsavel),
            joinedload(Atendimento.medico_supervisor)
        )

        # Aplicar filtros
        if paciente_id:
            query = query.filter(Atendimento.paciente_id == paciente_id)
        
        if enfermeiro_id:
            query = query.filter(Atendimento.enfermeiro_responsavel_id == enfermeiro_id)
            
        if medico_supervisor_id:
            query = query.filter(Atendimento.medico_supervisor_id == medico_supervisor_id)
        
        if status:
            query = query.filter(Atendimento.status == status)
            
        if tipo:
            query = query.filter(Atendimento.tipo_atendimento == tipo)
            
        if setor:
            query = query.filter(Atendimento.setor_atendimento.ilike(f"%{setor}%"))
        
        if data_inicio:
            query = query.filter(Atendimento.data_inicio >= data_inicio)
            
        if data_fim:
            query = query.filter(Atendimento.data_inicio <= data_fim)
        
        if search:
            query = query.join(Atendimento.paciente).filter(
                or_(
                    Atendimento.motivo_atendimento.ilike(f"%{search}%"),
                    Atendimento.observacoes_enfermagem.ilike(f"%{search}%"),
                    Atendimento.paciente.has(nome=search),
                    Atendimento.setor_atendimento.ilike(f"%{search}%")
                )
            )

        return query.order_by(desc(Atendimento.data_inicio)).offset(skip).limit(limit).all()

    def count(
        self,
        db: Session,
        paciente_id: Optional[int] = None,
        enfermeiro_id: Optional[int] = None,
        medico_supervisor_id: Optional[int] = None,
        status: Optional[StatusAtendimento] = None,
        tipo: Optional[TipoAtendimento] = None,
        data_inicio: Optional[date] = None,
        data_fim: Optional[date] = None,
        setor: Optional[str] = None,
        search: Optional[str] = None
    ) -> int:
        """Contar atendimentos com filtros"""
        query = db.query(Atendimento)

        # Aplicar os mesmos filtros da consulta principal
        if paciente_id:
            query = query.filter(Atendimento.paciente_id == paciente_id)
        if enfermeiro_id:
            query = query.filter(Atendimento.enfermeiro_responsavel_id == enfermeiro_id)
        if medico_supervisor_id:
            query = query.filter(Atendimento.medico_supervisor_id == medico_supervisor_id)
        if status:
            query = query.filter(Atendimento.status == status)
        if tipo:
            query = query.filter(Atendimento.tipo_atendimento == tipo)
        if setor:
            query = query.filter(Atendimento.setor_atendimento.ilike(f"%{setor}%"))
        if data_inicio:
            query = query.filter(Atendimento.data_inicio >= data_inicio)
        if data_fim:
            query = query.filter(Atendimento.data_inicio <= data_fim)
        if search:
            query = query.join(Atendimento.paciente).filter(
                or_(
                    Atendimento.motivo_atendimento.ilike(f"%{search}%"),
                    Atendimento.observacoes_enfermagem.ilike(f"%{search}%"),
                    Atendimento.paciente.has(nome=search),
                    Atendimento.setor_atendimento.ilike(f"%{search}%")
                )
            )

        return query.count()

    def create(self, db: Session, atendimento_data: AtendimentoCreate) -> Atendimento:
        """Criar novo atendimento"""
        data_dict = atendimento_data.model_dump()
        
        # Se data_inicio não foi fornecida, usar agora
        if not data_dict.get('data_inicio'):
            data_dict['data_inicio'] = datetime.utcnow()
            
        db_atendimento = Atendimento(**data_dict)
        db.add(db_atendimento)
        db.commit()
        db.refresh(db_atendimento)
        return self.get_by_id(db, db_atendimento.id)

    def update(self, db: Session, atendimento_id: int, atendimento_data: AtendimentoUpdate) -> Optional[Atendimento]:
        """Atualizar atendimento existente"""
        db_atendimento = self.get_by_id(db, atendimento_id)
        if not db_atendimento:
            return None

        update_data = atendimento_data.model_dump(exclude_unset=True)
        
        # Calcular duração automaticamente se data_fim foi fornecida
        if 'data_fim' in update_data and update_data['data_fim']:
            if db_atendimento.data_inicio:
                delta = update_data['data_fim'] - db_atendimento.data_inicio
                update_data['duracao_minutos'] = int(delta.total_seconds() / 60)
        
        for field, value in update_data.items():
            if hasattr(db_atendimento, field):
                setattr(db_atendimento, field, value)

        db.commit()
        db.refresh(db_atendimento)
        return db_atendimento

    def delete(self, db: Session, atendimento_id: int) -> bool:
        """Deletar atendimento"""
        db_atendimento = self.get_by_id(db, atendimento_id)
        if not db_atendimento:
            return False

        db.delete(db_atendimento)
        db.commit()
        return True

    def get_by_paciente(self, db: Session, paciente_id: int, limit: int = 50) -> List[Atendimento]:
        """Buscar atendimentos de um paciente específico"""
        return db.query(Atendimento).options(
            joinedload(Atendimento.enfermeiro_responsavel),
            joinedload(Atendimento.medico_supervisor)
        ).filter(Atendimento.paciente_id == paciente_id).order_by(desc(Atendimento.data_inicio)).limit(limit).all()

    def get_by_enfermeiro(self, db: Session, enfermeiro_id: int, data: Optional[date] = None) -> List[Atendimento]:
        """Buscar atendimentos de um enfermeiro específico"""
        query = db.query(Atendimento).options(
            joinedload(Atendimento.paciente),
            joinedload(Atendimento.medico_supervisor)
        ).filter(Atendimento.enfermeiro_responsavel_id == enfermeiro_id)
        
        if data:
            query = query.filter(Atendimento.data_inicio.between(
                datetime.combine(data, datetime.min.time()),
                datetime.combine(data, datetime.max.time())
            ))
        
        return query.order_by(asc(Atendimento.data_inicio)).all()

    def get_atendimentos_em_andamento(self, db: Session, enfermeiro_id: Optional[int] = None) -> List[Atendimento]:
        """Buscar atendimentos em andamento"""
        query = db.query(Atendimento).options(
            joinedload(Atendimento.paciente),
            joinedload(Atendimento.enfermeiro_responsavel),
            joinedload(Atendimento.medico_supervisor)
        ).filter(Atendimento.status.in_([StatusAtendimento.AGUARDANDO, StatusAtendimento.EM_ATENDIMENTO]))
        
        if enfermeiro_id:
            query = query.filter(Atendimento.enfermeiro_responsavel_id == enfermeiro_id)
        
        return query.order_by(asc(Atendimento.data_inicio)).all()

    def get_triagens_pendentes(self, db: Session, setor: Optional[str] = None) -> List[Atendimento]:
        """Buscar triagens pendentes"""
        query = db.query(Atendimento).options(
            joinedload(Atendimento.paciente),
            joinedload(Atendimento.enfermeiro_responsavel)
        ).filter(
            and_(
                Atendimento.tipo_atendimento == TipoAtendimento.TRIAGEM,
                Atendimento.status.in_([StatusAtendimento.AGUARDANDO, StatusAtendimento.EM_ATENDIMENTO])
            )
        )
        
        if setor:
            query = query.filter(Atendimento.setor_atendimento == setor)
        
        return query.order_by(asc(Atendimento.data_inicio)).all()

    def finalizar_atendimento(self, db: Session, atendimento_id: int) -> Optional[Atendimento]:
        """Finalizar atendimento automaticamente"""
        db_atendimento = self.get_by_id(db, atendimento_id)
        if not db_atendimento:
            return None

        db_atendimento.status = StatusAtendimento.CONCLUIDO
        db_atendimento.data_fim = datetime.utcnow()
        
        # Calcular duração
        if db_atendimento.data_inicio:
            delta = db_atendimento.data_fim - db_atendimento.data_inicio
            db_atendimento.duracao_minutos = int(delta.total_seconds() / 60)

        db.commit()
        db.refresh(db_atendimento)
        return db_atendimento

    def iniciar_atendimento(self, db: Session, atendimento_id: int) -> Optional[Atendimento]:
        """Marcar atendimento como em andamento"""
        db_atendimento = self.get_by_id(db, atendimento_id)
        if not db_atendimento:
            return None

        db_atendimento.status = StatusAtendimento.EM_ATENDIMENTO
        
        # Se ainda não tem data_inicio, definir agora
        if not db_atendimento.data_inicio:
            db_atendimento.data_inicio = datetime.utcnow()

        db.commit()
        db.refresh(db_atendimento)
        return db_atendimento

    def get_estatisticas_enfermeiro(self, db: Session, enfermeiro_id: int, data: date) -> dict:
        """Obter estatísticas de atendimentos de um enfermeiro para uma data"""
        base_query = db.query(Atendimento).filter(
            and_(
                Atendimento.enfermeiro_responsavel_id == enfermeiro_id,
                Atendimento.data_inicio >= datetime.combine(data, datetime.min.time()),
                Atendimento.data_inicio <= datetime.combine(data, datetime.max.time())
            )
        )
        
        total = base_query.count()
        concluidos = base_query.filter(Atendimento.status == StatusAtendimento.CONCLUIDO).count()
        em_andamento = base_query.filter(Atendimento.status == StatusAtendimento.EM_ATENDIMENTO).count()
        triagens = base_query.filter(Atendimento.tipo_atendimento == TipoAtendimento.TRIAGEM).count()
        
        return {
            "total": total,
            "concluidos": concluidos,
            "em_andamento": em_andamento,
            "triagens": triagens,
            "pendentes": total - concluidos
        }


# Instância global do repository
atendimento_repository = AtendimentoRepository() 