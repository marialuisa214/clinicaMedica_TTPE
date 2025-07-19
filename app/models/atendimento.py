"""
Modelo de Atendimento
"""

from sqlalchemy import Column, Integer, String, DateTime, Text, ForeignKey, Enum, Float
from sqlalchemy.orm import relationship
from datetime import datetime
import enum

from app.models.base import BaseModel


class TipoAtendimento(str, enum.Enum):
    """Enum para tipos de atendimento"""
    TRIAGEM = "triagem"
    EMERGENCIA = "emergencia"
    INTERNACAO = "internacao"
    AMBULATORIAL = "ambulatorial"
    DOMICILIAR = "domiciliar"
    PROCEDIMENTO = "procedimento"


class StatusAtendimento(str, enum.Enum):
    """Enum para status do atendimento"""
    AGUARDANDO = "aguardando"
    EM_ATENDIMENTO = "em_atendimento"
    CONCLUIDO = "concluido"
    INTERROMPIDO = "interrompido"
    TRANSFERIDO = "transferido"


class Atendimento(BaseModel):
    """
    Modelo de Atendimento
    
    Representa um atendimento realizado por enfermeiro a um paciente,
    incluindo triagem, procedimentos e cuidados de enfermagem.
    """
    __tablename__ = "atendimentos"

    id = Column(Integer, primary_key=True, index=True)
    
    # Relacionamentos
    paciente_id = Column(Integer, ForeignKey("pacientes.id"), nullable=False, index=True)
    enfermeiro_responsavel_id = Column(Integer, ForeignKey("funcionarios.id"), nullable=False, index=True)
    medico_supervisor_id = Column(Integer, ForeignKey("funcionarios.id"), nullable=True, index=True)
    
    # Dados do atendimento
    tipo_atendimento = Column(Enum(TipoAtendimento), nullable=False, default=TipoAtendimento.AMBULATORIAL)
    motivo_atendimento = Column(String(500), nullable=False)
    
    # Datas e horários
    data_inicio = Column(DateTime, nullable=False, default=datetime.utcnow, index=True)
    data_fim = Column(DateTime, nullable=True)
    duracao_minutos = Column(Integer, nullable=True)
    
    # Status
    status = Column(Enum(StatusAtendimento), nullable=False, default=StatusAtendimento.AGUARDANDO, index=True)
    
    # Triagem (sinais vitais)
    pressao_arterial = Column(String(20), nullable=True)  # Ex: "120/80"
    temperatura = Column(Float, nullable=True)  # Em Celsius
    frequencia_cardiaca = Column(Integer, nullable=True)  # BPM
    frequencia_respiratoria = Column(Integer, nullable=True)  # IRPM
    saturacao_oxigenio = Column(Float, nullable=True)  # %
    peso = Column(Float, nullable=True)  # Kg
    altura = Column(Float, nullable=True)  # cm
    dor_escala = Column(Integer, nullable=True)  # Escala 0-10
    
    # Observações e procedimentos
    queixas_principais = Column(Text, nullable=True)
    historico_atual = Column(Text, nullable=True)
    procedimentos_realizados = Column(Text, nullable=True)
    medicamentos_administrados = Column(Text, nullable=True)
    observacoes_enfermagem = Column(Text, nullable=True)
    orientacoes_paciente = Column(Text, nullable=True)
    
    # Resultado do atendimento
    condicoes_alta = Column(Text, nullable=True)
    encaminhamentos = Column(Text, nullable=True)
    retorno_necessario = Column(String(255), nullable=True)
    
    # Localização
    setor_atendimento = Column(String(100), nullable=True)
    leito = Column(String(20), nullable=True)
    
    # Timestamps
    created_at = Column(DateTime, default=datetime.utcnow, nullable=False)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow, nullable=False)

    # Relacionamentos
    paciente = relationship("Paciente", back_populates="atendimentos")
    enfermeiro_responsavel = relationship(
        "Funcionario",
        foreign_keys=[enfermeiro_responsavel_id],
        back_populates="atendimentos_enfermeiro"
    )
    medico_supervisor = relationship(
        "Funcionario",
        foreign_keys=[medico_supervisor_id],
        back_populates="atendimentos_supervisionados"
    )

    def __repr__(self):
        return f"<Atendimento(id={self.id}, paciente_id={self.paciente_id}, tipo='{self.tipo_atendimento}', status='{self.status}')>"

    @property
    def status_display(self) -> str:
        """Retorna o status em formato legível"""
        status_map = {
            StatusAtendimento.AGUARDANDO: "Aguardando",
            StatusAtendimento.EM_ATENDIMENTO: "Em Atendimento",
            StatusAtendimento.CONCLUIDO: "Concluído",
            StatusAtendimento.INTERROMPIDO: "Interrompido",
            StatusAtendimento.TRANSFERIDO: "Transferido"
        }
        return status_map.get(self.status, self.status)

    @property
    def tipo_display(self) -> str:
        """Retorna o tipo em formato legível"""
        tipo_map = {
            TipoAtendimento.TRIAGEM: "Triagem",
            TipoAtendimento.EMERGENCIA: "Emergência",
            TipoAtendimento.INTERNACAO: "Internação",
            TipoAtendimento.AMBULATORIAL: "Ambulatorial",
            TipoAtendimento.DOMICILIAR: "Domiciliar",
            TipoAtendimento.PROCEDIMENTO: "Procedimento"
        }
        return tipo_map.get(self.tipo_atendimento, self.tipo_atendimento)

    @property
    def imc(self) -> float:
        """Calcula o IMC se peso e altura estiverem disponíveis"""
        if self.peso and self.altura:
            altura_metros = self.altura / 100
            return round(self.peso / (altura_metros ** 2), 2)
        return None

    @property
    def duracao_calculada(self) -> int:
        """Calcula duração em minutos se data_fim estiver definida"""
        if self.data_fim and self.data_inicio:
            delta = self.data_fim - self.data_inicio
            return int(delta.total_seconds() / 60)
        return self.duracao_minutos 