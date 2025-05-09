package model;

import java.util.ArrayList;

public class Enfermeiro extends Funcionario {
	private String corem;
	

	private ArrayList<Triagem> listaTriagem =new ArrayList<>();
	private ArrayList<Exame> listaExame = new ArrayList<>();
	
	//CONSTRUTOR	
	public Enfermeiro(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha, String corem){
		super(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, usuario, senha);	
		this.corem = corem;
	}

	//METODO

	public String getCorem() {
		return corem;
	}

	public void setCorem(String corem) {
		this.corem = corem;
	}
	
	public ArrayList<Triagem> getListaTriagem() {
		return listaTriagem;
	}
	public void setListaTriagem(ArrayList<Triagem> listaTriagem) {
		this.listaTriagem = listaTriagem;
	}

	public ArrayList<Exame> getListaExame() {
		return listaExame;
	}

	public void setListaExame(ArrayList<Exame> listaExame) {
		this.listaExame = listaExame;
	}


	

}
