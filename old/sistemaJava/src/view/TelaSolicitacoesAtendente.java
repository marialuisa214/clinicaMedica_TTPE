package view;

import control.ControleDados;
import model.Atendente;

public class TelaSolicitacoesAtendente extends javax.swing.JFrame {

    public TelaSolicitacoesAtendente(ControleDados dados, Atendente atendente) {
        this.dados = dados;
        this.atendente = atendente;
        initComponents();
    }
                         
    private void initComponents() {

        labelDataHora = new javax.swing.JLabel();
        labelIdAtendente = new javax.swing.JLabel();
        labelAtendente = new javax.swing.JLabel();
        buttonVoltar = new javax.swing.JButton();
        labelSolicitacoesC = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSolicitacoesConsulta = new javax.swing.JTable();
        buttonAgendarC = new javax.swing.JButton();
        labelSolicitacoesE = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSolicitacoesExame = new javax.swing.JTable();
        buttonAgendarE = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelDataHora.setText("<data/hora atual>");

        labelIdAtendente.setText("<id atendente>");

        labelAtendente.setText("<nome atendente>");

        buttonVoltar.setBackground(new java.awt.Color(153, 153, 153));
        buttonVoltar.setText("Voltar");

        labelSolicitacoesC.setText("Solicitações de Consulta");

        tableSolicitacoesConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Funcionário", "CRM/COREM", "Solicitação"
            }
        ));
        jScrollPane2.setViewportView(tableSolicitacoesConsulta);

        buttonAgendarC.setBackground(new java.awt.Color(153, 153, 153));
        buttonAgendarC.setText("Agendar");

        labelSolicitacoesE.setText("Solicitações de Exame");

        tableSolicitacoesExame.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Funcionário", "CRM/COREM", "Solicitação"
            }
        ));
        jScrollPane1.setViewportView(tableSolicitacoesExame);

        buttonAgendarE.setBackground(new java.awt.Color(153, 153, 153));
        buttonAgendarE.setText("Agendar");
        buttonAgendarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgendarEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSolicitacoesE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonAgendarC, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonAgendarE, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelSolicitacoesC))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelAtendente)
                                .addComponent(labelDataHora)
                                .addComponent(labelIdAtendente)))))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelAtendente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelIdAtendente))
                    .addComponent(buttonVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDataHora)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelSolicitacoesC, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAgendarC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSolicitacoesE, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAgendarE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void buttonAgendarEActionPerformed(java.awt.event.ActionEvent evt) {                                               
        
    }                                              

    

    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonAgendarC;
    private javax.swing.JButton buttonAgendarE;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAtendente;
    private javax.swing.JLabel labelDataHora;
    private javax.swing.JLabel labelIdAtendente;
    private javax.swing.JLabel labelSolicitacoesC;
    private javax.swing.JLabel labelSolicitacoesE;
    private javax.swing.JTable tableSolicitacoesConsulta;
    private javax.swing.JTable tableSolicitacoesExame;
    // End of variables declaration                  
    
    private ControleDados dados;
    private Atendente atendente; 
}