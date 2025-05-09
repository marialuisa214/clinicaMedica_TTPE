package view;

import javax.swing.JOptionPane;

import control.ControleDados;
import model.Atendente;
import model.Entrada;
import model.Paciente;
import model.TabelaPaciente;

public class TelaRegistraEntrada extends javax.swing.JFrame {

    public TelaRegistraEntrada(ControleDados dados, Atendente atendente) {
        this.dados = dados;
        this.atendente = atendente;
        this.tabela  = new TabelaPaciente(dados);
        initComponents();

        tablePacientes.setModel(tabela);

    }
                       
    private void initComponents() {

        labelRegistroDeEntrada = new javax.swing.JLabel();
        buttonVoltar = new javax.swing.JButton();
        fieldBusca = new javax.swing.JTextField();
        buttonBusca = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePacientes = new javax.swing.JTable();
        labelSituacao = new javax.swing.JLabel();
        dropboxSituacao = new javax.swing.JComboBox<String>();
        buttonNovaEntrada = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        

        labelRegistroDeEntrada.setText("Registro de Entrada");

        buttonVoltar.setBackground(new java.awt.Color(153, 153, 153));
        buttonVoltar.setText("Voltar");
        buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoltarActionPerformed(evt);
            }
        });

        buttonBusca.setBackground(new java.awt.Color(153, 153, 153));
        buttonBusca.setText("Buscar");

        tablePacientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablePacientes);

        labelSituacao.setText("Situação do Paciente:");

        dropboxSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o grau de dor", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        buttonNovaEntrada.setBackground(new java.awt.Color(153, 153, 153));
        buttonNovaEntrada.setText("Nova Entrada");
        buttonNovaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNovaEntradaActionPerformed(evt);
            }
        });
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSituacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dropboxSituacao, 0, 144, Short.MAX_VALUE)
                                .addGap(108, 108, 108))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(fieldBusca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBusca))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonVoltar)
                        .addGap(93, 93, 93)
                        .addComponent(labelRegistroDeEntrada)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonNovaEntrada)))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRegistroDeEntrada)
                    .addComponent(buttonVoltar))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSituacao)
                    .addComponent(dropboxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(buttonNovaEntrada)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>         
    
    
    private void buttonNovaEntradaActionPerformed(java.awt.event.ActionEvent evt) {   
        if(tablePacientes.getSelectedRow() != -1){
            Paciente paciente = tabela.selecionaItem(tablePacientes.getSelectedRow());
            Entrada entrada = new Entrada(dropboxSituacao.getSelectedItem().toString(), paciente);
            dados.getaBancoEntradas().add(entrada);

            new TelaPrincipalAtendente(dados, atendente).setVisible(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Confira se selecionou um Paciente e preencheu corretamente o grau de dor!");
        }                                              
    }

    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {    
        new TelaPrincipalAtendente(dados, atendente).setVisible(true);  
        this.dispose();                                            
        
    }


    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonBusca;
    private javax.swing.JButton buttonNovaEntrada;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JComboBox<String> dropboxSituacao;
    private javax.swing.JTextField fieldBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelRegistroDeEntrada;
    private javax.swing.JLabel labelSituacao;
    private javax.swing.JTable tablePacientes;
    // End of variables declaration    
    
    private ControleDados dados;
    private Atendente atendente;
    private TabelaPaciente tabela;
}