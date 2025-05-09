package model;


import javax.swing.table.AbstractTableModel;


public class TabelaMedico extends AbstractTableModel {

    private Medico medico;
    private String[] colunas =  {"Dia", "Horário", "Paciente", "Código" }; 


//CONSTRUTOR
    public TabelaMedico(Medico medico){
        this.medico = medico;  
    }

    public String getColumnName(int coluna){
        return colunas[coluna];

    }

    @Override
//    quantidade de linhas
    public int getRowCount() {
        return medico.getListConsultasPendentes().size();
    
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
                return medico.getListConsultasPendentes().get(linha).getData();
            case 1:
                return medico.getListConsultasPendentes().get(linha).getHorario();
            case 2:
                return medico.getListConsultasPendentes().get(linha).getPaciente().getNome();
            case 3:
                return medico.getListConsultasPendentes().get(linha).getCodigo();
        }

        return null;
    }

    public void addRow(Marcada cm){
        this.medico.getListConsultasPendentes().add(cm);;
        this.fireTableDataChanged();
    }
    
    public Marcada selecionaItem(int linha){
        return this.medico.getListConsultasPendentes().get(linha);
    }

}
