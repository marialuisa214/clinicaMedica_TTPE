package model;

public abstract class Consulta {

    //ATRIBUTOS
    protected Paciente paciente;
    protected String codigo;
    protected Medico medico;
    protected String descricaoMedica;
    protected boolean status;
    protected String data;
    protected String horario;
    

    //CONSTRUTOR
    public Consulta(String codigo, Paciente paciente) {
        this.codigo = codigo;
        this.paciente = paciente;
    }

    //METODOS
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescricaoMedica() {
        return descricaoMedica;
    }

    public void setDescricaoMedica(String descricaoMedica) {
        this.descricaoMedica = descricaoMedica;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}