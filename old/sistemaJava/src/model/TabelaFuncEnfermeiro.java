package model;

import javax.swing.table.AbstractTableModel;

import control.ControleDados;

public class TabelaFuncEnfermeiro extends AbstractTableModel {
    private ControleDados dados;
    private String[] colunas =  { "Enfermeiro",  "COREM" };


    //CONSTRUTOR
    public TabelaFuncEnfermeiro(ControleDados dados){
        this.dados = dados;
    }


    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    @Override
//    quantidade de linhas
    public int getRowCount() {
        return dados.getBancoEnfermeiros().size();
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
                return dados.getBancoEnfermeiros().get(linha).getNome();
            case 1:
                return dados.getBancoEnfermeiros().get(linha).getCorem();
        }

        return null;
    }

    public void addRow(Enfermeiro exame){
        this.dados.getBancoEnfermeiros().add(exame);
        this.fireTableDataChanged();
    }
    
    public Enfermeiro selecionaItem(int linha){
        return this.dados.getBancoEnfermeiros().get(linha);
    }
    public void removeRow(int linha){
        this.dados.getBancoEnfermeiros().remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
}
