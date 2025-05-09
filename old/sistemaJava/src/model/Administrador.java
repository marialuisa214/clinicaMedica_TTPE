package model;

public class Administrador extends Funcionario{
	
	//ATRIBUTO
	private String idAdmin; 
	
	
	//CONSTRUTOR
	public Administrador(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha){
		super(nome, rg, cpf, sexo, dataNascimento, telefone, email, cidade, endereco, usuario, senha); 		
	}
	
	//METODOS
		public String getIdAdmin() {
			return idAdmin;
		}	
	
		public void setIdAdmin(String idAdmin) {
			this.idAdmin = idAdmin;
		}
}

