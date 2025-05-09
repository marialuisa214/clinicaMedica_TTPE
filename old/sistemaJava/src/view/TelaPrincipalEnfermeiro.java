package view;

import javax.swing.JOptionPane;

import control.ControleDados;
import model.*;

public class TelaPrincipalEnfermeiro extends javax.swing.JFrame {

    public TelaPrincipalEnfermeiro(ControleDados dados, Enfermeiro enfermeiro) {
        this.enfermeiro  = enfermeiro;
        this.dados = dados;
        this.tabelaTriagem = new TabelaTriagem(dados); 
        this.tabelaExame = new TabelaExameEnfermeiro(dados);
        initComponents();

        jTable1.setModel(tabelaTriagem);
        jTable2.setModel(tabelaExame);
    }
                              
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelNomeEnfer = new javax.swing.JLabel();
        labelNomeEnfermeiro = new javax.swing.JLabel();
        labelCoremEnfer = new javax.swing.JLabel();
        labelCoremEnfemeiro = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelTriagem = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonFazerTriagem = new javax.swing.JButton();
        labelExame = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonFazerExame = new javax.swing.JButton();
        buttonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelNomeEnfer.setText("Enfermeiro(a):");

        labelNomeEnfermeiro.setText(enfermeiro.getNome());

        labelCoremEnfer.setText("COREM:");

        labelCoremEnfemeiro.setText(this.enfermeiro.getCorem());

        labelTriagem.setText("Triagem:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        buttonFazerTriagem.setText("Fazer triagem");
        buttonFazerTriagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFazerTriagemActionPerformed(evt);
            }
        });

        labelExame.setText("Exame:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        buttonFazerExame.setText("Fazer exame");
        buttonFazerExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFazerExameActionPerformed(evt);
            }
        });

        buttonVoltar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        buttonVoltar.setText("Voltar");
        buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelExame)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelNomeEnfer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNomeEnfermeiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addComponent(buttonVoltar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTriagem)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelCoremEnfer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelCoremEnfemeiro)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonFazerExame, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonFazerTriagem, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonVoltar)
                    .addComponent(labelNomeEnfermeiro)
                    .addComponent(labelNomeEnfer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCoremEnfemeiro)
                    .addComponent(labelCoremEnfer))
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTriagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonFazerTriagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(labelExame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonFazerExame)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        // </editor-fold>
    }                      

    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {  
        new TelaLogin(dados).setVisible(true);
        this.dispose();                                           
        
    }                                            

    private void buttonFazerTriagemActionPerformed(java.awt.event.ActionEvent evt) {  
        if(jTable1.getSelectedRow() != -1 ){

            Entrada e = tabelaTriagem.selecionaItem(jTable1.getSelectedRow());
            new TelaTriagemEnfermeiro(dados, e, enfermeiro).setVisible(true);                                               
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma triagem antes de ir para a proxima página!");
        }
        
    }                                                  

    private void buttonFazerExameActionPerformed(java.awt.event.ActionEvent evt) {  
        if(jTable2.getSelectedRow() != -1 ){
            Exame exame = tabelaExame.selecionaItem(jTable2.getSelectedRow());
            new TelaExameEnfermeiro(dados, exame.getPaciente(), exame, enfermeiro).setVisible(true);
                this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Selecione um exame antes de ir para a proxima página!");
        }

        
    }                                               
    

    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonFazerExame;
    private javax.swing.JButton buttonFazerTriagem;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel labelCoremEnfemeiro;
    private javax.swing.JLabel labelCoremEnfer;
    private javax.swing.JLabel labelExame;
    private javax.swing.JLabel labelNomeEnfer;
    private javax.swing.JLabel labelNomeEnfermeiro;
    private javax.swing.JLabel labelTriagem;
    // End of variables declaration          
    private ControleDados dados;
    private Enfermeiro enfermeiro;
    private TabelaTriagem tabelaTriagem;
    private TabelaExameEnfermeiro tabelaExame;
}
