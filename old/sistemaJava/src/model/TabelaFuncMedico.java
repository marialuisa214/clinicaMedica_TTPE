package model;

import javax.swing.table.AbstractTableModel;
import control.ControleDados;

public class TabelaFuncMedico extends AbstractTableModel {
    private ControleDados dados;

    private String[] colunas =  { "Medico",  "CRM" };


    //CONSTRUTOR
    public TabelaFuncMedico(ControleDados dados){
        this.dados = dados;
    }


    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    @Override
//    quantidade de linhas
    public int getRowCount() {
        return dados.getBancoMedicos().size();
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
                return dados.getBancoMedicos().get(linha).getNome();
            case 1:
                return dados.getBancoMedicos().get(linha).getCrm();
        }

        return null;
    }

    public void addRow(Medico exame){
        this.dados.getBancoMedicos().add(exame);
        this.fireTableDataChanged();
    }
    
    public Medico selecionaItem(int linha){
        return this.dados.getBancoMedicos().get(linha);
    }
    public void removeRow(int linha){
        this.dados.getBancoMedicos().remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
}

