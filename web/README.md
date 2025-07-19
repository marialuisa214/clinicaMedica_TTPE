# Frontend - Sistema de Clínica Médica

## 📋 Descrição

Frontend da aplicação de gestão hospitalar desenvolvido em React com TypeScript, seguindo as melhores práticas de desenvolvimento e integrado com a API FastAPI.

## 🛠️ Tecnologias

- **React 18** com TypeScript
- **Material-UI (MUI)** para componentes visuais
- **React Router** para roteamento
- **Axios** para comunicação com API
- **React Hook Form** para formulários
- **Context API** para gerenciamento de estado

## 🚀 Funcionalidades

### Autenticação
- Login seguro com JWT
- Controle de acesso por tipo de usuário
- Proteção de rotas

### Dashboards Específicos
- **Administrador**: Controle geral do sistema
- **Médico**: Agenda e consultas
- **Enfermeiro**: Triagem e exames
- **Atendente**: Cadastros e agendamentos

### Funcionalidades Principais
- **Agenda Médica**: Visualização e gerenciamento de consultas
- **Histórico do Paciente**: Consultas, exames e receitas
- **Gerenciamento de Funcionários**: CRUD completo (apenas admin)
- **Interface Responsiva**: Adaptável a diferentes dispositivos

## 📦 Instalação

### Pré-requisitos
- Node.js 18+ 
- npm ou yarn

### Instalação Local

1. **Navegue para a pasta web**
```bash
cd clinicaMedica_TTPE/web
```

2. **Instale as dependências**
```bash
npm install
```

3. **Execute a aplicação**
```bash
npm start
```

A aplicação será aberta em: http://localhost:3000

## 🔐 Usuários de Teste

Para testar a aplicação, você pode usar os seguintes usuários (configure na API):

```
Administrador:
- Usuário: admin
- Senha: admin123

Médico:
- Usuário: medico01
- Senha: medico123

Enfermeiro:
- Usuário: enfermeiro01
- Senha: enfermeiro123

Atendente:
- Usuário: atendente01
- Senha: atendente123
```

## 📁 Estrutura de Pastas

```
src/
├── components/
│   ├── common/          # Componentes compartilhados
│   ├── auth/           # Componentes de autenticação
│   └── ...
├── pages/
│   ├── admin/          # Páginas do administrador
│   ├── medico/         # Páginas do médico
│   ├── enfermeiro/     # Páginas do enfermeiro
│   ├── paciente/       # Páginas relacionadas a pacientes
│   └── ...
├── services/           # Serviços de API
├── contexts/           # Contextos React
├── types/              # Tipos TypeScript
├── utils/              # Utilitários
└── hooks/              # Custom hooks
```

## 🎨 Páginas Implementadas

### ✅ Completamente Implementadas
- **Login**: Autenticação com validação
- **Dashboard**: Personalizado por tipo de usuário
- **Agenda Médica**: Visualização e gerenciamento de consultas
- **Histórico do Paciente**: Consultas, exames e receitas
- **Gerenciar Funcionários**: CRUD completo para admin

### 🚧 Em Desenvolvimento
- Cadastro de Pacientes
- Triagem (Enfermeiro)
- Agendamentos (Atendente)
- Relatórios (Admin)
- Configurações do Sistema

## 🔄 Integração com API

A aplicação está configurada para se comunicar com a API FastAPI através de:

- **Proxy**: Configurado para `http://localhost:8000`
- **Interceptors**: Adição automática de token JWT
- **Error Handling**: Tratamento de erros 401 (token expirado)

## 🧪 Scripts Disponíveis

```bash
# Desenvolvimento
npm start

# Build para produção
npm run build

# Testes
npm test

# Análise de bundle
npm run analyze
```

## 📱 Responsividade

A aplicação é totalmente responsiva e funciona em:
- Desktop (1920px+)
- Laptop (1024px+)
- Tablet (768px+)
- Mobile (320px+)

## 🔒 Segurança

- Autenticação JWT
- Proteção de rotas
- Validação de formulários
- Sanitização de dados
- CORS configurado

## 🚀 Deploy

### Build de Produção
```bash
npm run build
```

### Docker
```bash
# Usar dockerfile específico para frontend
docker build -t clinica-frontend .
docker run -p 3000:80 clinica-frontend
```

## 📞 Suporte

Para suporte técnico ou dúvidas sobre o frontend:
- Verifique a documentação da API
- Confira se a API está rodando em `http://localhost:8000`
- Verifique os logs do console do navegador

---

⭐ **Interface moderna e intuitiva para gestão hospitalar!** ⭐ 