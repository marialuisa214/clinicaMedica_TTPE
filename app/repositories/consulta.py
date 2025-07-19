"""
Repositório para consultas e agenda médica
"""

from typing import List, Optional
from sqlalchemy.orm import Session, joinedload
from sqlalchemy import and_, or_, func, desc
from datetime import datetime, date, timedelta

from app.models.consulta import Consulta, AgendaMedico, StatusConsulta, TipoConsulta
from app.models.funcionario import Funcionario
from app.models.paciente import Paciente
from app.schemas.consulta import ConsultaCreate, ConsultaUpdate, AgendaMedicoCreate, AgendaMedicoUpdate


class ConsultaRepository:
    """Repositório para operações de consultas"""
    
    def get_by_id(self, db: Session, consulta_id: int) -> Optional[Consulta]:
        """Buscar consulta por ID com dados relacionados"""
        return db.query(Consulta)\
            .options(joinedload(Consulta.paciente))\
            .options(joinedload(Consulta.medico))\
            .options(joinedload(Consulta.atendente))\
            .filter(Consulta.id == consulta_id).first()
    
    def get_all(
        self,
        db: Session,
        skip: int = 0,
        limit: int = 100,
        medico_id: Optional[int] = None,
        paciente_id: Optional[int] = None,
        status: Optional[StatusConsulta] = None,
        data_inicio: Optional[date] = None,
        data_fim: Optional[date] = None
    ) -> tuple[List[Consulta], int]:
        """Listar consultas com filtros"""
        query = db.query(Consulta)\
            .options(joinedload(Consulta.paciente))\
            .options(joinedload(Consulta.medico))
        
        # Filtros
        if medico_id:
            query = query.filter(Consulta.medico_id == medico_id)
        
        if paciente_id:
            query = query.filter(Consulta.paciente_id == paciente_id)
        
        if status:
            query = query.filter(Consulta.status == status)
        
        if data_inicio:
            query = query.filter(func.date(Consulta.data_hora) >= data_inicio)
        
        if data_fim:
            query = query.filter(func.date(Consulta.data_hora) <= data_fim)
        
        # Ordenar por data/hora
        query = query.order_by(Consulta.data_hora)
        
        total = query.count()
        consultas = query.offset(skip).limit(limit).all()
        
        return consultas, total
    
    def create(self, db: Session, consulta_data: ConsultaCreate, atendente_id: Optional[int] = None) -> Consulta:
        """Criar nova consulta"""
        consulta = Consulta(**consulta_data.model_dump())
        if atendente_id:
            consulta.atendente_id = atendente_id
        
        db.add(consulta)
        db.commit()
        db.refresh(consulta)
        
        return consulta
    
    def update(self, db: Session, consulta_id: int, consulta_data: ConsultaUpdate) -> Optional[Consulta]:
        """Atualizar consulta"""
        consulta = self.get_by_id(db, consulta_id)
        if not consulta:
            return None
        
        update_data = consulta_data.model_dump(exclude_unset=True)
        
        for field, value in update_data.items():
            if hasattr(consulta, field):
                setattr(consulta, field, value)
        
        db.commit()
        db.refresh(consulta)
        
        return consulta
    
    def delete(self, db: Session, consulta_id: int) -> bool:
        """Deletar consulta"""
        consulta = self.get_by_id(db, consulta_id)
        if not consulta:
            return False
        
        db.delete(consulta)
        db.commit()
        
        return True
    
    def get_consultas_medico_hoje(self, db: Session, medico_id: int) -> List[Consulta]:
        """Buscar consultas do médico para hoje"""
        hoje = date.today()
        return db.query(Consulta)\
            .options(joinedload(Consulta.paciente))\
            .filter(
                and_(
                    Consulta.medico_id == medico_id,
                    func.date(Consulta.data_hora) == hoje
                )
            )\
            .order_by(Consulta.data_hora)\
            .all()
    
    def get_consultas_por_periodo(
        self, 
        db: Session, 
        data_inicio: date, 
        data_fim: date,
        medico_id: Optional[int] = None
    ) -> List[Consulta]:
        """Buscar consultas por período"""
        query = db.query(Consulta)\
            .options(joinedload(Consulta.paciente))\
            .options(joinedload(Consulta.medico))\
            .filter(
                and_(
                    func.date(Consulta.data_hora) >= data_inicio,
                    func.date(Consulta.data_hora) <= data_fim
                )
            )
        
        if medico_id:
            query = query.filter(Consulta.medico_id == medico_id)
        
        return query.order_by(Consulta.data_hora).all()


class AgendaMedicoRepository:
    """Repositório para agenda médica"""
    
    def get_by_id(self, db: Session, agenda_id: int) -> Optional[AgendaMedico]:
        """Buscar agenda por ID"""
        return db.query(AgendaMedico)\
            .options(joinedload(AgendaMedico.medico))\
            .filter(AgendaMedico.id == agenda_id).first()
    
    def get_agenda_medico(
        self,
        db: Session,
        medico_id: int,
        data_inicio: Optional[date] = None,
        data_fim: Optional[date] = None
    ) -> List[AgendaMedico]:
        """Buscar agenda do médico por período"""
        query = db.query(AgendaMedico).filter(AgendaMedico.medico_id == medico_id)
        
        if data_inicio:
            query = query.filter(func.date(AgendaMedico.data) >= data_inicio)
        
        if data_fim:
            query = query.filter(func.date(AgendaMedico.data) <= data_fim)
        
        return query.order_by(AgendaMedico.data, AgendaMedico.hora_inicio).all()
    
    def create(self, db: Session, agenda_data: AgendaMedicoCreate) -> AgendaMedico:
        """Criar entrada na agenda"""
        agenda = AgendaMedico(**agenda_data.model_dump())
        
        db.add(agenda)
        db.commit()
        db.refresh(agenda)
        
        return agenda
    
    def update(self, db: Session, agenda_id: int, agenda_data: AgendaMedicoUpdate) -> Optional[AgendaMedico]:
        """Atualizar agenda"""
        agenda = self.get_by_id(db, agenda_id)
        if not agenda:
            return None
        
        update_data = agenda_data.model_dump(exclude_unset=True)
        
        for field, value in update_data.items():
            if hasattr(agenda, field):
                setattr(agenda, field, value)
        
        db.commit()
        db.refresh(agenda)
        
        return agenda
    
    def delete(self, db: Session, agenda_id: int) -> bool:
        """Deletar entrada da agenda"""
        agenda = self.get_by_id(db, agenda_id)
        if not agenda:
            return False
        
        db.delete(agenda)
        db.commit()
        
        return True
    
    def get_horarios_disponiveis(self, db: Session, medico_id: int, data: date) -> List[str]:
        """Buscar horários disponíveis do médico em uma data"""
        agenda = db.query(AgendaMedico).filter(
            and_(
                AgendaMedico.medico_id == medico_id,
                func.date(AgendaMedico.data) == data,
                AgendaMedico.disponivel == True
            )
        ).all()
        
        horarios = []
        for item in agenda:
            # Gerar horários de 30 em 30 minutos entre hora_inicio e hora_fim
            hora_inicio = datetime.strptime(item.hora_inicio, "%H:%M").time()
            hora_fim = datetime.strptime(item.hora_fim, "%H:%M").time()
            
            current_time = datetime.combine(data, hora_inicio)
            end_time = datetime.combine(data, hora_fim)
            
            while current_time < end_time:
                # Verificar se já existe consulta neste horário
                consulta_existente = db.query(Consulta).filter(
                    and_(
                        Consulta.medico_id == medico_id,
                        Consulta.data_hora == current_time,
                        Consulta.status != StatusConsulta.CANCELADA
                    )
                ).first()
                
                if not consulta_existente:
                    horarios.append(current_time.strftime("%H:%M"))
                
                current_time += timedelta(minutes=30)
        
        return sorted(horarios)


# Instâncias globais dos repositórios
consulta_repository = ConsultaRepository()
agenda_medico_repository = AgendaMedicoRepository() 