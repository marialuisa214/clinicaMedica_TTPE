package view;

import javax.swing.JOptionPane;

import control.ControleDados;
import model.Enfermeiro;
import model.Entrada;
import model.Paciente;

public class TelaAtualizaProntuario extends javax.swing.JFrame {

    public TelaAtualizaProntuario(Paciente paciente, ControleDados dados, Enfermeiro enfermeiro, Entrada entrada) {
        this.paciente = paciente;
        this.dados = dados;
        this.entrada = entrada;
        this.enfermeiro = enfermeiro;
        initComponents();
    }
                          
    private void initComponents() {

        labelNomeEnfer = new javax.swing.JLabel();
        labelNomeEnfermeiro = new javax.swing.JLabel();
        labelCoremEnfer = new javax.swing.JLabel();
        labelCoremEnfermeiro = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelNomePac = new javax.swing.JLabel();
        labelNomePaciente = new javax.swing.JLabel();
        labelIdadePac = new javax.swing.JLabel();
        labelIdadePaciente = new javax.swing.JLabel();
        labelPesoPac = new javax.swing.JLabel();
        TextFieldPesoPaciente = new javax.swing.JTextField();
        labelAlturaPac = new javax.swing.JLabel();
        TestFieldAlturaPaciente = new javax.swing.JTextField();
        buttonEnviarProntuario = new javax.swing.JButton();
        buttonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelNomeEnfer.setText("Nome do enfermeiro(a):");

        labelNomeEnfermeiro.setText(enfermeiro.getNome());

        labelCoremEnfer.setText("COREM:");

        labelCoremEnfermeiro.setText(enfermeiro.getCorem());

        labelNomePac.setText("Nome do paciente:");

        labelNomePaciente.setText(paciente.getNome());

        labelIdadePac.setText("Idade:");

        labelIdadePaciente.setText(String.valueOf(paciente.getIdade()));

        labelPesoPac.setText("Peso:");

        labelAlturaPac.setText("Altura:");

        

        buttonEnviarProntuario.setText("Enviar prontuário");
        buttonEnviarProntuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnviarProntuarioActionPerformed(evt);
            }
        });

        buttonVoltar.setText("Voltar");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonEnviarProntuario))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelNomeEnfer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelNomeEnfermeiro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                                .addComponent(buttonVoltar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelCoremEnfer)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelCoremEnfermeiro))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelNomePac)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelNomePaciente))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelIdadePac)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelIdadePaciente))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelPesoPac)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TextFieldPesoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelAlturaPac)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TestFieldAlturaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonVoltar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNomeEnfer, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNomeEnfermeiro)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCoremEnfer)
                    .addComponent(labelCoremEnfermeiro))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNomePac)
                    .addComponent(labelNomePaciente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIdadePac)
                    .addComponent(labelIdadePaciente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPesoPac)
                    .addComponent(TextFieldPesoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAlturaPac)
                    .addComponent(TestFieldAlturaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonEnviarProntuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                   

    private void buttonEnviarProntuarioActionPerformed(java.awt.event.ActionEvent evt) {  
        if(TestFieldAlturaPaciente.getText().isEmpty()  || TextFieldPesoPaciente.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "erro");

        } else{
            this.paciente.getProntuario().setPeso(TextFieldPesoPaciente.getText());
            this.paciente.getProntuario().setAltura(TestFieldAlturaPaciente.getText());
            new TelaTriagemEnfermeiro(dados, entrada, enfermeiro).setVisible(true);
            this.dispose();
        }                  
        
    }                                                      

    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        new TelaTriagemEnfermeiro(dados, entrada, enfermeiro).setVisible(true);
        this.dispose();
    }                                            

    // Variables declaration - do not modify                     
    private javax.swing.JTextField TestFieldAlturaPaciente;
    private javax.swing.JTextField TextFieldPesoPaciente;
    private javax.swing.JButton buttonEnviarProntuario;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelAlturaPac;
    private javax.swing.JLabel labelCoremEnfer;
    private javax.swing.JLabel labelCoremEnfermeiro;
    private javax.swing.JLabel labelIdadePac;
    private javax.swing.JLabel labelIdadePaciente;
    private javax.swing.JLabel labelNomeEnfer;
    private javax.swing.JLabel labelNomeEnfermeiro;
    private javax.swing.JLabel labelNomePac;
    private javax.swing.JLabel labelNomePaciente;
    private javax.swing.JLabel labelPesoPac;
    // End of variables declaration      
    
    private Enfermeiro enfermeiro;
    private Paciente paciente;
    private ControleDados dados;
    private Entrada entrada;
}