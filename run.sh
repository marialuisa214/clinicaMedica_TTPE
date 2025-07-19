#!/bin/bash

echo "🚀 Sistema de Clínica Médica - FastAPI + React"
echo "=============================================="

# Função para verificar se um comando existe
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Verificar dependências
echo "📋 Verificando dependências..."

if ! command_exists node; then
    echo "❌ Node.js não encontrado. Instale Node.js 18+"
    exit 1
fi

if ! command_exists npm; then
    echo "❌ npm não encontrado. Instale npm"
    exit 1
fi

echo "✅ Dependências verificadas!"

# Instalar dependências do frontend
echo ""
echo "📦 Instalando dependências do frontend..."
cd web

if [ ! -d "node_modules" ]; then
    echo "Instalando dependências do npm..."
    npm install
else
    echo "Dependências já instaladas!"
fi

echo ""
echo "🎯 Escolha uma opção:"
echo "1. Executar apenas o frontend"
echo "2. Executar frontend em modo de desenvolvimento"
echo "3. Build para produção"
echo "4. Executar testes do frontend"

read -p "Digite sua opção (1-4): " option

case $option in
    1)
        echo "🚀 Iniciando frontend..."
        echo "Frontend: http://localhost:3000"
        echo "Conectando com API: http://localhost:8000"
        npm start
        ;;
    2)
        echo "🚀 Iniciando frontend em modo desenvolvimento..."
        echo "Frontend: http://localhost:3000"
        echo "Conectando com API: http://localhost:8000"
        echo "Hot reload ativado!"
        npm start
        ;;
    3)
        echo "🏗️ Criando build de produção..."
        npm run build
        echo "✅ Build criado na pasta 'build'"
        ;;
    4)
        echo "🧪 Executando testes do frontend..."
        npm test -- --coverage --watchAll=false
        ;;
    *)
        echo "❌ Opção inválida"
        exit 1
        ;;
esac 