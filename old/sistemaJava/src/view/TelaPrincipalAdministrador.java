package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import control.ControleDados;
import model.Administrador;

public class TelaPrincipalAdministrador extends javax.swing.JFrame {

    public TelaPrincipalAdministrador(ControleDados dados, Administrador adm) {
        this.dados = dados;
        this.adm = adm;
        this.c = new Date();
        initComponents();
    }
                             
        private void initComponents() {
    
            labelNaocadastrado = new javax.swing.JLabel();
            labelCadastrado = new javax.swing.JLabel();
            buttonBusca = new javax.swing.JButton();
            fieldBusca = new javax.swing.JTextField();
            labelDataHora = new javax.swing.JLabel();
            labelIdAdm = new javax.swing.JLabel();
            labelAdm = new javax.swing.JLabel();
            buttonVoltar = new javax.swing.JButton();
            buttonNovoFuncionario = new javax.swing.JButton();
            buttonGerenciar = new javax.swing.JButton();

            
    
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    
            labelNaocadastrado.setText("Este funcionário não está cadastrado!");
            labelNaocadastrado.setVisible(false);
            labelCadastrado.setText("Este funcionário está cadastrado!");
            labelCadastrado.setVisible(false);


            buttonBusca.setBackground(new java.awt.Color(153, 153, 153));
            buttonBusca.setText("Buscar Funcionário");
            buttonBusca.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonBuscaActionPerformed(evt);
                }
            });
    
            buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonVoltarActionPerformed(evt);
                }
            });
    
            labelDataHora.setText(formatador.format(c));
    
            labelIdAdm.setText(adm.getIdAdmin());
    
            labelAdm.setText(adm.getNome());
    
            buttonVoltar.setBackground(new java.awt.Color(153, 153, 153));
            buttonVoltar.setText("voltar");
    
            buttonNovoFuncionario.setBackground(new java.awt.Color(153, 153, 153));
            buttonNovoFuncionario.setText("Novo Funcionário");
            buttonNovoFuncionario.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonNovoFuncionarioActionPerformed(evt);
                }
            });
    
            buttonGerenciar.setBackground(new java.awt.Color(153, 153, 153));
            buttonGerenciar.setText("Gerenciar Funcionarios");
            buttonGerenciar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonGerenciarActionPerformed(evt);
                }
            });
    
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(buttonVoltar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelAdm, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelDataHora, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelIdAdm, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelCadastrado)
                                        .addComponent(labelNaocadastrado)))
                                .addComponent(fieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonGerenciar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buttonBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(buttonNovoFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGap(29, 29, 29))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelAdm)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelIdAdm)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelDataHora)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(fieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonBusca))
                            .addGap(25, 25, 25)
                            .addComponent(buttonNovoFuncionario))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(buttonVoltar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelCadastrado)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(labelNaocadastrado)))))
                    .addGap(9, 9, 9)
                    .addComponent(buttonGerenciar)
                    .addGap(68, 68, 68))
            );
    
            pack();
        }// </editor-fold>                        
    
        private void buttonNovoFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {                                                      
            new TelaCadastra(dados, adm).setVisible(true);
            this.dispose();
        }                                                     
    
        private void buttonBuscaActionPerformed(java.awt.event.ActionEvent evt) {                                            
            for(int i = 0 ; i<= dados.getBancoPacientes().size(); i++){
                if(dados.getBancoPacientes().get(i).getCpf().equals(fieldBusca.getText())){
                    labelCadastrado.setVisible(true);

                }

            }
        } 
        
        private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                            
            new TelaLogin(dados).setVisible(true);
            this.dispose();
        }
          
        private void buttonGerenciarActionPerformed(java.awt.event.ActionEvent evt) {                                                
            new TelaControleFuncionario(dados, adm).setVisible(true);
            this.dispose();
        }                                               
    
        
    
        // Variables declaration - do not modify                     
        private javax.swing.JButton buttonBusca;
        private javax.swing.JButton buttonGerenciar;
        private javax.swing.JButton buttonNovoFuncionario;
        private javax.swing.JButton buttonVoltar;
        private javax.swing.JTextField fieldBusca;
        private javax.swing.JLabel labelAdm;
        private javax.swing.JLabel labelCadastrado;
        private javax.swing.JLabel labelDataHora;
        private javax.swing.JLabel labelIdAdm;
        private javax.swing.JLabel labelNaocadastrado;
        // End of variables declaration                   
   
    
    private ControleDados dados;
    private Administrador adm;
    private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    private Date c;
}