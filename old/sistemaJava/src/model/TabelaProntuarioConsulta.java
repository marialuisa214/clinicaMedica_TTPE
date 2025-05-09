package model;

import javax.swing.table.AbstractTableModel;

public class TabelaProntuarioConsulta extends AbstractTableModel {
    
    private Prontuario prontuario;
    private String[] colunas = {"DATA", "CÓDIGO", "DESCRIÇÃO"};


    //CONSTRUTOR
    public TabelaProntuarioConsulta(Prontuario p ){
        this.prontuario = p;

    }

    public String getColumnName(int coluna){
        return colunas[coluna];
    }

    @Override
//    quantidade de linhas
    public int getRowCount() {
        return prontuario.getHistoricoConsultasMarcadas().size();
    
    }

    @Override
//    quantidade de colunas
    public int getColumnCount() { 
        return colunas.length;
    }

    @Override
//    seleciona os valores da tabela
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return prontuario.getHistoricoConsultasMarcadas().get(linha).getData();
            case 1:
                return prontuario.getHistoricoConsultasMarcadas().get(linha).getCodigo();
            case 2:
                return prontuario.getHistoricoConsultasMarcadas().get(linha).getDescricaoMedica();
        }

        return null;
    }
    
    public void addRow(Marcada cm){
        this.prontuario.getHistoricoConsultasMarcadas().add(cm);;
        this.fireTableDataChanged();
    }
    
    public Marcada selecionaItem(int linha){
        return this.prontuario.getHistoricoConsultasMarcadas().get(linha);
    }
}
