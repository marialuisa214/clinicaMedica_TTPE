package view;

import javax.swing.JOptionPane;

import control.*;
import model.*;

public class TelaPrincipalMedico extends javax.swing.JFrame { 



    public TelaPrincipalMedico(ControleDados controle, Medico m) {
        this.medico = m;
        this.controle = controle;
        this.tabela = new TabelaMedico(m); 
        initComponents();

        tabelaConsultas.setModel(tabela);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        labelCrm = new javax.swing.JLabel();
        buttonSair = new javax.swing.JButton();
        labelNomeMedico = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsultas = new javax.swing.JTable();
        buttonRelatorioConsulta = new javax.swing.JButton();
        buttonMundancaHorario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frameMedico"); // NOI18N

        labelCrm.setFont(new java.awt.Font("Gujarati MT", 0, 8)); // NOI18N
        labelCrm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCrm.setText(medico.getCrm());

        buttonSair.setBackground(new java.awt.Color(204, 153, 255));
        buttonSair.setFont(new java.awt.Font("Gujarati MT", 1, 13)); // NOI18N
        buttonSair.setText("sair");
        buttonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSairActionPerformed(evt);
            }
        });

        labelNomeMedico.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelNomeMedico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomeMedico.setText(medico.getNome());

        jLabel3.setText("Agenda de Consultas e Exames:");

        tabelaConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaConsultas);

        buttonRelatorioConsulta.setBackground(new java.awt.Color(204, 204, 255));
        buttonRelatorioConsulta.setFont(new java.awt.Font("Gujarati MT", 0, 14)); // NOI18N
        buttonRelatorioConsulta.setText("Gerar Relatórios de Consulta");
        buttonRelatorioConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRelatorioConsultaActionPerformed(evt);
            }
        });

        buttonMundancaHorario.setBackground(new java.awt.Color(204, 204, 255));
        buttonMundancaHorario.setFont(new java.awt.Font("Gujarati MT", 0, 14)); // NOI18N
        buttonMundancaHorario.setText("Solicitar mudança de horário");
        buttonMundancaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMundancaHorarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonSair))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(labelCrm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelNomeMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(buttonMundancaHorario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonRelatorioConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelNomeMedico)
                        .addGap(12, 12, 12)
                        .addComponent(labelCrm))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(buttonSair)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMundancaHorario)
                    .addComponent(buttonRelatorioConsulta))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void buttonSairActionPerformed(java.awt.event.ActionEvent evt) {  
        new TelaLogin(controle).setVisible(true);
        this.dispose();

    }                                          

    private void buttonMundancaHorarioActionPerformed(java.awt.event.ActionEvent evt) {    
        new TelaHorario(controle, medico).setVisible(true);
        this.dispose();                                               
    }                                                     

    
    private void buttonRelatorioConsultaActionPerformed(java.awt.event.ActionEvent evt) { 
        
        if(tabelaConsultas.getSelectedRow() != -1 ){
            Marcada consulta = tabela.selecionaItem(tabelaConsultas.getSelectedRow());
            new TelaRelatorioConsulta(controle, consulta).setVisible(true);
                this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma consulta antes de ir para a proxima página!");
        }

        
    }                                                                                                        

    
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonMundancaHorario;
    private javax.swing.JButton buttonRelatorioConsulta;
    private javax.swing.JButton buttonSair;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabelaConsultas;
    private javax.swing.JLabel labelCrm;
    private javax.swing.JLabel labelNomeMedico;
    // End of variables declaration  
    private ControleDados controle;
    private Medico medico;
    private TabelaMedico tabela;
    

}
    
    