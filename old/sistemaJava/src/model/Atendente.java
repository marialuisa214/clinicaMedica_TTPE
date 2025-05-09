

package model;

public class Atendente extends Funcionario{

//ATRIBUTOS
	private String idAtendente;

	
//CONSTRUTOR
	public Atendente(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha){
		super(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, usuario, senha); 		
	}
	
//METODO
	public String getIdAtendente() {
			return idAtendente;
	}
	public void setIdAtendente(String a) {
		 this.idAtendente = a;
}

}