package model;

import javax.swing.table.AbstractTableModel;

public class TabelaProntuarioExame extends AbstractTableModel {
    
    private Prontuario prontuario;
    private String[] colunas = {"DATA", "CÓDIGO" ,"EXAME"};

//CONSTRUTOR
    public TabelaProntuarioExame(Prontuario p ){
        this.prontuario = p;

    }

    @Override
    public int getRowCount() {
        return prontuario.getHistoricoExame().size();
    }

    @Override
    public String getColumnName(int coluna){
        return colunas[coluna];
    }

    @Override
        public int getColumnCount() { 
            return colunas.length;
        }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return prontuario.getHistoricoExame().get(linha).getData();
            case 1:
                return prontuario.getHistoricoExame().get(linha).getCodigo();
            case 2:
                return prontuario.getHistoricoExame().get(linha).getNomeExame();
        }

        return null;
    }

    public void addRow(Exame exame){
        this.prontuario.getHistoricoExame().add(exame);
        this.fireTableDataChanged();
    }
    
    public Exame selecionaItem(int linha){
        return this.prontuario.getHistoricoExame().get(linha);
    }
   
}
