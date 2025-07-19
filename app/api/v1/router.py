"""
Router principal da API v1
"""

from fastapi import APIRouter

from app.api.v1 import auth, pacientes, funcionarios, consultas

api_router = APIRouter()

api_router.include_router(auth.router, prefix="/auth", tags=["autenticação"])
api_router.include_router(pacientes.router, prefix="/pacientes", tags=["pacientes"])
api_router.include_router(funcionarios.router, prefix="/funcionarios", tags=["funcionários"])
api_router.include_router(consultas.router, prefix="/consultas", tags=["consultas"]) 