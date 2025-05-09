package view;

import javax.swing.JOptionPane;

import control.ControleDados;
import model.Enfermeiro;
import model.Exame;
import model.Paciente;

public class TelaExameEnfermeiro extends javax.swing.JFrame {

    public TelaExameEnfermeiro(ControleDados dados, Paciente paciente, Exame exame, Enfermeiro enfermeiro) {
        this.exame = exame;
        this.paciente = paciente;
        this.dados = dados;
        this.enfermeiro = enfermeiro;

        initComponents();
    }
                     
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        botaoVoltar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelPaciente = new javax.swing.JLabel();
        labelExame = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaResultados = new javax.swing.JTextArea();
        labelResultados = new javax.swing.JLabel();
        botaoFinalizarExame = new javax.swing.JButton();
        labelNomePaciente = new javax.swing.JLabel();
        labelNomeExame = new javax.swing.JLabel();
        labelNumCodigo = new javax.swing.JLabel();
        labelData = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText(enfermeiro.getNome());

        jLabel2.setText(enfermeiro.getCorem());

        botaoVoltar.setText("Voltar");
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });
        botaoFinalizarExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizarExameActionPerformed(evt);
            }
        });

        labelPaciente.setText("Paciente:");

        labelExame.setText("Exame:");

        labelCodigo.setText("Codigo:");

        textAreaResultados.setColumns(20);
        textAreaResultados.setRows(5);
        jScrollPane1.setViewportView(textAreaResultados);

        labelResultados.setText("Resultados:");

        botaoFinalizarExame.setText("Finalizar exame");

        labelNomePaciente.setText(paciente.getNome());

        labelNomeExame.setText(exame.getNomeExame());

        labelNumCodigo.setText(exame.getCodigo());

        labelData.setText("Data:");

        jLabel11.setText(exame.getData());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(botaoVoltar)
                .addGap(25, 25, 25))
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(labelExame)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelNomeExame))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(labelCodigo)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelNumCodigo))
                                    .addComponent(labelResultados))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelPaciente)
                        .addGap(18, 18, 18)
                        .addComponent(labelNomePaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelData)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(44, 44, 44))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(botaoFinalizarExame)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(botaoVoltar)))
                .addGap(23, 23, 23)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPaciente)
                    .addComponent(labelNomePaciente)
                    .addComponent(labelData)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelExame)
                    .addComponent(labelNomeExame))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(labelNumCodigo))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(labelResultados)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoFinalizarExame)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                            
       new TelaPrincipalEnfermeiro(dados, enfermeiro).setVisible(true);
       this.dispose();
    }                                           

    private void botaoFinalizarExameActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(textAreaResultados.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Vocé deve preencher todos os campos para Finalizar o Exame." );
        }else{
            this.exame.setResponsavelTecnico(enfermeiro);
            this.exame.setResultado(textAreaResultados.getText());
            dados.getBancoExames().remove(exame);

            new TelaPrincipalEnfermeiro(dados, enfermeiro).setVisible(true);
            this.dispose();
        }
        
     }
  

    // Variables declaration - do not modify                     
    private javax.swing.JButton botaoFinalizarExame;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelExame;
    private javax.swing.JLabel labelNomeExame;
    private javax.swing.JLabel labelNomePaciente;
    private javax.swing.JLabel labelNumCodigo;
    private javax.swing.JLabel labelPaciente;
    private javax.swing.JLabel labelResultados;
    private javax.swing.JTextArea textAreaResultados;
    // End of variables declaration   
    
    private Exame exame;
    private Paciente paciente;
    private ControleDados dados;
    private Enfermeiro enfermeiro;
}