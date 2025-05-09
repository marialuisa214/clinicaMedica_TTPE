package view;

import control.ControleDados;
import model.Atendente;
import model.Paciente;
import model.TabelaTodosExames;

public class TelaAgendaExame extends javax.swing.JFrame {

    public TelaAgendaExame(ControleDados dados,  Atendente atendente) {
        this.dados = dados;
        this.atendente = atendente;
        this.tabela = new TabelaTodosExames(dados);
        
        initComponents();
        tableConsulta.setModel(this.tabela);

    }
                
    private void initComponents() {

        scrollTableConsulta = new javax.swing.JScrollPane();
        tableConsulta = new javax.swing.JTable();
        escolheMedico = new java.awt.Choice();
        buttonMarcaConsulta = new javax.swing.JToggleButton();
        buttonAlteraConsulta = new javax.swing.JToggleButton();
        buttonDesmarcaConsulta = new javax.swing.JToggleButton();
        buttonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Horário/Consulta", "Paciente"
            }
        ));
        scrollTableConsulta.setViewportView(tableConsulta);

        escolheMedico.add("Selecione um Paciente!");
        for(int i = 0; i<dados.getBancoPacientes().size(); i ++){
            escolheMedico.add(dados.getBancoPacientes().get(i).getNome());
            
        }

        buttonMarcaConsulta.setBackground(new java.awt.Color(153, 153, 153));
        buttonMarcaConsulta.setText("Marcar Exame");
        buttonMarcaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMarcaConsultaActionPerformed(evt);
            }
        });

        buttonAlteraConsulta.setBackground(new java.awt.Color(153, 153, 153));
        buttonAlteraConsulta.setText("Alterar Exame");
        buttonAlteraConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAlteraConsultaActionPerformed(evt);
            }
        });

        buttonDesmarcaConsulta.setBackground(new java.awt.Color(153, 153, 153));
        buttonDesmarcaConsulta.setText("Desmarcar Exame");
        buttonDesmarcaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesmarcaConsultaActionPerformed(evt);
            }
        });

        buttonVoltar.setBackground(new java.awt.Color(153, 153, 153));
        buttonVoltar.setText("Voltar");
        buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonVoltar)
                    .addComponent(escolheMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollTableConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonMarcaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonAlteraConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonDesmarcaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(buttonVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(escolheMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTableConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonMarcaConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonAlteraConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonDesmarcaConsulta)))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>                        

    private void buttonMarcaConsultaActionPerformed(java.awt.event.ActionEvent evt) {   
        for(int i = 0; i<dados.getBancoPacientes().size(); i ++){
            if(escolheMedico.getSelectedItem().equals(dados.getBancoPacientes().get(i).getNome())){
                this.paciente = dados.getBancoPacientes().get(i);
            }

        }
        new TelaMarcaExame(dados, atendente, paciente).setVisible(true);
        this.dispose();
        }                                                      

    private void buttonAlteraConsultaActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    private void buttonDesmarcaConsultaActionPerformed(java.awt.event.ActionEvent evt) {                                                           
        if(tableConsulta.getSelectedRow() != -1 ){
            tabela.removeRow(tableConsulta.getSelectedRow());
            
        }
    }                                                      

    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        new TelaPrincipalAtendente(dados, atendente).setVisible(true);
        this.dispose();
    }                                            


    // Variables declaration - do not modify                     
    private javax.swing.JToggleButton buttonAlteraConsulta;
    private javax.swing.JToggleButton buttonDesmarcaConsulta;
    private javax.swing.JToggleButton buttonMarcaConsulta;
    private javax.swing.JButton buttonVoltar;
    private java.awt.Choice escolheMedico;
    private javax.swing.JScrollPane scrollTableConsulta;
    private javax.swing.JTable tableConsulta;
    // End of variables declaration                   

    private TabelaTodosExames tabela;
    private ControleDados dados;
    private Atendente atendente;
    private Paciente paciente;
}