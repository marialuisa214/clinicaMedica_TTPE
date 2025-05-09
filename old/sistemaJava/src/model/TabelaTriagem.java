package model;

import javax.swing.table.AbstractTableModel;

import control.ControleDados;


public class TabelaTriagem extends AbstractTableModel {
    private ControleDados dados;
    private String[] colunas =  { "Horario de Entrada",  "Paciente", "Situação" };


    //CONSTRUTOR
    public TabelaTriagem(ControleDados dados){
        this.dados = dados;
    }


    public String getColumnName(int coluna){
        return colunas[coluna];
    }

    @Override
//    quantidade de linhas
    public int getRowCount() {
        return dados.getaBancoEntradas().size();
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
                return dados.getaBancoEntradas().get(linha).getDataEntrada();
            case 1:
                return dados.getaBancoEntradas().get(linha).getPacienteVinculado().getNome();
            case 2:
                return dados.getaBancoEntradas().get(linha).getSituacaoPaciente();
        }

        return null;
    }

    public void addRow(Entrada e){
        this.dados.getaBancoEntradas().add(e);
        this.fireTableDataChanged();
    }
    
    public Entrada selecionaItem(int linha){
        return this.dados.getaBancoEntradas().get(linha);
    }
}