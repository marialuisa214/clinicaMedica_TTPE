"""
Router principal da API
"""

from fastapi import APIRouter

from app.core.config import settings
from app.api.v1 import auth, pacientes, funcionarios, consultas, exames, atendimentos

# Router principal da API v1
api_router = APIRouter()

# Incluir rotas de autenticação
api_router.include_router(auth.router, prefix="/auth", tags=["Autenticação"])

# Incluir rotas de pacientes
api_router.include_router(pacientes.router, prefix="/pacientes", tags=["Pacientes"])

# Incluir rotas de funcionários
api_router.include_router(funcionarios.router, prefix="/funcionarios", tags=["Funcionários"])

# Incluir rotas de consultas
api_router.include_router(consultas.router, prefix="/consultas", tags=["Consultas"])

# Incluir rotas de exames
api_router.include_router(exames.router, prefix="/exames", tags=["Exames"])

# Incluir rotas de atendimentos
api_router.include_router(atendimentos.router, prefix="/atendimentos", tags=["Atendimentos"])

# Endpoint de health check
@api_router.get("/health")
def health_check():
    """Endpoint para verificar se a API está funcionando"""
    return {
        "status": "healthy",
        "service": settings.project_name,
        "version": settings.version
    } 