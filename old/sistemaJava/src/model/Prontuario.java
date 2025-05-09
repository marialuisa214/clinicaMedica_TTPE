package model;

import java.util.ArrayList;

public class Prontuario {

	// ATRIBUTOS
	private Paciente paciente;
	private String acompanhamento; // descrição médica
	private String peso;
	private String altura;
	private Boolean validado;
	private ArrayList<Marcada> historicoConsultasMarcadas = new ArrayList<>();
	private ArrayList<Emergencia> historicoConsultasEmergencia = new ArrayList<>();
	private ArrayList<Exame> historicoExame =  new ArrayList<>();

	// CONSTRUTOR
	public Prontuario() {
		this.validado = false;
		// ou seja, sempre ao criar um prontuario ele vem sem os dados,
		// só podendo ser passar a ser 'true' quando preenchido por outra classe

	}

	// METODOS

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getAcompanhamento() {
		return acompanhamento;
	}

	public void setAcompanhamento(String acompanhamento) {
		this.acompanhamento = acompanhamento;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public Boolean getValidado() {
		return validado;
	}

	public void setValidado(Boolean validado) {
		this.validado = validado;
	}

	public ArrayList<Marcada> getHistoricoConsultasMarcadas() {
		return historicoConsultasMarcadas;
	}

	public void setHistoricoConsultasMarcadas(Marcada consulta) {
		historicoConsultasMarcadas.add(consulta);
	}

	public ArrayList<Exame> getHistoricoExame() {
		return historicoExame;
	}

	public void setHistoricoExame(Exame exame) {
		historicoExame.add(exame);
	}

	public ArrayList<Emergencia> getHistoricoConsultasEmergencia() {
		return historicoConsultasEmergencia;
	}

	public void setHistoricoConsultasEmergencia(ArrayList<Emergencia> historicoConsultasEmergencia) {
		this.historicoConsultasEmergencia = historicoConsultasEmergencia;
	}

	// public ArrayList<Consulta> getHistoricoConsultas() {
	// return historicoConsultas;
	// }
	// public void adicionaConsulta(Consulta consulta){
	// historicoConsultas.add(consulta);
	// }
	// public ArrayList<Exame> getHistoricoExame() {
	// return historicoExame;
	// }
	// public void adicionaExame(Exame exame){
	// historicoExame.add(exame);
	// }
		
	}