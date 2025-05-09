package model;

import java.util.ArrayList;
import java.util.List;

public class Farmacia {

  //ATRIBUTOS
  private String categoria;
  private ArrayList<Medicamento> listaMedicamento = new ArrayList<>();
  private int quantidadeMedicamento;
  private Farmaceutico farmaceutico;
  
  //CONSTRUTOR
  public Farmacia(String categoria, Farmaceutico farmaceutico, List<Medicamento> medicamentos,
      int quantidadeMedicamento) {
    this.categoria = categoria;
    this.farmaceutico = farmaceutico;
    this.quantidadeMedicamento = quantidadeMedicamento;
  } 

  //METODOS
  public String getCategoria() {
    return categoria;
  }
  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }
  public Farmaceutico getFarmaceutico() {
    return farmaceutico;
  }
  public void setFarmaceutico(Farmaceutico farmaceutico) {
    this.farmaceutico = farmaceutico;
  }
  public List<Medicamento> getListaMedicamento() {
    return listaMedicamento;
  }
  public int getQuantidadeMedicamento() {
    return quantidadeMedicamento;
  }
  public void setQuantidadeMedicamento(int quantidadeMedicamento) {
    this.quantidadeMedicamento = quantidadeMedicamento;
  }

}
