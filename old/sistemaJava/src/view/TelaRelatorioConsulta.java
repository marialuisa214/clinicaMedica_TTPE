package view;


import javax.swing.JOptionPane;

import control.ControleDados;
import model.*;

public class TelaRelatorioConsulta extends javax.swing.JFrame {

    
    public TelaRelatorioConsulta(ControleDados dados, Marcada consulta) {
        this.dados = dados;
        this.consulta = consulta;
        initComponents();
    }

                        
    private void initComponents() {

        labelCrm = new javax.swing.JLabel();
        botaoVoltarMedico = new javax.swing.JButton();
        labelNomeMedico = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelPaciente = new javax.swing.JLabel();
        labelCodigoConsulta = new javax.swing.JLabel();
        botaoConsultaProntuario = new javax.swing.JButton();
        labelTipoConsulta = new javax.swing.JLabel();
        textDescricao = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        botaoGeraReceita = new javax.swing.JButton();
        botaoFinalizaConsulta = new javax.swing.JButton();
        botaoAtestado = new javax.swing.JButton();
        labelNomePaciente = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();
        botaoExame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelCrm.setFont(new java.awt.Font("Gujarati MT", 0, 8)); // NOI18N
        labelCrm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCrm.setText("CRM");

        botaoVoltarMedico.setBackground(new java.awt.Color(204, 153, 255));
        botaoVoltarMedico.setFont(new java.awt.Font("Gujarati MT", 1, 13)); // NOI18N
        botaoVoltarMedico.setText("Voltar");
        botaoVoltarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarMedicoActionPerformed(evt);
            }
        });

        labelNomeMedico.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelNomeMedico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomeMedico.setText(consulta.getMedico().getNome());

        labelPaciente.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelPaciente.setText("Paciente:");

        labelCodigoConsulta.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelCodigoConsulta.setText("Código de Consulta:");

        botaoConsultaProntuario.setBackground(new java.awt.Color(204, 204, 255));
        botaoConsultaProntuario.setFont(new java.awt.Font("Gujarati MT", 1, 15)); // NOI18N
        botaoConsultaProntuario.setText("Prontuario");
        botaoConsultaProntuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConsultaProntuarioActionPerformed(evt);
            }
        });

        labelTipoConsulta.setFont(new java.awt.Font("Gujarati MT", 0, 12)); // NOI18N
        labelTipoConsulta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoConsulta.setText("Consulta Marcada");

        textDescricao.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        textDescricao.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textDescricao.setToolTipText("descrição do estado do paciente");
        textDescricao.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        textDescricao.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textDescricao.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        textDescricao.setPreferredSize(new java.awt.Dimension(0, 0));
        textDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDescricaoActionPerformed(evt);
            }
        });

        labelDescricao.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelDescricao.setText("Descriçao:");

        botaoGeraReceita.setBackground(new java.awt.Color(204, 204, 255));
        botaoGeraReceita.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        botaoGeraReceita.setText("Gerar Receita Médica ");

        botaoFinalizaConsulta.setBackground(new java.awt.Color(204, 204, 255));
        botaoFinalizaConsulta.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        botaoFinalizaConsulta.setText("Finalizar Consulta");
        botaoFinalizaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizaConsultaActionPerformed(evt);
            }
        });

        botaoGeraReceita.setBackground(new java.awt.Color(204, 204, 255));
        botaoGeraReceita.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        botaoGeraReceita.setText("Gerar Receita Médica ");
        botaoGeraReceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGeraReceitaActionPerformed(evt);
            }
        });

        botaoAtestado.setBackground(new java.awt.Color(204, 204, 255));
        botaoAtestado.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        botaoAtestado.setText("Gerar Atestado Médico");

        labelNomePaciente.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelNomePaciente.setText(consulta.getPaciente().getNome());

        labelCodigo.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelCodigo.setText(consulta.getCodigo());

        botaoExame.setBackground(new java.awt.Color(204, 204, 255));
        botaoExame.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        botaoExame.setText("Solicitar Exame");
        botaoExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCrm, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoVoltarMedico)
                                .addGap(13, 13, 13))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoAtestado)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(botaoExame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoGeraReceita, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelCodigoConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoConsultaProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelDescricao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labelPaciente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(labelTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botaoFinalizaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCrm)
                    .addComponent(botaoVoltarMedico))
                .addGap(5, 5, 5)
                .addComponent(labelTipoConsulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPaciente)
                    .addComponent(labelNomePaciente))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigoConsulta)
                    .addComponent(botaoConsultaProntuario)
                    .addComponent(labelCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoExame)
                        .addGap(18, 18, 18)
                        .addComponent(botaoGeraReceita)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoAtestado))
                    .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(botaoFinalizaConsulta)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>                        

    private void botaoVoltarMedicoActionPerformed(java.awt.event.ActionEvent evt) {    
        new TelaPrincipalMedico(dados, consulta.getMedico()).setVisible(true); 
        this.dispose();                                       
        
    }                                                 

    private void botaoConsultaProntuarioActionPerformed(java.awt.event.ActionEvent evt) {  
        new TelaProntuario(consulta.getPaciente().getProntuario(), dados, consulta).setVisible(true);
        this.dispose();                                                     
        
    }                                                       

    private void textDescricaoActionPerformed(java.awt.event.ActionEvent evt) {                                              
        
    }                                             

    private void botaoFinalizaConsultaActionPerformed(java.awt.event.ActionEvent evt) {    
        
        if(textDescricao.getText().isEmpty()){ 
            JOptionPane.showMessageDialog(null,"Vocé deve preencher todos os campos para Finalizar a Consulta." );
        } else{ 
            consulta.setDescricaoMedica(textDescricao.getText());
            consulta.setStatus(true);
            consulta.getMedico().getListConsultasPendentes().remove(consulta);
            
            new TelaPrincipalMedico(dados, consulta.getMedico()).setVisible(true);
            this.dispose();

        }                                               
        
    }                                                     

    private void botaoExameActionPerformed(java.awt.event.ActionEvent evt) {                                           
        new TelaExame(consulta.getPaciente(), consulta.getMedico(), consulta, dados).setVisible(true);
        this.dispose();
    }
    
    private void botaoGeraReceitaActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        new TelaReceita(consulta.getMedico(), consulta, dados).setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton botaoAtestado;
    private javax.swing.JButton botaoConsultaProntuario;
    private javax.swing.JButton botaoExame;
    private javax.swing.JButton botaoFinalizaConsulta;
    private javax.swing.JButton botaoGeraReceita;
    private javax.swing.JButton botaoVoltarMedico;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelCodigoConsulta;
    private javax.swing.JLabel labelCrm;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelNomeMedico;
    private javax.swing.JLabel labelNomePaciente;
    private javax.swing.JLabel labelPaciente;
    private javax.swing.JLabel labelTipoConsulta;
    private javax.swing.JTextField textDescricao;
    // End of variables declaration           
    
    private Marcada consulta;
    private ControleDados dados;
}


