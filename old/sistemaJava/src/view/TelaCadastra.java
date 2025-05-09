package view;

import control.ControleDados;
import model.Administrador;

public class TelaCadastra extends javax.swing.JFrame {

  
    public TelaCadastra(ControleDados dados, Administrador adm ) {
        this.dados = dados;
        this.adm = adm;
        initComponents();
    }

    private void initComponents() {

        botaoVoltar = new javax.swing.JButton();
        radioMedico = new javax.swing.JRadioButton();
        labeNovoFuncionario = new javax.swing.JLabel();
        radioAtendente = new javax.swing.JRadioButton();
        labelNome = new javax.swing.JLabel();
        formatedData = new javax.swing.JFormattedTextField();
        labelCPF = new javax.swing.JLabel();
        radioEnfermeiro = new javax.swing.JRadioButton();
        labelRG = new javax.swing.JLabel();
        radioADM = new javax.swing.JRadioButton();
        labelSexo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelInfo = new javax.swing.JLabel();
        LabelEspecialidade = new javax.swing.JLabel();
        botaoFinaliza = new javax.swing.JButton();
        botaoSenha = new javax.swing.JButton();
        textInfo = new javax.swing.JTextField();
        textEspecialidade = new javax.swing.JTextField();
        labelUsuario = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        labelUserGerado = new javax.swing.JLabel();
        labelSenhaGerada = new javax.swing.JLabel();
        checkMasculino = new javax.swing.JCheckBox();
        checkFeminino = new javax.swing.JCheckBox();
        textNome = new javax.swing.JTextField();
        textRG = new javax.swing.JTextField();
        textCpf = new javax.swing.JTextField();
        textTelefone = new javax.swing.JTextField();
        textEmail = new javax.swing.JTextField();
        labelDataNasc = new javax.swing.JLabel();
        textEstado = new javax.swing.JTextField();
        textEndereco = new javax.swing.JTextField();
        labelTelefone = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelEstado = new javax.swing.JLabel();
        labelEndereco = new javax.swing.JLabel();

        groupButton = new javax.swing.ButtonGroup();

        groupButton.add(radioMedico);
        groupButton.add(radioEnfermeiro);
        groupButton.add(radioADM);
        groupButton.add(radioAtendente);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botaoVoltar.setText("Voltar");

        radioMedico.setText("Medico");
        radioMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMedicoActionPerformed(evt);
            }
        });
        radioADM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioADMActionPerformed(evt);
            }
        });radioAtendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAtendenteActionPerformed(evt);
            }
        });radioEnfermeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEnfermeiroActionPerformed(evt);
            }
        });

        labeNovoFuncionario.setText("Novo Funcionario!");

        radioAtendente.setText("Atendente");

        labelNome.setText("Nome:");

        

        labelCPF.setText("CPF:");

        radioEnfermeiro.setText("Enfermeiro");

        labelRG.setText("RG:");

        radioADM.setText("Administrador");

        labelSexo.setText("Sexo:");

        

        botaoFinaliza.setText("Finalizar!");
        botaoFinaliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizaActionPerformed(evt);
            }
        });

        botaoSenha.setText("Gerar Senha");
        botaoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSenhaActionPerformed(evt);
            }
        });

        
        labelInfo.setText("CRM:");
        labelInfo.setVisible(false);

        LabelEspecialidade.setText("Especialidade:");
        LabelEspecialidade.setVisible(false);

        labelUsuario.setText("Usuario:");
        labelUsuario.setVisible(false);

        labelSenha.setText("Senha:");
        labelSenha.setVisible(false);

        labelUserGerado.setText("jLabel15");
        labelUserGerado.setVisible(false);

        labelSenhaGerada.setText("jLabel16");
        labelSenhaGerada.setVisible(false);

        textInfo.setVisible(false);
        textEspecialidade.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textInfo))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelEspecialidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textEspecialidade)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 33, Short.MAX_VALUE)
                                .addComponent(botaoSenha)
                                .addGap(39, 39, 39)))))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSenha)
                            .addComponent(labelUsuario))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUserGerado, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSenhaGerada, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(botaoFinaliza)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInfo)
                    .addComponent(textInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario)
                    .addComponent(labelUserGerado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEspecialidade)
                    .addComponent(textEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenha)
                    .addComponent(labelSenhaGerada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSenha)
                    .addComponent(botaoFinaliza))
                .addGap(28, 28, 28))
        );

        checkMasculino.setText("Masculino");

        checkFeminino.setText("Feminino");
        ;

        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });


        labelDataNasc.setText("Data Nascimento:");


        labelTelefone.setText("Telefone:");

        labelEmail.setText("E-mail:");

        labelEstado.setText("Cidade/Estado");

        labelEndereco.setText("Endereço:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(checkMasculino))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(radioMedico)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(labeNovoFuncionario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoVoltar)
                                .addGap(15, 15, 15))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(checkFeminino)
                                        .addGap(61, 61, 61)
                                        .addComponent(labelDataNasc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(formatedData, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(radioAtendente)
                                        .addGap(46, 46, 46)
                                        .addComponent(radioEnfermeiro)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCPF)
                                .addGap(18, 18, 18)
                                .addComponent(textCpf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelRG)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textRG, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelTelefone)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(radioADM)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelSexo)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelNome)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelEmail)
                                                .addGap(18, 18, 18)
                                                .addComponent(textEmail)
                                                .addGap(169, 169, 169))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelEndereco)
                                                .addGap(18, 18, 18)
                                                .addComponent(textEndereco))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelEstado)
                                                .addGap(18, 18, 18)
                                                .addComponent(textEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoVoltar)
                    .addComponent(labeNovoFuncionario))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCPF)
                    .addComponent(labelRG)
                    .addComponent(textRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSexo)
                    .addComponent(checkMasculino)
                    .addComponent(checkFeminino)
                    .addComponent(labelDataNasc)
                    .addComponent(formatedData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefone)
                    .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEstado)
                    .addComponent(textEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEndereco)
                    .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioEnfermeiro)
                    .addComponent(radioAtendente)
                    .addComponent(radioMedico)
                    .addComponent(radioADM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void radioMedicoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        labelInfo.setVisible(true);
        LabelEspecialidade.setVisible(true);
        textInfo.setVisible(true);
        textEspecialidade.setVisible(true);
        //cadastra MedicoAqui!
    }   

    private void radioADMActionPerformed(java.awt.event.ActionEvent evt) {                                            
        labelInfo.setText("Novo ID: ");
        labelInfo.setVisible(true);
        textInfo.setVisible(true);


        textEspecialidade.setVisible(false);
        LabelEspecialidade.setVisible(false);
        //cadastra ADM!
    } 
    
    
    private void radioAtendenteActionPerformed(java.awt.event.ActionEvent evt) {                                            
        labelInfo.setText("Novo ID: ");
        labelInfo.setVisible(true);
        textInfo.setVisible(true);

        textEspecialidade.setVisible(false);
        LabelEspecialidade.setVisible(false);
        //cadastra atendente

    } 
    
    
    private void radioEnfermeiroActionPerformed(java.awt.event.ActionEvent evt) {                                            
        labelInfo.setText("COREM: ");
        labelInfo.setVisible(true);
        LabelEspecialidade.setVisible(true);

        textEspecialidade.setVisible(false);
        LabelEspecialidade.setVisible(false);
        //cadastra enfermeiro!
    }                                         
                                          

    private void botaoFinalizaActionPerformed(java.awt.event.ActionEvent evt) {     
        
    }  


    private void botaoSenhaActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // Gerar user e  senha Aleatoria (usar a funfao random)
    }                                           
                                       

                                                

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                       
        new TelaPrincipalAdministrador(dados, adm).setVisible(true);
        this.dispose();
    }                                      

    // Variables declaration - do not modify                     
    private javax.swing.JLabel LabelEspecialidade;
    private javax.swing.JButton botaoFinaliza;
    private javax.swing.JButton botaoSenha;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JCheckBox checkFeminino;
    private javax.swing.JCheckBox checkMasculino;
    private javax.swing.JFormattedTextField formatedData;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labeNovoFuncionario;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelDataNasc;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelRG;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelSenhaGerada;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JLabel labelUserGerado;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JRadioButton radioADM;
    private javax.swing.JRadioButton radioAtendente;
    private javax.swing.JRadioButton radioEnfermeiro;
    private javax.swing.JRadioButton radioMedico;
    private javax.swing.JTextField textCpf;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textEndereco;
    private javax.swing.JTextField textEspecialidade;
    private javax.swing.JTextField textEstado;
    private javax.swing.JTextField textInfo;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textRG;
    private javax.swing.JTextField textTelefone;

    private javax.swing.ButtonGroup groupButton;
    // End of variables declaration    
    
    private Administrador adm;
    private ControleDados dados;

}
