package model;

import javax.swing.table.AbstractTableModel;

import control.ControleDados;

public class TabelaExameEnfermeiro extends AbstractTableModel {
    private ControleDados dados;
    private String[] colunas =  { "Data",  "Paciente", "Tipo de Exame" };


    //CONSTRUTOR
    public TabelaExameEnfermeiro(ControleDados dados){
        this.dados = dados;
    }


    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    @Override
//    quantidade de linhas
    public int getRowCount() {
        return dados.getBancoExames().size();
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
                return dados.getBancoExames().get(linha).getData();
            case 1:
                return dados.getBancoExames().get(linha).getPaciente().getNome();
            case 2:
                return dados.getBancoExames().get(linha).getNomeExame();
        }

        return null;
    }

    public void addRow(Exame exame){
        this.dados.getBancoExames().add(exame);
        this.fireTableDataChanged();
    }
    
    public Exame selecionaItem(int linha){
        return this.dados.getBancoExames().get(linha);
    }
}
