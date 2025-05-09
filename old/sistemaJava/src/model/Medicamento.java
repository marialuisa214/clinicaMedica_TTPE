package model;

public class Medicamento {

  //ATRIBUTOS
  String nome;
  String codMedicamento;
  String lab;

  //METODOS
  public Medicamento(String nome, String codMedicamento) {
    this.nome = nome;
    this.codMedicamento = codMedicamento;
  }

  //ATRIBUTOS
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getCodMedicamento() {
    return codMedicamento;
  }
  public void setCodMedicamento(String codMedicamento) {
    this.codMedicamento = codMedicamento;
  }
  public String getLab() {
    return lab;
  }
  public void setLab(String lab) {
    this.lab = lab;
  }
  
}
