"""
Configuração do banco de dados
"""

from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from .config import settings

# Configuração específica para PostgreSQL
engine = create_engine(
    settings.database_url,
    pool_pre_ping=True,  # Verifica conexões antes de usar
    pool_recycle=300,    # Recicla conexões a cada 5 minutos
    echo=False           # True para debug SQL
)

SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()

# Dependency para obter sessão do banco
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close() 