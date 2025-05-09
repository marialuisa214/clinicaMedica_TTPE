package model;

public class Farmaceutico extends Funcionario {
//ATRIBUTOS
	private int crf;

	//CONSTRUTOR
	public Farmaceutico(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha, int crf){
		super(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, usuario, senha);				
		this.crf = crf;
	}
	
//METODOS
	public int getCrf() {
		return crf;
	}
	public void setCrf(int crf) {
		this.crf = crf;
	}


}
