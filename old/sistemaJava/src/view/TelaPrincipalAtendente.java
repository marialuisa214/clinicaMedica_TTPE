package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import control.*;
import model.Atendente;



public class TelaPrincipalAtendente extends javax.swing.JFrame {

    
    public TelaPrincipalAtendente(ControleDados dados, Atendente atendente) {
        this.atendente = atendente;
        this.dados = dados;
        this.c = new Date();


        initComponents();
      }
                         
      private void initComponents() {

        labelAtendente = new javax.swing.JLabel();
        labelIdAtendente = new javax.swing.JLabel();
        labelDataHora = new javax.swing.JLabel();
        fieldBuscaPaciente = new javax.swing.JTextField();
        buttonVoltar = new javax.swing.JButton();
        buttonBuscaPaciente = new javax.swing.JButton();
        buttonSolicitações = new javax.swing.JButton();
        buttonCadastraPaciente = new javax.swing.JButton();
        buttonAgendaConsulta = new javax.swing.JButton();
        buttonAgendaExame = new javax.swing.JButton();
        buttonNovaEntrada = new javax.swing.JButton();
        labelCadastrado = new javax.swing.JLabel();
        labelNaocadastrado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelAtendente.setText(atendente.getNome());

        labelIdAtendente.setText(atendente.getIdAtendente());

        labelDataHora.setText(formatador.format(c));

       

        buttonVoltar.setBackground(new java.awt.Color(153, 153, 153));
        buttonVoltar.setText("voltar");
        buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoltarPacienteActionPerformed(evt);
            }
        });

        buttonBuscaPaciente.setBackground(new java.awt.Color(153, 153, 153));
        buttonBuscaPaciente.setText("Buscar Paciente");
        buttonBuscaPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscaPacienteActionPerformed(evt);
            }
        });

        buttonSolicitações.setBackground(new java.awt.Color(153, 153, 153));
        buttonSolicitações.setText("Solicitações");
        buttonSolicitações.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSolicitaçõesActionPerformed(evt);
            }
        });

        buttonCadastraPaciente.setBackground(new java.awt.Color(153, 153, 153));
        buttonCadastraPaciente.setText("Novo Paciente");
        buttonCadastraPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastraPacienteActionPerformed(evt);
            }
        });

        buttonAgendaConsulta.setBackground(new java.awt.Color(153, 153, 153));
        buttonAgendaConsulta.setText("Agenda Consulta");
        buttonAgendaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgendaConsultaActionPerformed(evt);
            }
        });

        buttonAgendaExame.setBackground(new java.awt.Color(153, 153, 153));
        buttonAgendaExame.setText("Agenda Exame");
        buttonAgendaExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgendaExameActionPerformed(evt);
            }
        });

        buttonNovaEntrada.setBackground(new java.awt.Color(153, 153, 153));
        buttonNovaEntrada.setText("Nova Entrada");
        buttonNovaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNovaEntradaActionPerformed(evt);
            }
        });

        labelCadastrado.setText("Este paciente está cadastrado!");
        labelCadastrado.setVisible(false);
        labelNaocadastrado.setText("Este paciente não está cadastrado!");
        labelNaocadastrado.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(buttonVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelAtendente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataHora, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelIdAtendente, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldBuscaPaciente)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCadastrado)
                                    .addComponent(labelNaocadastrado))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(buttonAgendaExame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonAgendaConsulta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(buttonCadastraPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonSolicitações, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonBuscaPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonNovaEntrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelAtendente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelIdAtendente))
                    .addComponent(buttonVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDataHora)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldBuscaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBuscaPaciente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSolicitações)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCadastraPaciente))
                    .addComponent(labelCadastrado)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(labelNaocadastrado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAgendaConsulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAgendaExame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonNovaEntrada)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>                        

    private void buttonVoltarPacienteActionPerformed(java.awt.event.ActionEvent evt) {    //GABI_MINHA AMIGA_faz ai pra mim!                                                
        new TelaLogin(dados).setVisible(true);
        this.dispose();
    }
    
    private void buttonBuscaPacienteActionPerformed(java.awt.event.ActionEvent evt) {    //GABI_MINHA AMIGA_faz ai pra mim!                                                
        for(int i = 0; i <= dados.getBancoPacientes().size(); i++){
            if(fieldBuscaPaciente.getText().equals(dados.getBancoPacientes().get(i).getCpf())){
                labelCadastrado.setVisible(true);
            }
        }
    } 
                                                  

    private void buttonCadastraPacienteActionPerformed(java.awt.event.ActionEvent evt) {  
        new TelaCadastraPaciente(dados, atendente).setVisible(true);
        this.dispose();

       
    }                                                      

    private void buttonSolicitaçõesActionPerformed(java.awt.event.ActionEvent evt) {   
        new TelaSolicitacoesAtendente(dados, atendente).setVisible(true);
        this.dispose();

        
    }                                                  

    private void buttonAgendaConsultaActionPerformed(java.awt.event.ActionEvent evt) { 
        new TelaAgendaConsulta(dados, atendente).setVisible(true); 
        this.dispose();                                       
        
    }                                                    

    private void buttonAgendaExameActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        new TelaAgendaExame(dados, atendente).setVisible(true);  
        this.dispose();
    }                                                 

    private void buttonNovaEntradaActionPerformed(java.awt.event.ActionEvent evt) {    
        new TelaRegistraEntrada(dados, atendente).setVisible(true);  
        this.dispose();                                            
        
    }       

    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonAgendaConsulta;
    private javax.swing.JButton buttonAgendaExame;
    private javax.swing.JButton buttonBuscaPaciente;
    private javax.swing.JButton buttonCadastraPaciente;
    private javax.swing.JButton buttonNovaEntrada;
    private javax.swing.JButton buttonSolicitações;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JTextField fieldBuscaPaciente;
    private javax.swing.JLabel labelAtendente;
    private javax.swing.JLabel labelCadastrado;
    private javax.swing.JLabel labelDataHora;
    private javax.swing.JLabel labelIdAtendente;
    private javax.swing.JLabel labelNaocadastrado;
    // End of variables declaration      
    

    private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    private Date c;
    private ControleDados dados;
    private Atendente atendente;
}