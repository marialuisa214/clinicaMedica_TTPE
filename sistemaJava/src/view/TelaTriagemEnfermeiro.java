package view;

public class TelaTriagemEnfermeiro extends javax.swing.JFrame {
    

public TelaTriagemEnfermeiro() {
    initComponents();
}                         
    private void initComponents() {

        labelNomePac = new javax.swing.JLabel();
        labelNomePaciente = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelSituacaoPac = new javax.swing.JLabel();
        labelSituacaoPaciente = new javax.swing.JLabel();
        labelDescricaoPaciente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        radioButtonEmergente = new javax.swing.JRadioButton();
        radioButtonUrgente = new javax.swing.JRadioButton();
        radioButtonNaoUrgente = new javax.swing.JRadioButton();
        buttonGerarProntuario = new javax.swing.JButton();
        buttonEnviarConsulta = new javax.swing.JButton();
        buttonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelNomePac.setText("Nome do paciente: ");

        labelNomePaciente.setText("jLabel4");

        labelSituacaoPac.setText("Situação:");

        labelSituacaoPaciente.setText("jLabel5");

        labelDescricaoPaciente.setText("Descrição:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        radioButtonEmergente.setText("Emergente");
        radioButtonEmergente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonEmergenteActionPerformed(evt);
            }
        });

        radioButtonUrgente.setText("Urgente");
        radioButtonUrgente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonUrgenteActionPerformed(evt);
            }
        });

        radioButtonNaoUrgente.setText("Não urgente");
        radioButtonNaoUrgente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonNaoUrgenteActionPerformed(evt);
            }
        });

        buttonGerarProntuario.setText("Gerar prontuário");

        buttonEnviarConsulta.setText("Enviar para consulta");

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
                .addContainerGap()
                .addComponent(labelNomePac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNomePaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonVoltar)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelDescricaoPaciente)
                .addGap(332, 332, 332))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(buttonGerarProntuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonEnviarConsulta)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSituacaoPac)
                        .addGap(3, 3, 3)
                        .addComponent(labelSituacaoPaciente)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(radioButtonEmergente)
                        .addGap(61, 61, 61)
                        .addComponent(radioButtonUrgente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioButtonNaoUrgente)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonVoltar)
                    .addComponent(labelNomePac)
                    .addComponent(labelNomePaciente))
                .addGap(22, 22, 22)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSituacaoPaciente)
                    .addComponent(labelSituacaoPac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDescricaoPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioButtonEmergente)
                    .addComponent(radioButtonUrgente)
                    .addComponent(radioButtonNaoUrgente))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonGerarProntuario)
                    .addComponent(buttonEnviarConsulta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        // </editor-fold>
    }                       

    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        
    }                                            

    private void radioButtonUrgenteActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        
    }                                                  

    private void radioButtonNaoUrgenteActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        
    }                                                     

    private void radioButtonEmergenteActionPerformed(java.awt.event.ActionEvent evt) {    

    }                                                 
                                                       
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaTriagemEnfermeiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaTriagemEnfermeiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaTriagemEnfermeiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaTriagemEnfermeiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaTriagemEnfermeiro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonEnviarConsulta;
    private javax.swing.JButton buttonGerarProntuario;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelDescricaoPaciente;
    private javax.swing.JLabel labelNomePac;
    private javax.swing.JLabel labelNomePaciente;
    private javax.swing.JLabel labelSituacaoPac;
    private javax.swing.JLabel labelSituacaoPaciente;
    private javax.swing.JRadioButton radioButtonEmergente;
    private javax.swing.JRadioButton radioButtonNaoUrgente;
    private javax.swing.JRadioButton radioButtonUrgente;
    // End of variables declaration                   
}