package view;

import control.ControleDados;
import model.Marcada;
import model.Prontuario;

public class TelaDetalhesConsulta extends javax.swing.JFrame {

    public TelaDetalhesConsulta(Marcada consulta, ControleDados dados, Prontuario p ){
        this.consulta = consulta;
        this.dados = dados;
        this.p = p;
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelNomeMedico = new javax.swing.JLabel();
        labelCmr = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        botaoVoltar = new javax.swing.JButton();
        labelNome = new javax.swing.JLabel();
        labelNomePaciente = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();
        labelCodigoConsulto = new javax.swing.JLabel();
        labelData = new javax.swing.JLabel();
        labelDataConsulta = new javax.swing.JLabel();
        labelDescricao = new javax.swing.JLabel();
        labelDescricaoMedico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelNomeMedico.setText(consulta.getMedico().getNome());

        labelCmr.setText(consulta.getMedico().getCrm());

        botaoVoltar.setText("Voltar");
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });

        labelNome.setText("Nome:");

        labelNomePaciente.setText(consulta.getPaciente().getNome());

        labelCodigo.setText("Código:");

        labelCodigoConsulto.setText(consulta.getCodigo());

        labelData.setText("Data consulta:");

        labelDataConsulta.setText(consulta.getData());

        labelDescricao.setText("Descricao");

        labelDescricaoMedico.setText(consulta.getDescricaoMedica());
        labelDescricaoMedico.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(labelCmr)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(labelNomeMedico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoVoltar)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelNome)
                        .addGap(36, 36, 36)
                        .addComponent(labelNomePaciente))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelCodigoConsulto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelData)
                .addGap(18, 18, 18)
                .addComponent(labelDataConsulta)
                .addGap(54, 54, 54))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDescricao)
                .addGap(18, 18, 18)
                .addComponent(labelDescricaoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNomeMedico)
                    .addComponent(botaoVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCmr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(labelNomePaciente)
                    .addComponent(labelData)
                    .addComponent(labelDataConsulta))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(labelCodigoConsulto))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDescricao)
                    .addComponent(labelDescricaoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {  
        new TelaProntuario(p, dados, consulta).setVisible(true);;
        this.dispose();                                          
        
    }                                           


    // Variables declaration - do not modify                     
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelCmr;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelCodigoConsulto;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDataConsulta;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelDescricaoMedico;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNomeMedico;
    private javax.swing.JLabel labelNomePaciente;
    // End of variables declaration   

    private Marcada consulta;
    private ControleDados dados;
    private Prontuario p;
    
    
}

