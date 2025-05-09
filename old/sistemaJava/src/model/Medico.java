package model;

import java.util.ArrayList;

public class Medico extends Funcionario {
//ATRIBUTOS
	private String especialidade;
	private String crm;
	// private ArrayList<Agenda> listaAgenda = new ArrayList<>();
	private ArrayList<Marcada> listConsultasPendentes = new ArrayList<>();



//CONSTRUTOR	
public Medico(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha, String crm, String especialidade){
	super(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, usuario, senha);		 		
	this.crm = crm;
	this.especialidade = especialidade;
}

	
	
//METODOS
	
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	// public ArrayList<Agenda> getAgenda() {
	// 	return listaAgenda;
	// }
	// public void setAgenda(ArrayList<Agenda> agenda) {
	// 	this.listaAgenda = agenda;
	// }
	
	// public void adicionaAgenda(Agenda agenda){
	// 	 this.listaAgenda.add(agenda);
	// }

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public ArrayList<Marcada> getListConsultasPendentes() {
		return listConsultasPendentes;
	}



	public void setListConsultasPendentes(ArrayList<Marcada> listConsultasPendentes) {
		this.listConsultasPendentes = listConsultasPendentes;
	}

	//criar receita para o paciente
	//assinar/confirma receita
	//atestado médico?
	

}
