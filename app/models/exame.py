"""
Modelo de Exame
"""

from sqlalchemy import Column, Integer, String, DateTime, Text, ForeignKey, Enum
from sqlalchemy.orm import relationship
from datetime import datetime
import enum

from app.models.base import BaseModel


class TipoExame(str, enum.Enum):
    """Enum para tipos de exame"""
    LABORATORIAL = "laboratorial"
    IMAGEM = "imagem"
    CARDIOLOGICO = "cardiologico"
    NEUROLÓGICO = "neurologico"
    OFTALMOLOGICO = "oftalmologico"
    AUDITIVO = "auditivo"
    OUTROS = "outros"


class StatusExame(str, enum.Enum):
    """Enum para status do exame"""
    AGENDADO = "agendado"
    EM_PREPARO = "em_preparo"
    EM_EXECUCAO = "em_execucao"
    CONCLUIDO = "concluido"
    CANCELADO = "cancelado"
    RESULTADO_DISPONIVEL = "resultado_disponivel"


class Exame(BaseModel):
    """
    Modelo de Exame
    
    Representa um exame médico solicitado para um paciente,
    com médico responsável, data/hora e controle de status.
    """
    __tablename__ = "exames"

    id = Column(Integer, primary_key=True, index=True)
    
    # Relacionamentos
    paciente_id = Column(Integer, ForeignKey("pacientes.id"), nullable=False, index=True)
    medico_responsavel_id = Column(Integer, ForeignKey("funcionarios.id"), nullable=False, index=True)
    enfermeiro_responsavel_id = Column(Integer, ForeignKey("funcionarios.id"), nullable=True, index=True)
    
    # Dados do exame
    nome_exame = Column(String(255), nullable=False, index=True)
    tipo_exame = Column(Enum(TipoExame), nullable=False, default=TipoExame.LABORATORIAL)
    descricao = Column(Text, nullable=True)
    
    # Agendamento
    data_agendamento = Column(DateTime, nullable=False, index=True)
    data_execucao = Column(DateTime, nullable=True)
    data_resultado = Column(DateTime, nullable=True)
    
    # Status e observações
    status = Column(Enum(StatusExame), nullable=False, default=StatusExame.AGENDADO, index=True)
    observacoes = Column(Text, nullable=True)
    preparo_necessario = Column(Text, nullable=True)
    
    # Resultados
    resultado = Column(Text, nullable=True)
    laudo_medico = Column(Text, nullable=True)
    arquivo_resultado = Column(String(500), nullable=True)  # Path do arquivo
    
    # Valores (opcional)
    valor_exame = Column(String(20), nullable=True)
    convenio = Column(String(100), nullable=True)
    
    # Timestamps
    created_at = Column(DateTime, default=datetime.utcnow, nullable=False)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow, nullable=False)

    # Relacionamentos
    paciente = relationship("Paciente", back_populates="exames")
    medico_responsavel = relationship(
        "Funcionario", 
        foreign_keys=[medico_responsavel_id],
        back_populates="exames_medico"
    )
    enfermeiro_responsavel = relationship(
        "Funcionario",
        foreign_keys=[enfermeiro_responsavel_id], 
        back_populates="exames_enfermeiro"
    )

    def __repr__(self):
        return f"<Exame(id={self.id}, nome='{self.nome_exame}', paciente_id={self.paciente_id}, status='{self.status}')>"

    @property
    def status_display(self) -> str:
        """Retorna o status em formato legível"""
        status_map = {
            StatusExame.AGENDADO: "Agendado",
            StatusExame.EM_PREPARO: "Em Preparo", 
            StatusExame.EM_EXECUCAO: "Em Execução",
            StatusExame.CONCLUIDO: "Concluído",
            StatusExame.CANCELADO: "Cancelado",
            StatusExame.RESULTADO_DISPONIVEL: "Resultado Disponível"
        }
        return status_map.get(self.status, self.status)

    @property
    def tipo_display(self) -> str:
        """Retorna o tipo em formato legível"""
        tipo_map = {
            TipoExame.LABORATORIAL: "Laboratorial",
            TipoExame.IMAGEM: "Imagem",
            TipoExame.CARDIOLOGICO: "Cardiológico",
            TipoExame.NEUROLÓGICO: "Neurológico", 
            TipoExame.OFTALMOLOGICO: "Oftalmológico",
            TipoExame.AUDITIVO: "Auditivo",
            TipoExame.OUTROS: "Outros"
        }
        return tipo_map.get(self.tipo_exame, self.tipo_exame) 