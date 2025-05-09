package model;

public class Triagem {

    // ATRIBUTOS
    private String descricao;
    private String gravidade;
    private Entrada entrada;
    private Enfermeiro enfermeiro;


    // CONSTRUTOR
    public Triagem(Entrada entrada, Enfermeiro enfermeiro) {
        this.enfermeiro = enfermeiro;
        this.entrada = entrada;
        System.out.println(this.entrada.getPacienteVinculado().getNome());
    }

    // METODOS
    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Enfermeiro getEnfermeiro() {
        return enfermeiro;
    }

    public void setEnfermeiro(Enfermeiro enfermeiro) {
        this.enfermeiro = enfermeiro;
    }

   

    // public void cadastraProntuario(Float altura, Float peso, String acompanhamento) {
    //     // quando o paciente ainda não possui prontuario preenchido:
    //     if (this.pacienteVinculado.getProntuario().getValidado() == false) {
    //         this.pacienteVinculado.getProntuario().setAcompanhamento(acompanhamento);
    //         this.pacienteVinculado.getProntuario().setPeso(peso);
    //         this.pacienteVinculado.getProntuario().setAltura(altura);

    //         this.pacienteVinculado.getProntuario().setValidado(true);
    //     }
    // }

}
