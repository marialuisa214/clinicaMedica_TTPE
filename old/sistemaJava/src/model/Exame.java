package model;

public class Exame {
    //atributos
    private String nomeExame;
    private String data;
    private String Horario;
    private String codigo;
    private Paciente paciente;
    private Funcionario responsavelTecnico;
    // private Agenda agenda;
    private String Resultado;
    private boolean status; //feito=true ou pendente=false
    
    //construtor
    public Exame(String nomeExame, String codigo, Paciente paciente){
        this.nomeExame = nomeExame;
        this.codigo = codigo;
        this.paciente = paciente;
        this.status = false;
    }
    //métodos 
    public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    public String getNomeExame() {
        return nomeExame;
    }
    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }
    public String getCodigo() {
        return codigo;
    }  
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Funcionario getResponsavelTecnico() {
      return responsavelTecnico;
    }

    public void setResponsavelTecnico(Funcionario responsavelTecnico) {
      this.responsavelTecnico = responsavelTecnico;
    }

    // public Agenda getAgenda() {
    //     return agenda;
    // }

    // public void setAgenda(Agenda agenda) {
    //     this.agenda = agenda;
    // }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getResultado() {
        return Resultado;
    }
    public void setResultado(String resultado) {
        Resultado = resultado;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
