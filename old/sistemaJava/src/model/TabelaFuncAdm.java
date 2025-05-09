package model;

import javax.swing.table.AbstractTableModel;

import control.ControleDados;

public class TabelaFuncAdm extends AbstractTableModel {
    private ControleDados dados;
    private String[] colunas =  { "Administrador",  "ID" };


    //CONSTRUTOR
    public TabelaFuncAdm(ControleDados dados){
        this.dados = dados;
    }

    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    @Override
//    quantidade de linhas
    public int getRowCount() {
        return dados.getBancoAdministradores().size();
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
                return dados.getBancoAdministradores().get(linha).getNome();
            case 1:
                return dados.getBancoAdministradores().get(linha).getIdAdmin();
        }

        return null;
    }

    public void addRow(Administrador exame){
        this.dados.getBancoAdministradores().add(exame);
        this.fireTableDataChanged();
    }
    
    public Administrador selecionaItem(int linha){
        return this.dados.getBancoAdministradores().get(linha);
    }
    public void removeRow(int linha){
        this.dados.getBancoAdministradores().remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
}
