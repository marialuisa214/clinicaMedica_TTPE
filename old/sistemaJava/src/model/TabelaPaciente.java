package model;

import javax.swing.table.AbstractTableModel;

import control.ControleDados;

public class TabelaPaciente extends AbstractTableModel {

    private ControleDados dados;
    private String[] colunas =  {"NOME", "TELEFONE" }; 

//CONSTRUTOR
    public TabelaPaciente(ControleDados dados){
        this.dados = dados;
    }
    public String getColumnName(int coluna){
        return colunas[coluna];
    }

    @Override
    public int getRowCount() {
        return dados.getBancoPacientes().size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return dados.getBancoPacientes().get(linha).getNome();
            case 1:
                return dados.getBancoPacientes().get(linha).getTelefone();
        }

        return null;
    }

    public void addRow(Paciente p){
        this.dados.getBancoPacientes().add(p);;
        this.fireTableDataChanged();
    }
    
    public Paciente selecionaItem(int linha){
        return this.dados.getBancoPacientes().get(linha);
    }
    
}
