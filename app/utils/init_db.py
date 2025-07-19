"""
Script para inicialização do banco de dados
"""

import logging
from sqlalchemy.orm import sessionmaker

from app.core.database import engine, get_db
from app.models.funcionario import Funcionario, Administrador, Medico, Enfermeiro, Atendente, Farmaceutico
from app.utils.security import get_password_hash

logger = logging.getLogger(__name__)

def create_default_users(db):
    """Criar usuários padrões do sistema"""
    logger.info("Criando usuários padrões...")
    
    try:
        # Verificar se admin já existe
        existing_admin = db.query(Funcionario).filter(
            Funcionario.usuario == "admin"
        ).first()
        
        if not existing_admin:
            # Criar Administrador
            admin = Administrador(
                nome="Administrador do Sistema",
                usuario="admin",
                senha_hash=get_password_hash("admin123"),
                email="admin@clinica.com",
                tipo="administrador",
                setor="Administração"
            )
            db.add(admin)
            logger.info("✅ Usuário admin criado")
        else:
            logger.info("ℹ️ Usuário admin já existe")
            
    except Exception as e:
        logger.error(f"❌ Erro ao criar usuário admin: {e}")

    try:
        # Verificar se médico já existe
        existing_medico = db.query(Funcionario).filter(
            Funcionario.usuario == "medico01"
        ).first()
        
        if not existing_medico:
            # Criar Médico
            medico = Medico(
                nome="Dr. João Silva",
                usuario="medico01",
                senha_hash=get_password_hash("medico123"),
                email="joao.silva@clinica.com",
                tipo="medico",
                crm="CRM12345-SP",
                especialidade="Cardiologia"
            )
            db.add(medico)
            logger.info("✅ Usuário medico01 criado")
        else:
            logger.info("ℹ️ Usuário medico01 já existe")
            
    except Exception as e:
        logger.error(f"❌ Erro ao criar usuário medico01: {e}")

    try:
        # Verificar se enfermeiro já existe
        existing_enfermeiro = db.query(Funcionario).filter(
            Funcionario.usuario == "enfermeiro01"
        ).first()
        
        if not existing_enfermeiro:
            # Criar Enfermeiro
            enfermeiro = Enfermeiro(
                nome="Maria Santos",
                usuario="enfermeiro01",
                senha_hash=get_password_hash("enfermeiro123"),
                email="maria.santos@clinica.com",
                tipo="enfermeiro",
                coren="COREN12345-SP"
            )
            db.add(enfermeiro)
            logger.info("✅ Usuário enfermeiro01 criado")
        else:
            logger.info("ℹ️ Usuário enfermeiro01 já existe")
            
    except Exception as e:
        logger.error(f"❌ Erro ao criar usuário enfermeiro01: {e}")

    try:
        # Verificar se atendente já existe
        existing_atendente = db.query(Funcionario).filter(
            Funcionario.usuario == "atendente01"
        ).first()
        
        if not existing_atendente:
            # Criar Atendente
            atendente = Atendente(
                nome="Ana Costa",
                usuario="atendente01",
                senha_hash=get_password_hash("atendente123"),
                email="ana.costa@clinica.com",
                tipo="atendente",
                setor="Recepção"
            )
            db.add(atendente)
            logger.info("✅ Usuário atendente01 criado")
        else:
            logger.info("ℹ️ Usuário atendente01 já existe")
            
    except Exception as e:
        logger.error(f"❌ Erro ao criar usuário atendente01: {e}")

    try:
        # Verificar se farmacêutico já existe
        existing_farmaceutico = db.query(Funcionario).filter(
            Funcionario.usuario == "farmaceutico01"
        ).first()
        
        if not existing_farmaceutico:
            # Criar Farmacêutico
            farmaceutico = Farmaceutico(
                nome="Carlos Farmacêutico",
                usuario="farmaceutico01",
                senha_hash=get_password_hash("farmaceutico123"),
                email="carlos.farm@clinica.com",
                tipo="farmaceutico",
                crf="CRF12345-SP"
            )
            db.add(farmaceutico)
            logger.info("✅ Usuário farmaceutico01 criado")
        else:
            logger.info("ℹ️ Usuário farmaceutico01 já existe")
            
    except Exception as e:
        logger.error(f"❌ Erro ao criar usuário farmaceutico01: {e}")

    # Commit das alterações
    try:
        db.commit()
        logger.info("✅ Todos os usuários padrões foram criados com sucesso!")
    except Exception as e:
        logger.error(f"❌ Erro ao salvar usuários: {e}")
        db.rollback()

def init_database():
    """Inicializar banco de dados"""
    logger.info("🚀 Inicializando banco de dados...")
    
    # Criar tabelas
    try:
        logger.info("Criando tabelas no banco de dados...")
        from app.core.database import Base
        from app.models import funcionario, paciente, consulta
        Base.metadata.create_all(bind=engine)
        logger.info("✅ Tabelas criadas com sucesso!")
    except Exception as e:
        logger.error(f"❌ Erro na criação das tabelas: {e}")
        return

    # Criar sessão
    SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
    db = SessionLocal()
    
    try:
        # Criar usuários padrão
        create_default_users(db)
        
        # Log final
        logger.info("✅ Inicialização do banco concluída!")
        
        # Mostrar informações dos usuários criados
        print("\n" + "="*50)
        print("👥 USUÁRIOS PADRÕES CRIADOS")
        print("="*50)
        print("ADMINISTRADOR:")
        print("  Usuário: admin")
        print("  Senha: admin123")
        print("  Email: admin@clinica.com")
        print()
        print("MÉDICO:")
        print("  Usuário: medico01")
        print("  Senha: medico123")
        print("  Email: joao.silva@clinica.com")
        print("  CRM: CRM12345-SP")
        print("  Especialidade: Cardiologia")
        print()
        print("ENFERMEIRO:")
        print("  Usuário: enfermeiro01")
        print("  Senha: enfermeiro123")
        print("  Email: maria.santos@clinica.com")
        print("  COREN: COREN12345-SP")
        print()
        print("ATENDENTE:")
        print("  Usuário: atendente01")
        print("  Senha: atendente123")
        print("  Email: ana.costa@clinica.com")
        print()
        print("FARMACÊUTICO:")
        print("  Usuário: farmaceutico01")
        print("  Senha: farmaceutico123")
        print("  Email: carlos.farm@clinica.com")
        print("  CRF: CRF12345-SP")
        print("="*50)
        
    except Exception as e:
        logger.error(f"❌ Erro na inicialização: {e}")
        db.rollback()
    finally:
        db.close()

if __name__ == "__main__":
    init_database() 