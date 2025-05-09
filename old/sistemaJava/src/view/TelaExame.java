package view;

import control.ControleDados;
import model.*;

public class TelaExame extends javax.swing.JFrame {

    public TelaExame(Paciente paciente, Medico medico, Marcada consulta, ControleDados dados) {
        this.paciente = paciente;
        this.medico = medico;
        this.dados = dados;
        this.consulta = consulta; 
        initComponents();
    }
                         
    private void initComponents() {

        labelCrm = new javax.swing.JLabel();
        botaoVoltar = new javax.swing.JButton();
        labelNomeMedico = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelExame = new javax.swing.JLabel();
        textExame = new javax.swing.JTextField();
        labelCodigo = new javax.swing.JLabel();
        textCodigo = new javax.swing.JTextField();
        labelNomePaciente = new javax.swing.JLabel();
        botaoSolicita = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelCrm.setFont(new java.awt.Font("Gujarati MT", 0, 8)); // NOI18N
        labelCrm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCrm.setText(medico.getCrm());

        botaoVoltar.setBackground(new java.awt.Color(204, 153, 255));
        botaoVoltar.setFont(new java.awt.Font("Gujarati MT", 1, 13)); // NOI18N
        botaoVoltar.setText("Voltar");
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });

        labelNomeMedico.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelNomeMedico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomeMedico.setText(medico.getNome());

        labelExame.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelExame.setText("Exame:");

        labelCodigo.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelCodigo.setText("Código:");


        labelNomePaciente.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelNomePaciente.setText(paciente.getNome());

        botaoSolicita.setText("Solicitar");
        botaoSolicita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSolicitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCodigo)
                            .addComponent(labelExame))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(textExame))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(labelCrm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelNomePaciente)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoVoltar)))
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(labelNomeMedico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSolicita)
                .addGap(119, 119, 119))
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(botaoVoltar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(labelNomeMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCrm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNomePaciente)))
                .addGap(3, 3, 3)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelExame)
                    .addComponent(textExame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(textCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(botaoSolicita)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        new TelaRelatorioConsulta(dados, consulta).setVisible(true);
        this.dispose();;
    }            
    
    private void botaoSolicitaActionPerformed(java.awt.event.ActionEvent evt) {   
        Exame exame = new Exame(textExame.getText(), textCodigo.getText(), paciente);
        
         Solicitacao novaSolicitacao = new Solicitacao();
         novaSolicitacao.setMedico(medico);
         novaSolicitacao.solicitaExame(exame);
        
        // dados.adicionaSolicitacao(novaSolicitacao);
        new TelaRelatorioConsulta(dados, consulta).setVisible(true);;
        this.dispose();
    } 

    // public static void main(String args[]) {
        
    //     java.awt.EventQueue.invokeLater(new Runnable() {
    //         public void run() {
    //             new TelaExame().setVisible(true);
    //         }
    //     });
    // }

    // Variables declaration - do not modify                     
    private javax.swing.JButton botaoSolicita;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelCrm;
    private javax.swing.JLabel labelExame;
    private javax.swing.JLabel labelNomeMedico;
    private javax.swing.JLabel labelNomePaciente;
    private javax.swing.JTextField textCodigo;
    private javax.swing.JTextField textExame;
    // End of variables declaration      
    
    private Paciente paciente;
    private Medico medico;

    private Marcada consulta;
    private ControleDados dados;
}
