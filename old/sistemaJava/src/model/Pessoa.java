package model;

import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Pessoa {

	// ATRIBUTOS
	protected String nome;
	protected String rg;
	
	protected String cpf;
	protected char sexo;
	protected long idade;
	protected String dataNascimento;
	protected String telefone;
	protected String email;
	protected String cidadeEstado;
	protected String endereco;

	
	// CONSTRUTOR
	public Pessoa(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco) {
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		calculaIdade();
		this.telefone = telefone;
		this.email = email;
		this.cidadeEstado = cidade;
		this.endereco = endereco;
	}
	
	// MÉTODOS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
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
	
	public void setIdade(long idade) {
		this.idade = idade;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public void calculaIdade() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		Date dataBase = null;
		Date atual = null;
		
		try {
			dataBase = sdf.parse(this.dataNascimento);
			atual = sdf.parse("06/01/2023");
		} catch (java.text.ParseException e) {
			return;
		}
		
		long diferencaMS = atual.getTime() - dataBase.getTime();
		long diferencaSegundos = diferencaMS / 1000;
		long diferencaMinutos = diferencaSegundos / 60;
		long diferencaHoras = diferencaMinutos / 60;
		long diferencaDias = diferencaHoras / 24;
		long diferencaMeses = diferencaDias / 30;
		long diferencaAnos = diferencaMeses / 12;
		this.idade = diferencaAnos;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCidadeEstado() {
		return cidadeEstado;
	}
	
	public void setCidadeEstado(String cidadeEstado) {
		this.cidadeEstado = cidadeEstado;
	}
	
	public String getEndereço() {
		return endereco;
	}
	
	public void setEndereço(String endereço) {
		this.endereco = endereço;
	}
}
