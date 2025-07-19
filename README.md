# 🏥 Sistema de Clínica Médica

Sistema completo de gestão de clínica médica desenvolvido com **FastAPI** (backend), **React** (frontend) e **PostgreSQL** (banco de dados).

## 🚀 Status do Sistema

✅ **SISTEMA TOTALMENTE FUNCIONAL!**

- ✅ **Backend FastAPI**: http://localhost:8000
- ✅ **Frontend React**: http://localhost:3000  
- ✅ **PostgreSQL**: localhost:5432
- ✅ **Documentação API**: http://localhost:8000/api/v1/docs

## 📋 Requisitos

- Docker
- Docker Compose

## 🔧 Como Executar

### Opção 1: Script Automatizado
```bash
./start.sh
# Escolha a opção 1 para inicializar o sistema completo
```

### Opção 2: Docker Compose Manual
```bash
# Iniciar todos os serviços
docker compose up --build -d

# Verificar status
docker compose ps

# Ver logs
docker compose logs -f
```

## 👥 Usuários Padrões

O sistema vem com usuários pré-configurados para teste:

| Tipo | Usuário | Senha | Email |
|------|---------|--------|-------|
| **Administrador** | `admin` | `admin123` | admin@clinica.com |
| **Médico** | `medico01` | `medico123` | joao.silva@clinica.com |
| **Enfermeiro** | `enfermeiro01` | `enfermeiro123` | maria.santos@clinica.com |
| **Atendente** | `atendente01` | `atendente123` | ana.costa@clinica.com |
| **Farmacêutico** | `farmaceutico01` | `farmaceutico123` | carlos.farm@clinica.com |

## 🔐 Testando a API

### Login
```bash
curl -X 'POST' 'http://localhost:8000/api/v1/auth/login' \
  -H 'Content-Type: application/json' \
  -d '{"usuario": "admin", "senha": "admin123"}'
```

### Health Check
```bash
curl http://localhost:8000/health
```

## 🌐 URLs Importantes

- **Frontend**: http://localhost:3000
- **API Backend**: http://localhost:8000
- **Documentação Swagger**: http://localhost:8000/api/v1/docs
- **Redoc**: http://localhost:8000/api/v1/redoc

## 🛠️ Comandos Úteis

```bash
# Parar todos os serviços
docker compose down

# Reconstruir containers
docker compose build --no-cache

# Ver logs específicos
docker compose logs api
docker compose logs web
docker compose logs db

# Acessar banco de dados
docker compose exec db psql -U clinica_user -d clinica_medica

# Limpar tudo e recomeçar
docker compose down -v
docker system prune -f
```

## 🏗️ Arquitetura

### Backend (FastAPI)
- **Arquitetura Limpa** com separação de responsabilidades
- **Princípios SOLID** aplicados
- **JWT Authentication** com roles
- **PostgreSQL** com SQLAlchemy ORM
- **Documentação automática** com Swagger/OpenAPI

### Frontend (React + TypeScript)
- **Material-UI** para interface moderna
- **React Router** para navegação
- **Context API** para gerenciamento de estado
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

## 🔒 Funcionalidades Implementadas

### Autenticação e Autorização
- ✅ Login com JWT
- ✅ Controle de acesso por roles
- ✅ Middleware de autenticação
- ✅ Proteção de rotas no frontend

### Gestão de Funcionários
- ✅ CRUD completo de funcionários
- ✅ Diferentes tipos: Admin, Médico, Enfermeiro, Atendente, Farmacêutico
- ✅ Campos específicos por tipo (CRM, COREN, CRF)

### Gestão de Pacientes
- ✅ Cadastro de pacientes
- ✅ Busca por nome e CPF
- ✅ Histórico de consultas

### Interface Responsiva
- ✅ Design moderno com Material-UI
- ✅ Dashboards específicos por role
- ✅ Formulários validados
- ✅ Navegação intuitiva

## 🐛 Troubleshooting

### Container não inicia
```bash
# Limpar volumes e reconstruir
docker compose down -v
docker system prune -f
docker compose up --build -d
```

### Problemas de login
- Verifique se o banco foi inicializado corretamente
- Confirme se os usuários padrão foram criados
- Veja os logs: `docker compose logs api`

### Frontend não carrega
- Verifique se os arquivos estáticos estão sendo servidos
- Confirme o build do React: `docker compose logs web`

## 🚀 Desenvolvimento

Para desenvolvimento local:

1. **Backend**: 
   ```bash
   cd app
   pip install -r requirements.txt
   uvicorn main:app --reload
   ```

2. **Frontend**: 
   ```bash
   cd web
   npm install
   npm start
   ```

## 📞 Suporte

Sistema desenvolvido seguindo as melhores práticas de:
- Clean Architecture
- SOLID Principles  
- RESTful APIs
- Modern React Patterns
- Docker Best Practices

---

**🎉 Sistema pronto para uso! Acesse http://localhost:3000 e faça login com as credenciais acima.**



