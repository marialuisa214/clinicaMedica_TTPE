package control;

import model.*;

public class ControleProntuario {

  public Prontuario prontuario;

  public void adicionaConsulta(Marcada consulta) {
    this.prontuario.setHistoricoConsultasMarcadas(consulta);
  }

  public void adicionaExame(Exame exame) {
    this.prontuario.setHistoricoExame(exame);
  }
}
