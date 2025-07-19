# Sistema de Clínica Médica

Sistema de gestão de clínica médica desenvolvido com **FastAPI**, **React** e **PostgreSQL**.

**SISTEMA TOTALMENTE FUNCIONAL!**

- **Backend FastAPI**: http://localhost:8000
- **Frontend React**: http://localhost:3000  
- **PostgreSQL**: localhost:5432
- **Documentação API**: http://localhost:8000/api/v1/docs

### Docker Compose
```bash
# Iniciar todos os serviços
docker compose up --build -d

# Iniciar Testes 
docker compose --profile test run --rm tests pytest tests/test_parametrized_validation.py -v
```

## Usuários

Usuários pré-configurados para teste:

| Tipo | Usuário | Senha | Email |
|------|---------|--------|-------|
| **Administrador** | `admin` | `admin123` | admin@clinica.com |
| **Médico** | `medico01` | `medico123` | joao.silva@clinica.com |
| **Enfermeiro** | `enfermeiro01` | `enfermeiro123` | maria.santos@clinica.com |
| **Atendente** | `atendente01` | `atendente123` | ana.costa@clinica.com |
| **Farmacêutico** | `farmaceutico01` | `farmaceutico123` | carlos.farm@clinica.com |


##  URLs

- **Frontend**: http://localhost:3000
- **API Backend**: http://localhost:8000
- **Documentação Swagger**: http://localhost:8000/api/v1/docs
- **Redoc**: http://localhost:8000/api/v1/redoc

## 🏗️ Arquitetura

### Backend (FastAPI)
- **Arquitetura** DDD e Arq em camadas
- **Princípios SOLID** aplicados
- **JWT Authentication**
- **PostgreSQL** com SQLAlchemy 
- **Documentação automática** com Swagger/OpenAPI

### Frontend (React + TypeScript)
- **Axios** para comunicação com API
- **Formulários validados** com React Hook Form

### Banco de Dados
- **PostgreSQL 15** com Docker
- **Inicialização automática** com usuários padrão
- **Schemas organizados** por domínio

## 📁 Estrutura do Projeto

```
clinicaMedica_TTPE/
├── app/                    # Backend FastAPI
│   ├── api/               # Endpoints da API
│   ├── core/              # Configurações
│   ├── models/            # Modelos SQLAlchemy
│   ├── schemas/           # Schemas Pydantic
│   ├── services/          # Lógica de negócio
│   ├── repositories/      # Acesso a dados
│   └── utils/             # Utilitários
├── web/                   # Frontend React
│   ├── src/
│   │   ├── components/    # Componentes React
│   │   ├── pages/         # Páginas/Telas
│   │   ├── services/      # APIs
│   │   └── contexts/      # Contextos React
│   ├── nginx.conf         # Configuração Nginx
│   └── Dockerfile         # Docker do frontend
├── docker-compose.yml     # Orquestração Docker
├── start.sh              # Script de inicialização
└── README.md             # Este arquivo
```

