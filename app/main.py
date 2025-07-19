"""
Aplicação principal FastAPI - Sistema de Clínica Médica
"""

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from fastapi.middleware.trustedhost import TrustedHostMiddleware
from contextlib import asynccontextmanager

from app.core.config import settings
from app.core.database import engine
from app.api.router import api_router
from app.utils.init_db import init_database
import logging

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

@asynccontextmanager
async def lifespan(app: FastAPI):
    logger.info("🚀 Inicializando aplicação...")
    
    from app.models import funcionario, paciente, consulta
    
    from app.core.database import Base
    Base.metadata.create_all(bind=engine)
    logger.info("📊 Tabelas criadas com sucesso!")
    
    try:
        init_database()
        logger.info("✅ Dados iniciais carregados!")
    except Exception as e:
        logger.error(f"⚠️ Erro ao carregar dados iniciais: {e}")
    
    yield
    
    logger.info("🛑 Aplicação finalizada")
app = FastAPI(
    title=settings.project_name,
    version=settings.version,
    description=settings.description,
    openapi_url=f"{settings.api_v1_str}/openapi.json",
    docs_url=f"{settings.api_v1_str}/docs",
    redoc_url=f"{settings.api_v1_str}/redoc",
    lifespan=lifespan
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=settings.cors_origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.add_middleware(
    TrustedHostMiddleware,
    allowed_hosts=["*"]
)

app.include_router(api_router, prefix=settings.api_v1_str)

@app.get("/health")
def root_health_check():
    """Health check na raiz"""
    return {"status": "healthy", "message": "Clínica Médica API is running"}
@app.get("/")
def read_root():
    """Rota raiz da API"""
    return {
        "message": "Sistema de Clínica Médica API", 
        "version": settings.version,
        "docs": f"{settings.api_v1_str}/docs"
    }

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(
        "app.main:app",
        host="0.0.0.0",
        port=8000,
        reload=True,
        log_level="info"
    ) 