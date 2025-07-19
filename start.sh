#!/bin/bash

echo "🚀 Sistema de Clínica Médica - Inicialização Completa"
echo "====================================================="

# Função para verificar se um comando existe
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Verificar dependências
echo "📋 Verificando dependências..."

if ! command_exists docker; then
    echo "❌ Docker não encontrado. Instale Docker"
    exit 1
fi

# Verificar se docker compose está disponível
if ! docker compose version >/dev/null 2>&1; then
    echo "❌ Docker Compose não encontrado. Instale Docker Compose"
    exit 1
fi

echo "✅ Dependências verificadas!"

echo ""
echo "🎯 Escolha uma opção:"
echo "1. Inicializar sistema completo (PostgreSQL + API + Frontend)"
echo "2. Parar todos os serviços"
echo "3. Reiniciar serviços"
echo "4. Ver logs dos serviços"
echo "5. Acessar banco de dados PostgreSQL"
echo "6. Executar migrações do banco"
echo "7. Status dos serviços"
echo "8. Limpar volumes e reconstruir (resolve problemas de banco)"

read -p "Digite sua opção (1-8): " option

case $option in
    1)
        echo "🚀 Iniciando sistema completo..."
        echo ""
        echo "📊 Serviços que serão iniciados:"
        echo "- PostgreSQL: http://localhost:5432"
        echo "- API FastAPI: http://localhost:8000"
        echo "- Frontend React: http://localhost:3000"
        echo "- Documentação API: http://localhost:8000/api/v1/docs"
        echo ""
        
        # Construir e iniciar todos os serviços
        docker compose up --build -d
        
        echo ""
        echo "⏳ Aguardando serviços iniciarem..."
        sleep 15
        
        echo ""
        echo "✅ Sistema iniciado com sucesso!"
        echo ""
        echo "🔗 Links importantes:"
        echo "Frontend: http://localhost:3000"
        echo "API: http://localhost:8000"
        echo "API Docs: http://localhost:8000/api/v1/docs"
        echo ""
        echo "👥 Usuários padrões criados:"
        echo "admin / admin123 (Administrador)"
        echo "medico01 / medico123 (Médico)"
        echo "enfermeiro01 / enfermeiro123 (Enfermeiro)"
        echo "atendente01 / atendente123 (Atendente)"
        echo "farmaceutico01 / farmaceutico123 (Farmacêutico)"
        echo ""
        echo "Para ver logs: docker compose logs -f"
        echo "Para parar: docker compose down"
        ;;
    2)
        echo "⏹️ Parando todos os serviços..."
        docker compose down
        echo "✅ Serviços parados!"
        ;;
    3)
        echo "🔄 Reiniciando serviços..."
        docker compose restart
        echo "✅ Serviços reiniciados!"
        ;;
    4)
        echo "📋 Logs dos serviços (Ctrl+C para sair):"
        docker compose logs -f
        ;;
    5)
        echo "🗄️ Conectando ao PostgreSQL..."
        echo "Dados de conexão:"
        echo "Host: localhost"
        echo "Port: 5432"
        echo "Database: clinica_medica"
        echo "User: clinica_user"
        echo "Password: clinica_password"
        echo ""
        docker compose exec db psql -U clinica_user -d clinica_medica
        ;;
    6)
        echo "🔄 Executando migrações do banco..."
        docker compose exec api python -c "from app.utils.init_db import init_database; init_database()"
        echo "✅ Migrações executadas!"
        ;;
    7)
        echo "📊 Status dos serviços:"
        docker compose ps
        ;;
    8)
        echo "🧹 Limpando volumes e reconstruindo..."
        echo "⚠️  Isso irá apagar todos os dados do banco!"
        read -p "Tem certeza? (s/N): " confirm
        if [[ $confirm == [sS] ]]; then
            docker compose down -v
            docker system prune -f
            echo "✅ Limpeza concluída! Execute a opção 1 para reconstruir."
        else
            echo "Operação cancelada."
        fi
        ;;
    *)
        echo "❌ Opção inválida"
        exit 1
        ;;
esac 