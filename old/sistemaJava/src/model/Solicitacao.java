package model;

import java.util.ArrayList;

public class Solicitacao {
//   ATRIBUTOS
    private Medico medico;
    ArrayList<Marcada> listaSolicitacaoConsulta = new ArrayList<>();
    ArrayList<Exame> listaSolicitacaoExame = new ArrayList<>();

    public Solicitacao(){
        this.listaSolicitacaoConsulta = new ArrayList<>();
        this.listaSolicitacaoExame = new ArrayList<>();

    }



//  METODOS
    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public ArrayList<Marcada> getListaSolicitacaoConsultas() {
        return listaSolicitacaoConsulta;
      }
      public void solicitaConsulta(Marcada consulta) {
        this.listaSolicitacaoConsulta.add(consulta);
    }

    public ArrayList<Exame> getListaSolicitacaoExame() {
        return listaSolicitacaoExame;
    }
      public void solicitaExame(Exame exame) {
        this.listaSolicitacaoExame.add(exame);
    }

    //public ArrayList<aviso> OU o medico pode mandar um email!!! ai maria como vc é inteligente
    
}
