"""
Configurações da aplicação
"""

from pydantic_settings import BaseSettings
from pydantic import ConfigDict
from typing import Optional, List
import os

class Settings(BaseSettings):
    # Metadados da aplicação
    project_name: str = "Sistema de Clínica Médica"
    version: str = "1.0.0"
    description: str = "API para gestão de clínica médica com FastAPI"
    api_v1_str: str = "/api/v1"
    
    # Database
    database_url: str = "postgresql://clinica_user:clinica_password@db:5432/clinica_medica"
    
    # Security
    secret_key: str = "sua-chave-secreta-muito-segura-mude-em-producao-123456789"
    algorithm: str = "HS256"
    access_token_expire_minutes: int = 30
    
    # CORS
    backend_cors_origins: str = '["http://localhost:3000","http://127.0.0.1:3000","http://localhost:8000"]'
    
    # Environment
    environment: str = "development"
    log_level: str = "INFO"
    
    # Usuários padrão do sistema
    default_admin_user: str = "admin"
    default_admin_password: str = "admin123"
    default_admin_name: str = "Administrador do Sistema"
    default_admin_email: str = "admin@clinica.com"
    
    @property
    def cors_origins(self) -> List[str]:
        """Converte string JSON para lista de origins"""
        import json
        if isinstance(self.backend_cors_origins, str):
            try:
                return json.loads(self.backend_cors_origins)
            except:
                return ["http://localhost:3000"]
        return self.backend_cors_origins
    
    model_config = ConfigDict(
        env_file=".env",
        case_sensitive=False,
        extra='allow'  # Permite campos extras do .env
    )

settings = Settings() 