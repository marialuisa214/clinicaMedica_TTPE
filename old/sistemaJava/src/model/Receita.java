package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Receita {

  //ATRIBUTOS
  private Paciente paciente;
  private Date dataPedido;
  private Date dataEntrega;
  private String unidade;
  private String descricaoMed;
  private String posologia;
  private String assinatura;
  private List<Medicamento> medicamentos = new ArrayList<>();

  //METODOS
  public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
  public Date getDataPedido() {
    return dataPedido;
  }
  public void setDataPedido(Date dataPedido) {
    this.dataPedido = dataPedido;
  }
  public Date getDataEntrega() {
    return dataEntrega;
  }
  public void setDataEntrega(Date dataEntrega) {
    this.dataEntrega = dataEntrega;
  }
  public String getUnidade() {
    return unidade;
  }
  public void setUnidade(String unidade) {
    this.unidade = unidade;
  }
  public String getDescricaoMed() {
    return descricaoMed;
  }
  public void setDescricaoMed(String descricaoMed) {
    this.descricaoMed = descricaoMed;
  }
  public String getPosologia() {
    return posologia;
  }
  public void setPosologia(String posologia) {
    this.posologia = posologia;
  }
  public String getAssinatura() {
    return assinatura;
  }
  public void setAssinatura(String assinatura) {
    this.assinatura = assinatura;
  }
  public List<Medicamento> getMedicamentos() {
    return medicamentos;
  }
  public void addMedicamentos(Medicamento medicamento) {
    this.medicamentos.add(medicamento);
  }
  
}
