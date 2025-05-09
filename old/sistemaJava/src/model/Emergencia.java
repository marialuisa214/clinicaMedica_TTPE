package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emergencia extends Consulta {
    // ATRIBUTOS
    private String codigoChamada;
    private Triagem triagem;
    private String data;
    private Date c;
    private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");


    // CONSTRUTOR

    public Emergencia(String codigo,Paciente paciente, String codigoChamada, Triagem triagem) {
        super(codigo, paciente);
        this.codigoChamada = codigoChamada;
        this.c = new Date();
        System.out.println(formatador.format(c));
        this.data = formatador.format(c);
        
        this.triagem = triagem;

    }
    // METODOS
    public Triagem getTriagem() {
        return triagem;
    }
    public void setTriagem(Triagem triagem) {
        this.triagem = triagem;
    }

    public String getCodigoChamada() {
        return codigoChamada;
    }
    public void setCodigoChamada(String codigoChamada) {
        this.codigoChamada = codigoChamada;
    }
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getData() {
        return data;
      }
      public void setData(String data) {
        this.data = data;
    }
}
