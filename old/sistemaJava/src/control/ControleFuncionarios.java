package control;

import model.*;

public class ControleFuncionarios {

  // METODOS
  // ADMINISTRADOR
  public Medico cadastraMedico(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha, String crm, String especialidade) {
    return new Medico(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, usuario, senha, crm, especialidade);
  }

  public Enfermeiro cadastraEnfermeiro(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha, String corem) {
    return new Enfermeiro(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, corem, usuario, senha);
  }

  public Farmaceutico cadastraFarmaceutico(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha, int crf) {
    return new Farmaceutico(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, usuario, senha, crf);
  }

  public Atendente cadastraAtendente(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha) {
    return new Atendente(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, usuario, senha);
  }

  // ATENDENTE
  public Paciente cadastraPaciente(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String patologia) {
    return new Paciente(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, patologia);
  }

}