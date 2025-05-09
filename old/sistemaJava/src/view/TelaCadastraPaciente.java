package view;

import javax.swing.JOptionPane;

import control.ControleDados;
import model.Atendente;

public class TelaCadastraPaciente extends javax.swing.JFrame {
  
    public TelaCadastraPaciente(ControleDados dados, Atendente atendente) {
        this.dados = dados;
        this.atendente = atendente;
    
      initComponents();
    }
                        
    private void initComponents() {

        inputNome = new javax.swing.JTextField();
        inputCpf = new javax.swing.JTextField();
        inputNascimento = new javax.swing.JTextField();
        patologiascroll = new javax.swing.JScrollPane();
        restricoesMedicas = new javax.swing.JTextArea();
        selecaoSexoFeminino = new javax.swing.JCheckBox();
        selecaoSexoMasculino = new javax.swing.JCheckBox();
        labelNomePaciente = new javax.swing.JLabel();
        labelCpfPaciente = new javax.swing.JLabel();
        labelSexoPaciente = new javax.swing.JLabel();
        labelNascimentoPaciente = new javax.swing.JLabel();
        labelObsMedica = new javax.swing.JLabel();
        buttonCadastraPaciente = new javax.swing.JButton();
        labelCadastroPaciente = new javax.swing.JLabel();
        inputEndereco = new javax.swing.JTextField();
        labelEndereco = new javax.swing.JLabel();
        labelTelefone = new javax.swing.JLabel();
        inputTelefone = new javax.swing.JTextField();
        labelCidadeEstado = new javax.swing.JLabel();
        inputCidadeEstado = new javax.swing.JTextField();
        labelRG = new javax.swing.JLabel();
        inputRG = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        inputEmail = new javax.swing.JTextField();
        buttonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        inputNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNomeActionPerformed(evt);
            }
        });

        inputCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputCpfActionPerformed(evt);
            }
        });

        inputNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNascimentoActionPerformed(evt);
            }
        });

        restricoesMedicas.setColumns(20);
        restricoesMedicas.setRows(5);
        restricoesMedicas.setText("(alergia, alerta ou restrição médica, etc..) ");
        patologiascroll.setViewportView(restricoesMedicas);

        selecaoSexoFeminino.setText("Feminino");
        selecaoSexoFeminino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecaoSexoFemininoActionPerformed(evt);
            }
        });

        selecaoSexoMasculino.setText("Masculino");
        selecaoSexoMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecaoSexoMasculinoActionPerformed(evt);
            }
        });

        labelNomePaciente.setText("Nome do Paciente:");

        labelCpfPaciente.setText("CPF:");

        labelSexoPaciente.setText("Sexo:");

        labelNascimentoPaciente.setText("Data de nascimento:");

        labelObsMedica.setText("possui alguma observação médica?");

        buttonCadastraPaciente.setBackground(new java.awt.Color(153, 153, 153));
        buttonCadastraPaciente.setText("Cadastrar Novo Paciente");
        buttonCadastraPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastraPacienteActionPerformed(evt);
            }
        });

        labelCadastroPaciente.setText("Cadastro Paciente");

        inputEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEnderecoActionPerformed(evt);
            }
        });

        labelEndereco.setText("Endereço residencial:");

        labelTelefone.setText("Telefone:");

        inputTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTelefoneActionPerformed(evt);
            }
        });

        labelCidadeEstado.setText("Cidade/Estado:");

        labelRG.setText("RG:");

        inputRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputRGActionPerformed(evt);
            }
        });

        labelEmail.setText("E-mail:");

        inputEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEmailActionPerformed(evt);
            }
        });

        buttonVoltar.setBackground(new java.awt.Color(153, 153, 153));
        buttonVoltar.setText("voltar");
        buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patologiascroll)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelCidadeEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputCidadeEstado))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputEmail))
                    .addComponent(inputEndereco)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelObsMedica)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(buttonCadastraPaciente))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelEndereco)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelRG)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(inputRG, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(labelCpfPaciente)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(inputCpf))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelTelefone)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(inputTelefone))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelNascimentoPaciente, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(labelSexoPaciente)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(selecaoSexoMasculino)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(inputNascimento)
                                        .addComponent(selecaoSexoFeminino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelNomePaciente)
                                        .addComponent(buttonVoltar))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelCadastroPaciente)
                                        .addComponent(inputNome, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCadastroPaciente)
                    .addComponent(buttonVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNomePaciente)
                    .addComponent(inputNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelRG)
                                .addComponent(labelCpfPaciente))
                            .addComponent(inputCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputRG, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSexoPaciente)
                    .addComponent(selecaoSexoMasculino)
                    .addComponent(selecaoSexoFeminino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNascimentoPaciente)
                    .addComponent(inputNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefone)
                    .addComponent(inputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCidadeEstado)
                    .addComponent(inputCidadeEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEndereco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelObsMedica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patologiascroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCadastraPaciente)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void inputNomeActionPerformed(java.awt.event.ActionEvent evt) {                                          
       
    }                                         

    private void inputCpfActionPerformed(java.awt.event.ActionEvent evt) {                                         
       
    }                                        

    private void inputNascimentoActionPerformed(java.awt.event.ActionEvent evt) {                                                
       
    }                                               

    private void selecaoSexoFemininoActionPerformed(java.awt.event.ActionEvent evt) {                                                     
      this.selecaoSexoMasculino.setSelected(false);
      this.selectSexo = 'F';
    }                                                    

    private void selecaoSexoMasculinoActionPerformed(java.awt.event.ActionEvent evt) {    
      this.selecaoSexoFeminino.setSelected(false);                                                 
      this.selectSexo = 'M';
    }                                                    

    private void buttonCadastraPacienteActionPerformed(java.awt.event.ActionEvent evt) {                                                       
      
        if(inputNome.getText().isEmpty() || inputNome.getText().isEmpty() || inputRG.getText().isEmpty() || inputCpf.getText().isEmpty() || inputNascimento.getText().isEmpty() || inputTelefone.getText().isEmpty() || inputEmail.getText().isEmpty() || inputCidadeEstado.getText().isEmpty() || inputEndereco.getText().isEmpty() || restricoesMedicas.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Você deve preencher todos os campos para finalizar o cadastro." );
        }
        else{dados.cadastraPaciente(this.inputNome.getText(), this.inputRG.getText(), this.inputCpf.getText(), this.selectSexo, this.inputNascimento.getText(), this.inputTelefone.getText(), this.inputEmail.getText(), this.inputCidadeEstado.getText(), this.inputEndereco.getText(), this.restricoesMedicas.getText());
            new TelaPrincipalAtendente(dados, atendente).setVisible(true);  
            this.dispose();
        }
    }                                                      

    private void inputEnderecoActionPerformed(java.awt.event.ActionEvent evt) {                                              
       
    }                                             

    private void inputTelefoneActionPerformed(java.awt.event.ActionEvent evt) {                                              
       
    }                                             

    private void inputRGActionPerformed(java.awt.event.ActionEvent evt) {                                        
       
    }                                       

    private void inputEmailActionPerformed(java.awt.event.ActionEvent evt) {                                           
       
    }                                          

    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        this.dispose();
        new TelaPrincipalAtendente(dados, atendente).setVisible(true);
    }                                            

    

    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonCadastraPaciente;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JTextField inputCidadeEstado;
    private javax.swing.JTextField inputCpf;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JTextField inputEndereco;
    private javax.swing.JTextField inputNascimento;
    private javax.swing.JTextField inputNome;
    private javax.swing.JTextField inputRG;
    private javax.swing.JTextField inputTelefone;
    private javax.swing.JLabel labelCadastroPaciente;
    private javax.swing.JLabel labelCidadeEstado;
    private javax.swing.JLabel labelCpfPaciente;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelNascimentoPaciente;
    private javax.swing.JLabel labelNomePaciente;
    private javax.swing.JLabel labelObsMedica;
    private javax.swing.JLabel labelRG;
    private javax.swing.JLabel labelSexoPaciente;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JScrollPane patologiascroll;
    private javax.swing.JTextArea restricoesMedicas;
    private javax.swing.JCheckBox selecaoSexoMasculino;
    private javax.swing.JCheckBox selecaoSexoFeminino;
    
    private Atendente atendente;
    private ControleDados dados;
    private char selectSexo;
    // End of variables declaration                   
}

