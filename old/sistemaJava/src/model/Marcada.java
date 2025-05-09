package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Marcada extends Consulta {

    // ATRIBUTOS
    private String data; 
    private String horario;
    private boolean status;
    private String descricaoMedica;
    private Medico medico;
    private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    private Date c;
    

    //CONSTRUTOR
    public Marcada(String codigo, Paciente paciente, Medico medico) {
        super(codigo, paciente);
        this.status = false;
        this.medico = medico;
    }
    // METODOS

    public String getHorario() {
        return horario;
    }

    public void setHorario(String hoario) {
        this.horario = hoario;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getDescricaoMedica() {
        return descricaoMedica;
    }

    public void setDescricaoMedica(String descricaoMedica) {
        this.descricaoMedica = descricaoMedica;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
  
}


