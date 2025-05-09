package model;

import java.util.ArrayList;

public class Paciente extends Pessoa {

//ATRIBUTOS	
	private String patologia; //cormobidade, restrição médica
	private ArrayList<Receita> listaReceitas = new ArrayList<>();
	private Prontuario prontuario;
	
	//CONSTRUTOR	
	public Paciente(String nome, String rg, String cpf, char sexo,String dataNascimento, String telefone, String email, String cidade, String endereco, String patologia){
		super(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco);
		this.patologia = patologia; 
		this.prontuario = new Prontuario();
	}
	//METODOS
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public char getSexo() {
	return sexo;
}

public void setSexo(char sexo) {
	this.sexo = sexo;
}
	
public long getIdade() {
	return idade;
}

public String getDataNascimento() {
	return dataNascimento;
}

public void setDataNascimento(String dataNascimento) {
	this.dataNascimento = dataNascimento;
}

public String getPatologia() {
	return patologia;
}

public void setPatologia(String patologia) {
	this.patologia = patologia;
}

public Prontuario getProntuario() {
	return prontuario;
}

public void setProntuario(Prontuario prontuario) {
	this.prontuario = prontuario;
}
public ArrayList<Receita> getListaReceitas() {
	return listaReceitas;
}

public void setListaReceitas(ArrayList<Receita> listaReceitas) {
	this.listaReceitas = listaReceitas;
}


}
