package view;

import javax.swing.JOptionPane;

import control.*;
import model.*;

public class TelaLogin extends javax.swing.JFrame {
    public TelaLogin(ControleDados controle) {
        initComponents();
        this.controle = controle;
    }

    private void initComponents() {

        // buttonGroup1 = new javax.swing.ButtonGroup();
        passwordSenha = new javax.swing.JPasswordField();
        textUsuario = new javax.swing.JTextField();
        labelUsuario = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        labelLogin = new javax.swing.JLabel();
        botaoEntrar = new javax.swing.JButton();
        radioAdm = new javax.swing.JRadioButton();
        radioAtendente = new javax.swing.JRadioButton();
        radioMedico = new javax.swing.JRadioButton();
        radioEnfermeiro = new javax.swing.JRadioButton();
        controle = new ControleDados();
        groupButton = new javax.swing.ButtonGroup();

        groupButton.add(radioMedico);
        groupButton.add(radioEnfermeiro);
        groupButton.add(radioAdm);
        groupButton.add(radioAtendente);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frameLogin");

        labelUsuario.setFont(new java.awt.Font("Gujarati MT", 0, 14)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(102, 102, 255));
        labelUsuario.setText("Usuario");

        labelSenha.setFont(new java.awt.Font("Gujarati MT", 0, 14)); // NOI18N
        labelSenha.setForeground(new java.awt.Color(102, 102, 255));
        labelSenha.setText("Senha");

        labelLogin.setFont(new java.awt.Font("Gujarati MT", 0, 24)); // NOI18N
        labelLogin.setForeground(new java.awt.Color(102, 102, 255));
        labelLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogin.setText("Login");
        labelLogin.setMaximumSize(new java.awt.Dimension(44, 24));
        labelLogin.setMinimumSize(new java.awt.Dimension(44, 24));

        botaoEntrar.setFont(new java.awt.Font("Gujarati MT", 0, 18)); // NOI18N
        botaoEntrar.setForeground(new java.awt.Color(102, 0, 255));
        botaoEntrar.setText("Entrar");
        botaoEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEntrarActionPerformed(evt);
            }
        });


        radioAdm.setFont(new java.awt.Font("Gujarati MT", 0, 14)); // NOI18N
        radioAdm.setText("Administrador");

        radioAtendente.setFont(new java.awt.Font("Gujarati MT", 0, 14)); // NOI18N
        radioAtendente.setText("Atendente");

        radioMedico.setFont(new java.awt.Font("Gujarati MT", 0, 14)); // NOI18N
        radioMedico.setText("Médico");

        radioEnfermeiro.setFont(new java.awt.Font("Gujarati MT", 0, 14)); // NOI18N
        radioEnfermeiro.setText("Enfermeiro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(botaoEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, 90,
                                                Short.MAX_VALUE)
                                        .addComponent(labelSenha)
                                        .addComponent(passwordSenha)
                                        .addComponent(labelUsuario)
                                        .addComponent(labelLogin, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textUsuario))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(radioAdm)
                                .addGap(34, 34, 34)
                                .addComponent(radioAtendente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43,
                                        Short.MAX_VALUE)
                                .addComponent(radioEnfermeiro)
                                .addGap(26, 26, 26)
                                .addComponent(radioMedico)
                                .addGap(28, 28, 28)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21,
                                        Short.MAX_VALUE)
                                .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelSenha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordSenha, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(radioAtendente)
                                        .addComponent(radioAdm)
                                        .addComponent(radioEnfermeiro)
                                        .addComponent(radioMedico))
                                .addGap(26, 26, 26)
                                .addComponent(botaoEntrar)
                                .addGap(32, 32, 32)));

        pack();
    }// </editor-fold>

    private void botaoEntrarActionPerformed(java.awt.event.ActionEvent evt) {

        if (textUsuario.getText().isEmpty() || passwordSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Você deve preencher um usuário e senha");
            return;
        }

        if (radioAdm.isSelected() || radioAtendente.isSelected() || radioMedico.isSelected()
                || radioEnfermeiro.isSelected()) {
            if (radioMedico.isSelected()) {
                for (Medico m : this.controle.getBancoMedicos()) {
                    if (textUsuario.getText().equals(m.getUsuario())) {
                        if (passwordSenha.getText().equals(m.getSenha())) {
                            new TelaPrincipalMedico(controle, m).setVisible(true);
                            this.dispose();
                            return;
                        }
                        break;
                    }
                }

                JOptionPane.showMessageDialog(rootPane, "Usuário e/ou senha incorretos.");
            } else if (radioAdm.isSelected()) {
                for (Administrador administrador : this.controle.getBancoAdministradores()) {
                    if (textUsuario.getText().equals(administrador.getUsuario())) {
                        if (passwordSenha.getText().equals(administrador.getSenha())) {
                            new TelaPrincipalAdministrador(controle, administrador).setVisible(true);
                            this.dispose();
                            return;
                        }
                        break;
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Usuário e/ou senha incorretos.");

            } else if (radioAtendente.isSelected()) {
                for (Atendente atendente : this.controle.getaBancoAtendente()) {
                    if (textUsuario.getText().equals(atendente.getUsuario())) {
                        if (passwordSenha.getText().equals(atendente.getSenha())) {
                            new TelaPrincipalAtendente(controle, atendente).setVisible(true);
                            this.dispose();
                            return;
                        }
                        break;
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Usuário e/ou senha incorretos.");

            } else if (radioEnfermeiro.isSelected()) {
                for (Enfermeiro e : this.controle.getBancoEnfermeiros()) {
                    if (textUsuario.getText().equals(e.getUsuario())) {
                        if (passwordSenha.getText().equals(e.getSenha())) {
                            new TelaPrincipalEnfermeiro(controle, e).setVisible(true);
                            this.dispose();
                            return;
                        }
                        break;
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Usuário e/ou senha incorretos.");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Você deve selecionar um tipo de usuário.");
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton botaoEntrar;
    // private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPasswordField passwordSenha;
    private javax.swing.JRadioButton radioAdm;
    private javax.swing.JRadioButton radioAtendente;
    private javax.swing.JRadioButton radioEnfermeiro;
    private javax.swing.JRadioButton radioMedico;
    private javax.swing.JTextField textUsuario;
    private javax.swing.ButtonGroup groupButton;
    // End of variables declaration
    private ControleDados controle;
}
