package view;

import control.ControleDados;
import model.Medico;

public class TelaHorario extends javax.swing.JFrame {

    public TelaHorario(ControleDados dados, Medico medico) {
        this.medico=medico;
        this.dados = dados;
        initComponents();
    }

    private void initComponents() {

        labelNomeMedico = new javax.swing.JLabel();
        labelCrm = new javax.swing.JLabel();
        botaoVoltar = new javax.swing.JButton();
        botaoSolicita = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textEmail = new javax.swing.JTextArea();
        labelEmail = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelNomeMedico.setText("nomeMedico");

        labelCrm.setText("crm");

        botaoVoltar.setText("Voltar");
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });

        botaoSolicita.setText("Solicitar Reunião");
        botaoSolicita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSolicitaActionPerformed(evt);
            }
        });

        textEmail.setColumns(20);
        textEmail.setRows(5);
        jScrollPane1.setViewportView(textEmail);

        labelEmail.setText("Adicionar e-mail:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSolicita)
                .addGap(128, 128, 128))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labelNomeMedico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                                .addComponent(botaoVoltar)))
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEmail)
                            .addComponent(labelCrm))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNomeMedico)
                    .addComponent(botaoVoltar))
                .addGap(18, 18, 18)
                .addComponent(labelCrm)
                .addGap(18, 18, 18)
                .addComponent(labelEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoSolicita)
                .addGap(17, 17, 17))
        );

        pack();
    }                     

    private void botaoSolicitaActionPerformed(java.awt.event.ActionEvent evt) {                                              
        //como fazer? Mandar string?

        new TelaPrincipalMedico(dados, medico).setVisible(true); 
        this.dispose();
    }                                             

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        new TelaPrincipalMedico(dados, medico).setVisible(true); 
        this.dispose();
    }    
    

    // Variables declaration - do not modify                     
    private javax.swing.JButton botaoSolicita;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JLabel labelCrm;
    private javax.swing.JLabel labelNomeMedico;
    private javax.swing.JTextArea textEmail;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration       
    ControleDados dados;
    Medico medico;            
}
