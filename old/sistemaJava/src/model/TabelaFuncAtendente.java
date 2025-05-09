package model;

import javax.swing.table.AbstractTableModel;

import control.ControleDados;

public class TabelaFuncAtendente extends AbstractTableModel {
    private ControleDados dados;
    private String[] colunas =  { "Atendente",  "ID" };


    //CONSTRUTOR
    public TabelaFuncAtendente(ControleDados dados){
        this.dados = dados;
    }


    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    @Override
//    quantidade de linhas
    public int getRowCount() {
        return dados.getaBancoAtendente().size();
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
                return dados.getaBancoAtendente().get(linha).getNome();
            case 1:
                return dados.getaBancoAtendente().get(linha).getIdAtendente();
        }

        return null;
    }

    public void addRow(Atendente exame){
        this.dados.getaBancoAtendente().add(exame);
        this.fireTableDataChanged();
    }
    
    public Atendente selecionaItem(int linha){
        return this.dados.getaBancoAtendente().get(linha);
    }
    public void removeRow(int linha){
        this.dados.getaBancoAtendente().remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
}

