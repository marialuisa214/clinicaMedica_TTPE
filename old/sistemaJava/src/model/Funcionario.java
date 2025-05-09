package model;

public class Funcionario extends Pessoa {
	
//ATRIBUTOS	
	private String usuario;
	private String senha;
	
//CONSTRUTOR	
	public Funcionario(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha){
		super(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco);
		this.usuario = usuario;
		this.senha = senha;
	}
	
//METODOS
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}