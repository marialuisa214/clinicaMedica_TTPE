package model;

import javax.swing.table.AbstractTableModel;

import control.ControleDados;

public class TabelaTodasConsultas extends AbstractTableModel {
    
    private ControleDados dados;
    private String[] colunas = {"DATA", "PACIENTE", "ESPECIALIDADE"};


    //CONSTRUTOR
    public TabelaTodasConsultas(ControleDados dados ){
        this.dados = dados;

    }

    public String getColumnName(int coluna){
        return colunas[coluna];
    }

    @Override
//    quantidade de linhas
    public int getRowCount() {
        return dados.getBancoConsultaMarcadas().size();
    
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
                return dados.getBancoConsultaMarcadas().get(linha).getData();
            case 1:
                return dados.getBancoConsultaMarcadas().get(linha).getPaciente().getNome();
            case 2:
                return dados.getBancoConsultaMarcadas().get(linha).getMedico().getEspecialidade();
        }

        return null;
    }
    
    public void addRow(Marcada cm){
        this.dados.getBancoConsultaMarcadas().add(cm);;
        this.fireTableDataChanged();
    }
    
    public Marcada selecionaItem(int linha){
        return this.dados.getBancoConsultaMarcadas().get(linha);
    }

    public void removeRow(int linha){
        this.dados.getBancoConsultaMarcadas().remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
}
