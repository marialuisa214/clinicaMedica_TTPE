package control;

import java.util.ArrayList;

import model.*;

public class ControleDados {
//MEDICO NORMAL
    ArrayList<Medico> bancoMedicos  = new ArrayList<>();
    
    public ArrayList<Medico> getBancoMedicos() {
        return bancoMedicos;
    }
    public void adicionaMedico(Medico novoMedico) {
        this.bancoMedicos.add(novoMedico);
    }

//MEDICO PLANTAO-emergencia
ArrayList<Medico> bancoMedicosPlantao  = new ArrayList<>();
    
public ArrayList<Medico> getBancoMedicosPlantao() {
    return bancoMedicosPlantao;
}

//CONSULTA MARCADA
    ArrayList<Marcada> bancoConsultaMarcadas  = new ArrayList<>();
    
    public ArrayList<Marcada> getBancoConsultaMarcadas() {
        return bancoConsultaMarcadas;
    }
    public void adicionaConsulta(Marcada novaConsultaMarcada) {
        this.bancoConsultaMarcadas.add(novaConsultaMarcada);
    }

//CONSULTAS DE EMERGENCIA
    ArrayList<Emergencia> bancoConsultaEmergencia  = new ArrayList<>();
    
    public ArrayList<Emergencia> getBancoConsultaEmergencias() {
        return bancoConsultaEmergencia;
    }

//PACIENTES
    ArrayList<Paciente> bancoPacientes = new ArrayList<>();

    public ArrayList<Paciente> getBancoPacientes() {
        return bancoPacientes;
    }
    public void adicionaPacientes(Paciente paciente) {
        this.bancoPacientes.add(paciente);
    }
    public void cadastraPaciente(String nome, String rg, String cpf, char sexo,String dataNascimento, String telefone, String email, String cidade, String endereco, String patologia) {
        Paciente paciente = new Paciente(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, patologia);
        this.bancoPacientes.add(paciente);
      }

//ADMINISTRADOR
    ArrayList<Administrador> bancoAdministradores = new ArrayList<>();

    public ArrayList<Administrador> getBancoAdministradores() {
        return bancoAdministradores;
    }

    //LISTA SOLICITACOES
    // ArrayList<Marcada> listaSolicitacaoConsulta = new ArrayList<>();
    // ArrayList<Exame> listaSolicitacaoExame = new ArrayList<>();

    // public ArrayList<Marcada> getListaSolicitacaoConsulta() {
    //     return listaSolicitacaoConsulta;
    // }

    // public ArrayList<Exame> getListaSolicitacaoExame() {
    //     return listaSolicitacaoExame;
    // }


    
//ENFERMEIROS
    ArrayList<Enfermeiro> bancoEnfermeiros = new ArrayList<>();

    public ArrayList<Enfermeiro> getBancoEnfermeiros() {
        return bancoEnfermeiros;
    }
    public void adicionaEnfermeiro(Enfermeiro enfermeiro) {
        this.bancoEnfermeiros.add(enfermeiro);
    }
//Triagem
    ArrayList<Triagem> bancoTriagem = new ArrayList<>();

    public ArrayList<Triagem> getBancoTriagem() {
        return bancoTriagem;
    }
//ENTRADAS
    ArrayList<Entrada> bancoEntradas = new ArrayList<>();

    public ArrayList<Entrada> getaBancoEntradas() {
        
        return bancoEntradas;
    }
    

// Atendente
    ArrayList<Atendente> bancoAtendentes =  new ArrayList<>();
    public ArrayList<Atendente> getaBancoAtendente() {
        return bancoAtendentes;
    }

//EXAMES
    ArrayList<Exame> bancoExames = new ArrayList<>();
    public ArrayList<Exame> getBancoExames() {
        return bancoExames;
    }

//lista consultas para serem feitas pelo atendente
    ArrayList<Marcada> bancoSolicitacoesMarcada  = new ArrayList<>();
    public ArrayList<Marcada> getBancoSolicitacoesMarcada() {
        return bancoSolicitacoesMarcada;
    }

    ArrayList<Exame> bancoSolicitacoeExame  = new ArrayList<>();
    public ArrayList<Exame> getBancoSolicitacoesExame() {
        return bancoSolicitacoeExame;
    }

    



    
    




    
  
}
