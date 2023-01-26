package view;

public class Receita extends javax.swing.JFrame {

    
    public Receita() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        labelCrm = new javax.swing.JLabel();
        botaoVoltar = new javax.swing.JButton();
        labelNomeMedico = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelPaciente = new javax.swing.JLabel();
        labelCodigoConsulta = new javax.swing.JLabel();
        labelNomePaciente = new javax.swing.JLabel();
        labelTipoConsulta = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();
        labelData = new javax.swing.JLabel();
        labelDescricao = new javax.swing.JLabel();
        textDescricao = new javax.swing.JTextField();
        labelDataHoje = new javax.swing.JLabel();
        botaoFinalizaConsulta = new javax.swing.JButton();
        labelMedicamento = new javax.swing.JLabel();
        textMedicamento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelCrm.setFont(new java.awt.Font("Gujarati MT", 0, 8)); // NOI18N
        labelCrm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCrm.setText("CRM");

        botaoVoltar.setBackground(new java.awt.Color(204, 153, 255));
        botaoVoltar.setFont(new java.awt.Font("Gujarati MT", 1, 13)); // NOI18N
        botaoVoltar.setForeground(new java.awt.Color(255, 255, 255));
        botaoVoltar.setText("Voltar");
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });

        labelNomeMedico.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelNomeMedico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomeMedico.setText("nome_do_medico");

        labelPaciente.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelPaciente.setText("Paciente:");

        labelCodigoConsulta.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelCodigoConsulta.setText("Código de Consulta:");

        labelNomePaciente.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelNomePaciente.setText("nome_paciente");

        labelTipoConsulta.setFont(new java.awt.Font("Gujarati MT", 0, 12)); // NOI18N
        labelTipoConsulta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoConsulta.setText("Tipo_de_consulta");

        labelCodigo.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelCodigo.setText("codigo_consulta");

        labelData.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelData.setText("Data:");

        labelDescricao.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelDescricao.setText("Descrição / Posologia:");

        textDescricao.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textDescricao.setToolTipText("");
        textDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDescricaoActionPerformed(evt);
            }
        });

        labelDataHoje.setFont(new java.awt.Font("Gujarati MT", 0, 13)); // NOI18N
        labelDataHoje.setText("data_de_hoje");

        botaoFinalizaConsulta.setBackground(new java.awt.Color(153, 153, 255));
        botaoFinalizaConsulta.setFont(new java.awt.Font("Gujarati MT", 0, 14)); // NOI18N
        botaoFinalizaConsulta.setText("Receitar");
        botaoFinalizaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizaConsultaActionPerformed(evt);
            }
        });

        labelMedicamento.setText("Medicamento:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCodigoConsulta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelDescricao)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(labelPaciente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelNomePaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelMedicamento)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(textMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelDataHoje))
                            .addComponent(textDescricao)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(labelNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCrm, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoVoltar)
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoFinalizaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelNomeMedico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCrm)
                    .addComponent(botaoVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTipoConsulta)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPaciente)
                    .addComponent(labelNomePaciente)
                    .addComponent(labelDataHoje)
                    .addComponent(labelData))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigoConsulta)
                    .addComponent(labelCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMedicamento)
                    .addComponent(textMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoFinalizaConsulta)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
    }                                           

    private void botaoFinalizaConsultaActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        
    }                                                     

    private void textDescricaoActionPerformed(java.awt.event.ActionEvent evt) {                                              
        
    }                                             

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Receita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton botaoFinalizaConsulta;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelCodigoConsulta;
    private javax.swing.JLabel labelCrm;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDataHoje;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelMedicamento;
    private javax.swing.JLabel labelNomeMedico;
    private javax.swing.JLabel labelNomePaciente;
    private javax.swing.JLabel labelPaciente;
    private javax.swing.JLabel labelTipoConsulta;
    private javax.swing.JTextField textDescricao;
    private javax.swing.JTextField textMedicamento;
    // End of variables declaration                   
}
    

