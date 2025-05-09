package control;

// import java.text.SimpleDateFormat;
// import java.util.Date;

import model.*;
import view.*;

public class testeModel {
  public static void main(String[] args){

    ControleDados banco = new ControleDados();

    Administrador adm = new Administrador("Gabriella Monteiro do Nscimento","3.268.791","049.497.471-03",'F',"01/12/1997","3798-4569","gabriellanascimento@gmail.com","Arniqueiras","Condomínio Vista Nobre","roberto","1212");
    adm.setIdAdmin("809213");
    banco.getBancoAdministradores().add(adm);

    Paciente paciente = new Paciente("Elaine Venson","3.890.657","004.625.879-00",'F',"25/03/1996","3478-2158","elainevenson@gmail.com","Brasília-DF","Bairro do Limoeiro","Nenhuma");
    Paciente paciente1 = new Paciente("Rejane Maria da Costa Figueiredo","3.782.487","047.150.304-01",'F',"27/02/1996","3578-2218","rejanefigueiredo@gmail.com","Brasília-DF","Bairro do Maracujá","Nenhuma");
    Paciente paciente2 = new Paciente("Luiz Carlos Miyadaira Ribeiro","3.602.987","045.250.520-01",'M',"05/07/1997","3547-2547","luizmiyadaira@gmail.com","Brasília-DF","Bairro do Mangueiral","Nenhuma");
    Paciente paciente3 = new Paciente("Pedro Vitor Augusto de Jesus","5.727.924","046.257.459-02",'M',"27/09/2002","3482-2547","pedrojesus@gmail.com","Gama","Quadra 45","Intolerância a lactose");
    Paciente paciente4 = new Paciente("Lucas Ramon Alves de Oliveira","7.583.977","047.358.569-01",'M',"05/01/2001","3759-2518","lucasalves@gmail.com","Valparaíso-GO","Avenida das Cachoeiras","Dermatite");
    Paciente paciente5 = new Paciente("Mateus Fernando Alves de Oliveira","3.352.677","047.345.569-02",'M',"05/01/2001","3759-2518","mateusalves@gmail.com","Valparaíso-GO","Avenida das Cachoeiras","Ansiedade");
    Paciente paciente6 = new Paciente("Bruno Seiji Kishibe","3.875.246","048.759.149-04",'F',"12/10/2001","3549-7890","brunokhishibe@gmail.com","Águas Claras","Avenida das Castanheiras","Asma");
    Paciente paciente7 = new Paciente("Maurício Marcos Costa de Souza","3.759.249","045.579.329-01",'M',"15/02/1995","3578-1897","mauriciosouza@gmail.com","Park Sul","Quadra 05","Alergia a Camarão");
    Paciente paciente8 = new Paciente("Ramon Castro dos Santos","3.359.789","046.157.347-02",'M',"10/02/2000","3567-3818","ramonsantos@gmail.com","Guará 2","Quadra 30","Rinite");
    Paciente paciente9 = new Paciente("Caio César Ribeiro","3.467.977","046.784.321-03",'M',"10/07/2006","3387-1249","caioribeiro@gmail.com","Taguatinga Norte","QNL 2","Diabetes tipo 1");
    Paciente paciente10 = new Paciente("Beatriz Soares da Silva","3.774.957","049.220.789-05",'F',"24/08/2010","3597-249","beatrizsilva@gmail.com","Guará 1","QI 7","Pressão alta");
    Paciente paciente11 = new Paciente("Gabriella Monteiro do Nscimento","3.268.791","049.497.471-03",'F',"01/12/1997","3798-4569","gabriellanascimento@gmail.com","Arniqueiras","Condomínio Vista Nobre","Rinite");
    Paciente paciente12 = new Paciente("Ana Luísa da Silva","3.249.700","045.123.321-05",'F',"10/11/2000","3577-1249","analuisasilva@gmail.com","Núcleo Bandeirante","Avenida Central","Nenhuma");
    Paciente paciente13 = new Paciente("Ana Júlia da Silva","3.489.746","047.357.467-04",'F',"10/09/2009","3577-1249","anajuliasilva@gmail.com","Núcleo Bandeirante","Avenida Central","Nenhuma");
    Paciente paciente14 = new Paciente("Vanessa Araújo","3.359.759","048.249.467-03",'F',"10/07/2006","3387-1249","vanessaaraujo@gmail.com","Taguatinga Norte","QNL 7","Sinusite");
    Paciente paciente15 = new Paciente("Phillippi Augusto da Silva","3.794.987","044.746.367-00",'M',"29/05/2000","3979-3259","phillippisilva@gmail.com","Taguatinga Sul","QSD 2","Pressão baixa");
    Paciente paciente16 = new Paciente("Fernando da Silva Júnior","3.497.746","043.349.137-00",'M',"15/04/1998","3792-3597","fernandojunior@gmail.com","Taguatinga Centro","Quadra C 11","Anemia falciforme");
    Paciente paciente17 = new Paciente("Clarisse Manuella de Oliveira Costa","3.789.704","047.644.951-01",'F',"07/07/2007","3497-3579","clarissecosta@gmail.com","Taguatinga Norte","QNL 2","Síndrome de Down");
    Paciente paciente18 = new Paciente("Maria Fernanda Ribeiro de Oliveira","3.579.426","044.574.654-03",'F',"08/09/1999","3367-1579","mariafernandaoliveira@gmail.com","Taguatinga Norte","QNL 2","Diabetes tipo 2");
    Paciente paciente19 = new Paciente("Mariana de Oliveira Gonçalves","3.759.789","041.774.606-03",'F',"07/04/2005","3388-9961","marianagonçalves@gmail.com","Taguatinga Norte","QNL 2","Fibrose cística");
    

    banco.getBancoPacientes().add(paciente);
    banco.getBancoPacientes().add(paciente1);
    banco.getBancoPacientes().add(paciente2);
    banco.getBancoPacientes().add(paciente3);
    banco.getBancoPacientes().add(paciente4);
    banco.getBancoPacientes().add(paciente5);
    banco.getBancoPacientes().add(paciente6);
    banco.getBancoPacientes().add(paciente7);
    banco.getBancoPacientes().add(paciente8);
    banco.getBancoPacientes().add(paciente9);
    banco.getBancoPacientes().add(paciente10);
    banco.getBancoPacientes().add(paciente11);
    banco.getBancoPacientes().add(paciente12);
    banco.getBancoPacientes().add(paciente13);
    banco.getBancoPacientes().add(paciente14);
    banco.getBancoPacientes().add(paciente15);
    banco.getBancoPacientes().add(paciente16);
    banco.getBancoPacientes().add(paciente17);
    banco.getBancoPacientes().add(paciente18);
    banco.getBancoPacientes().add(paciente19);

    //Atendente atendente = new Atendente(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha);
    Atendente atendente = new Atendente("Lorrane Lorena de Oliveira Ribeiro", "3.567.324","047.657.459.00",'F',"10/11/1995","(61)99185-7674", "lorraneLOribeiro@gmail.com","Gama","Quadra 12","lorrane" ,"1011");
    atendente.setIdAtendente("83asja3");
    Atendente atendente1 = new Atendente("Laryssa Brito Tavares", "3.559.123","048.657.154.00",'F',"17/08/1997","(61)98759-7245", "laryssaBTavares@gmail.com","Guará 1","Quadra 7","laryssa" ,"1708");
    atendente1.setIdAtendente("231g12j3");
    Atendente atendente2 = new Atendente("Beatriz Moreira do Nascimento", "3.466.324","047.032.477.01",'F',"08/11/1995","(61)97592-7677", "beatrizMNascimento@gmail.com","Gama","Quadra 07","beatriz" ,"0811");
    atendente2.setIdAtendente("12d12");
    Atendente atendente3 = new Atendente("Emanuel Augusto dos Anjos", "3.570.897","047.765.657.02",'M',"10/11/1995","(61)99745-7678", "emanuelAAnjos@gmail.com","Guará 2","Quadra 15","emanuel" ,"1011");
    atendente3.setIdAtendente("s67rvr");
    Atendente atendente4 = new Atendente("Jorge Evangelista Santana", "3.597.367","047.432.957.00",'M',"05/09/1992","(61)98745-3567", "jorgeESantana@gmail.com","Valparaíso-GO","Avenida das Castanheiras","jorge" ,"0509");
    atendente4.setIdAtendente("03934x3");

    banco.getaBancoAtendente().add(atendente);
    banco.getaBancoAtendente().add(atendente1);
    banco.getaBancoAtendente().add(atendente2);
    banco.getaBancoAtendente().add(atendente3);
    banco.getaBancoAtendente().add(atendente4);


    //Medico medico = new Medico(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha, String crm, String especialidade);
    Medico medico = new Medico("Maria Luisa Alves","3.579.321", "047.321.499-00", 'F', "10/12/2001", "(61)98745-3216", "maria_luisa_rodrigues@gmail.com", "Park Way", "Quadra 1", "malu", "1012", "DF101201","Clíco Geral");
    Medico medico1 = new Medico("Gabriela Silva Alves","3.789.123", "045.552.489-01", 'F', "27/11/2001", "(61)98677-3574", "gabriela_alves@gmail.com", "Riacho Fundo 2", "Quadra 7", "gabi", "2711", "DF271101","Clíco Geral");
    Medico medico2 = new Medico("Marina Márcia Costa de Souza","3.354.659", "047.189.498-01", 'F', "13/11/2000", "(61)99567-1918", "marina_souza@gmail.com", "Guará 2", "Quadra 28", "mari", "1311", "DF131100","Clíco Geral");
    Medico medico3 = new Medico("Júlia Stefanie Santos Mendonça","3.326.759", "046.332.579-02", 'F', "05/08/2000", "(61)98381-5974", "julia_mendonca@gmail.com", "Riacho Fundo 1", "Quadra 6", "juli", "0508", "DF050800","Pedriátra");
    Medico medico4 = new Medico("Marcos Aurélio Araújo","3.469.332", "048.155.467-00", 'M', "10/02/1995", "(61)99661-3554", "marcos_araujo@gmail.com", "Park Way", "Gama 3", "marc", "1002", "DF100295","Gastroenterologista");
    Medico medico5 = new Medico("Maria Catarina Miranda Barcelos","3.023.123", "045.552.489-01", 'F', "27/08/1998", "(61)99569-3257", "maria_catarina_barcelos@gmail.com", "Gama", "Quadra 7", "mari", "2708", "DF271898","Cardiologista");
    Medico medico6 = new Medico("Gabriel Sucupira de Oliveira","3.708.367", "048.367.489-02", 'M', "26/09/1999", "(61)98845-2376", "gabriel_oliveira@gmail.com", "Park Way", "Quadra 7", "gabr", "2609", "DF260999","Hematologista");
    Medico medico7 = new Medico("Rafael dos Santos Nascimento","3.788.897", "049.572.400-01", 'M', "22/10/1994", "(61)98569-1374", "rafael_nascimento@gmail.com", "Guará 2", "Quadra 17", "rafa", "2210", "DF221094","Pneumologista");
    Medico medico8 = new Medico("Pedro Henrique de Sousa Silva","3.770.321", "050.232.489-01", 'M', "26/11/1992", "(61)98778-3234", "pedro_silva@gmail.com", "Guará 2", "Quadra 21", "pedr", "2611", "DF261192","Dermatologista");
    Medico medico9 = new Medico("Augusto de Souza Pereira","3.776.123", "045.567.499-00", 'M', "06/03/1990", "(61)99597-3211", "augusto_pereira@gmail.com", "Guará 1", "Quadra 7", "augu", "20603", "DF060300","Mastologista");
      
    banco.getBancoMedicos().add(medico);
    banco.getBancoMedicos().add(medico1);
    banco.getBancoMedicos().add(medico2);
    banco.getBancoMedicos().add(medico3);
    banco.getBancoMedicos().add(medico4);
    banco.getBancoMedicos().add(medico5);
    banco.getBancoMedicos().add(medico6);
    banco.getBancoMedicos().add(medico7);
    banco.getBancoMedicos().add(medico8);
    banco.getBancoMedicos().add(medico9);



    //Marcada consuMarcada = new Marcada(String codigo, Paciente paciente, Medico medico);
    Marcada consuMarcada = new Marcada("0404", paciente1, medico8);
    consuMarcada.setDescricaoMedica("Tudo certo, Regiane Esta otima! ");
    consuMarcada.setData("01/02/2023");
    consuMarcada.setHorario("15:00");
    paciente1.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    

  // medico9.getListConsultasPendentes().add;

    Marcada consuMarcada1 = new Marcada("0606", paciente6, medico7);
    paciente6.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada1.setData("06/02/2023");
    consuMarcada1.setHorario("08:00");
//medico9.getListConsultasPendentes();



    Marcada consuMarcada2 = new Marcada("1717", paciente17, medico);
    paciente17.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("10/02/2023");
    consuMarcada.setHorario("10:30");
    
//medico9.getListConsultasPendentes();

    Marcada consuMarcada3 = new Marcada("0303", paciente2, medico);
    paciente2.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("07/02/2023");
    consuMarcada.setHorario("07:00");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada4 = new Marcada("0000", paciente, medico1);
    paciente.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("20/02/2023");
    consuMarcada.setHorario("08:30");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada5 = new Marcada("1717", paciente19, medico7);
    paciente17.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("03/02/2023");
    consuMarcada.setHorario("13:00");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada6 = new Marcada("1010", paciente10, medico5);
    paciente10.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("13/02/2023");
    consuMarcada.setHorario("11:00");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada7 = new Marcada("1313", paciente13, medico2);
    paciente13.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("04/02/2023");
    consuMarcada.setHorario("10:45");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada8 = new Marcada("0101", paciente1, medico9);
    paciente1.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("15/02/2023");
    consuMarcada.setHorario("10:00");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada9 = new Marcada("1616", paciente16, medico);
    paciente16.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("22/02/2023");
    consuMarcada.setHorario("11:00");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada11 = new Marcada("0202", paciente2, medico4);
    paciente2.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("03/02/2023");
    consuMarcada.setHorario("13:00");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada13 = new Marcada("0202", paciente2, medico7);
    paciente2.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("03/02/2023");
    consuMarcada.setHorario("14:00");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada14 = new Marcada("0202", paciente2, medico8);
    paciente2.getProntuario().getHistoricoConsultasMarcadas().add(consuMarcada);
    consuMarcada.setData("28/02/2023");
    consuMarcada.setHorario("13:00");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada10 = new Marcada("0202", paciente2, medico);
    //paciente2.getProntuario().getHistoricoExame().add(Hemograma completo);'INCLUIR EXAME'
    consuMarcada.setData("04/02/2023");
    consuMarcada.setHorario("07:00");
//medico9.getListConsultasPendentes();

    Marcada consuMarcada12 = new Marcada("0202", paciente2, medico5);
    //paciente2.getProntuario().getHistoricoExame().add(Eletrocardiograma);'INCLUIR EXAME'
    consuMarcada.setData("02/02/2023");
    consuMarcada.setHorario("13:30");
//medico9.getListConsultasPendentes();

    banco.getBancoConsultaMarcadas().add(consuMarcada); 
    banco.getBancoConsultaMarcadas().add(consuMarcada1);
    banco.getBancoConsultaMarcadas().add(consuMarcada2); 
    banco.getBancoConsultaMarcadas().add(consuMarcada3);
    banco.getBancoConsultaMarcadas().add(consuMarcada4); 
    banco.getBancoConsultaMarcadas().add(consuMarcada5);
    banco.getBancoConsultaMarcadas().add(consuMarcada6); 
    banco.getBancoConsultaMarcadas().add(consuMarcada10);
    banco.getBancoConsultaMarcadas().add(consuMarcada11); 
    banco.getBancoConsultaMarcadas().add(consuMarcada13);
    banco.getBancoConsultaMarcadas().add(consuMarcada12); 
    banco.getBancoConsultaMarcadas().add(consuMarcada14);



    medico.getListConsultasPendentes().add(consuMarcada2);
    medico.getListConsultasPendentes().add(consuMarcada3);
    medico.getListConsultasPendentes().add(consuMarcada10);
    medico.getListConsultasPendentes().add(consuMarcada9);

    medico7.getListConsultasPendentes().add(consuMarcada13);
    medico7.getListConsultasPendentes().add(consuMarcada5);

    //Enfermeiro enfermeiro = new Enfermeiro(String nome, String rg, String cpf, char sexo, String dataNascimento, String telefone, String email, String cidade, String endereco, String usuario, String senha, String corem);
    Enfermeiro enfermeiro = new Enfermeiro("Bruna Ferreira da Silva", "3.456.789", "056.438.504-50", 'F', "19/11/1998", "(61)98452-3568", "brunaFereirra@gmail.com", "Gama", "Quadra 07", "bruna", "1911", "DF1911");
    Enfermeiro enfermeiro1 = new Enfermeiro("Gabriel Morais Mendonça", "3.643.785", "021.389.506-31", 'M', "11/02/1973", "(61)99368-7890", "gabrielMorais@gmail.com", "Asa Sul", "Quadra CLS 103 Bloco A", "gggg", "1102", "1102");
    Enfermeiro enfermeiro2 = new Enfermeiro("Maria Ferreira Gomes", "3.567.106", "057.436.508-56", 'F', "15/08/1994", "(61)98745-3567", "mariaFereira@gmail.com", "Asa Sul", "Quadra CLS 103 Bloco A", "mmmmm", "1508", "1508");

    banco.getBancoEnfermeiros().add(enfermeiro);
    banco.getBancoEnfermeiros().add(enfermeiro1);
    banco.getBancoEnfermeiros().add(enfermeiro2);


    //Triagem triagem = new Triagem(entrada, enfermeiro);
    //Entrada entrada = new Entrada(Calendar dataEntrada, String situacaoPaciente, Paciente paciente);
    Entrada entrada = new Entrada("9", paciente5);
    entrada.setDataEntrada("02:23");

    Triagem triagem = new Triagem(entrada, enfermeiro);
    banco.getBancoTriagem().add(triagem);
    banco.getaBancoEntradas().add(entrada);

    Entrada entrada1 = new Entrada("8", paciente7);
    entrada1.setDataEntrada("02:27");

    Triagem triagem1 = new Triagem(entrada1, enfermeiro1);
    banco.getBancoTriagem().add(triagem1);
    banco.getaBancoEntradas().add(entrada1);
    
    Entrada entrada2 = new Entrada("2", paciente8);
    entrada.setDataEntrada("02:36");

    Triagem triagem2 = new Triagem(entrada2, enfermeiro2);
    banco.getBancoTriagem().add(triagem2);
    banco.getaBancoEntradas().add(entrada2);
    
    Entrada entrada3 = new Entrada("5", paciente9);
    entrada.setDataEntrada("02:42");
    Triagem triagem3 = new Triagem(entrada3, enfermeiro);
    banco.getBancoTriagem().add(triagem3);
    banco.getaBancoEntradas().add(entrada3);

    
    Entrada entrada4 = new Entrada("2", paciente11);
    entrada.setDataEntrada("02:43");
    Triagem triagem4 = new Triagem(entrada4, enfermeiro1);
    banco.getBancoTriagem().add(triagem4);
    banco.getaBancoEntradas().add(entrada4);
    
    Entrada entrada5 = new Entrada("1", paciente12);
    entrada.setDataEntrada("02:59");
    Triagem triagem5 = new Triagem(entrada5, enfermeiro2);
    banco.getBancoTriagem().add(triagem5);
    banco.getaBancoEntradas().add(entrada5);

    Entrada entrada6 = new Entrada("1", paciente14);
    entrada.setDataEntrada("03:01");
    Triagem triagem6 = new Triagem(entrada6, enfermeiro);
    banco.getBancoTriagem().add(triagem6);
    banco.getaBancoEntradas().add(entrada6);

    Entrada entrada7 = new Entrada("7", paciente15);
    entrada.setDataEntrada("03:08");
    Triagem triagem7 = new Triagem(entrada7, enfermeiro1);
    banco.getBancoTriagem().add(triagem7);
    banco.getaBancoEntradas().add(entrada7);
    
    //Emergencia emergencia = new Emergencia(String codigo,Paciente paciente, String codigoChamada, Triagem triagem);
    Emergencia emergencia = new Emergencia("0001", paciente5, "1000", triagem);
    Emergencia emergencia1 = new Emergencia("0002", paciente7, "2000", triagem1);

    banco.getBancoConsultaEmergencias().add(emergencia);
    banco.getBancoConsultaEmergencias().add(emergencia1);
    
    Exame exame = new Exame("Ressonância magnética", "3221", paciente);
    exame.setData("14/01/2023");
    exame.setResultado("Tudo certo....A ressonância magnética do crânio é um dos exames mais modernos para a avaliação do encéfalo e o diagnóstico de diversas patologias que podem acometer essa região.");
    paciente.getProntuario().getHistoricoExame().add(exame);
    

    Exame exame1 = new Exame("Exame Urina", "8732", paciente2);
    exame1.setData("18/01/2023");
    exame1.setResultado("Os valores normais variam de 1005 a 1035. Urinas com densidade próximas de 1005 estão bem diluídas; próximas de 1035 estão muito concentradas, indicando desidratação.");
    paciente2.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame2 = new Exame("Emograma", "2321", paciente4);
    paciente4.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame3 = new Exame("Mamografia", "3422", paciente3);
    paciente3.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame4 = new Exame("Tomografia", "1201", paciente12);
    paciente12.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame5 = new Exame("Exame Fezes", "4232", paciente10);
    paciente10.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame6 = new Exame("Raio-x", "3421", paciente6);
    paciente6.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame7 = new Exame(" Radiografia", "1237", paciente7);
    paciente7.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame8 = new Exame("Tomografia", "1221", paciente);
    paciente.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame9 = new Exame("Exame Urina", "9878", paciente);
    paciente.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame10 = new Exame("Emograma", "7865", paciente4);
    paciente4.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame11= new Exame("Eletrocardiograma", "7633", paciente14);
    paciente14.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame12= new Exame("Tomografia", "2353", paciente14);
    paciente14.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame14 = new Exame("Exame Fezes", "325", paciente3);
    paciente3.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame13= new Exame("Raio-x", "2323", paciente13);
    paciente13.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);

    Exame exame15 = new Exame(" Eletrocardiograma", "2341", paciente2);
    paciente2.getProntuario().getHistoricoExame().add(exame);
    // exame.setData(null);


    exame.setData("04/02/2023");
    banco.getBancoExames().add(exame);
    exame.setData("15/03/2023");
    banco.getBancoExames().add(exame1);
    exame.setData("17/02/203");
    banco.getBancoExames().add(exame2);
    exame.setData("21/02/2023");
    banco.getBancoExames().add(exame3);
    exame.setData("29/02/2023");
    banco.getBancoExames().add(exame12);
    exame.setData("03/03/2023");
    banco.getBancoExames().add(exame12);
    exame.setData("15/03/2023");
    banco.getBancoExames().add(exame15);
    exame.setData("01/03/2023");
    banco.getBancoExames().add(exame3);
    exame.setData("16/03/2023");
    banco.getBancoExames().add(exame4);
    exame.setData("28/02/2023");
    banco.getBancoExames().add(exame5);
    

  
    new TelaLogin(banco).setVisible(true);


   
  }
}
