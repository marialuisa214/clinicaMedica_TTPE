package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Entrada {
  //ATRIBUTOS
  private String dataEntrada;
  private String situacaoPaciente;
  private Paciente pacienteVinculado;
  private Date c;

  //CONSTRUTOR
  public Entrada(String situacaoPaciente, Paciente paciente){
    this.situacaoPaciente = situacaoPaciente;
    this.pacienteVinculado = paciente;
    this.c = new Date();
    System.out.println(formatador.format(c));
    this.dataEntrada = formatador.format(c);
  }

  //METODOS
  public String getDataEntrada() {
    return dataEntrada;
  }
  public void setDataEntrada(String dataEntrada) {
    this.dataEntrada = dataEntrada;
  }
  
  public String getSituacaoPaciente() {
    return situacaoPaciente;
  }
  public void setSituacaoPaciente(String situacaoPaciente) {
    this.situacaoPaciente = situacaoPaciente;
  }
  public Paciente getPacienteVinculado() {
    return pacienteVinculado;
  }
  public void setPacienteVinculado(Paciente pacienteVinculado) {
    this.pacienteVinculado = pacienteVinculado;
  }

  private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:dd");
}
