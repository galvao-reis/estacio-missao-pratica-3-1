package cadastropoo;

import model.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class CadastroPOO {

    private static final PessoaFisicaRepo pessoasFisicas = new PessoaFisicaRepo();
    private static final PessoaJuridicaRepo pessoasJuridicas = new PessoaJuridicaRepo();
    private static final Scanner input = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        while (true){
        System.out.println(
                """
                =================================================================
                Por favor, escolha um numero para continuar (Apenas números):
                        1 -> Incluir Pessoa.
                        2 -> Alterar Pessoa.
                        3 -> Excluir Pessoa.
                        4 -> Buscar pelo ID.
                        5 -> Exibir Todos.
                        6 -> Persistir Dados.
                        7 -> Recuperar Dados.
                        0 -> Finalizar Programa.
                ================================================================="""
        );
        int opcao = -1;
        String resposta = input.nextLine();
        try{
            opcao = Integer.parseInt(resposta);
        }
        catch(NumberFormatException exception){
            //System.out.println(exception.toString());
            System.out.println("As opções apenas aceitam números.");
        }
        switch(opcao){
            case 1->{
                cadastroPessoa();
            }
            case 2->{
                alterarCadastro();
            }
            case 3->{
                excluirCadastro();
            }
            case 4->{
                procurarID();
            }
            case 5->{
                exibirTodos();
            }
            case 6->{
                salvarArquivo();
            }
            case 7->{
                recuperarArquivo();
            }
            case 0->{
                System.exit(0);
            }
        }
        }
    }
    
    private static int verificarTipoPessoa(){
        int opcao = -1;
        while (opcao < 0 | opcao > 2){
            System.out.println("""
                               Qual o tipo? (Apenas Numeros)
                               1 - Fisica
                               2 - Juridica
                               0 - Retornar""");
            String resposta = input.nextLine();
            try{
                opcao = Integer.parseInt(resposta);
                if (opcao < 0){
                    System.out.println( "Por favor insira um numero posivito." );
                }
            }
            catch(NumberFormatException exception){
                System.out.println("A resposta nao e um numero, tente novamente");
            }
        }
        return opcao;
    }
    
    private static void cadastroPessoa(){
        //opcao só pode ser 1,2 ou 0;
        int opcao = verificarTipoPessoa();
        switch (opcao){
            case 1 -> {
                cadastroPessoaFisica();
            }
            case 2 -> {
                cadastroPessoaJuridica();
            }
            default ->{
                System.out.println("Esta opcao não é reconhecida. Retornando ao menu.");
            }
        }
    }
    
    private static int pedirId(){
        String resposta;
        int id = -1;
        while (id < 0){
            System.out.println("Insira o ID desejado: (Apenas Numeros)");
            System.out.println("Para retornar digite 0");
            resposta = input.nextLine();
            try {
                id = (Integer.parseInt(resposta));
            }
            catch(NumberFormatException exception){
                System.out.println("Este ID nao e valido!");
            }
        }
       return id;
    }
    
    private static String pedirNomePessoa(){
        System.out.println("Insira o nome da pessoa:");
        return input.nextLine();
    }
    
    private static String pedirNomeEmpresa(){
        System.out.println("Insira o nome da empresa:");
        return input.nextLine();
    }
    
    private static String pedirCpf(){
        System.out.println("Insira o CPF da pessoa:");
        return input.nextLine();
    }
    
    private static String pedirCnpj(){
        System.out.println("Insira o CNPJ da empresa:");
        return input.nextLine();
            
    }
    private static int pedirIdade(){
        int idade = -1;
        while (idade < 0){
            System.out.println("Insira a idade da pessoa: (Apenas Numeros)");
            String resposta = input.nextLine();
            try {
                idade = (Integer.parseInt(resposta));
                if ( idade < 0 ){
                    System.out.println("Idade deve ser um numero positivo.");
                }
            }
            catch(NumberFormatException exception){
                System.out.println("A idade inserida não é um número! Tente novamente");
                cadastroPessoaFisica();
            }                
        }
        return idade;
    }
    
    private static String verificarInformacoes(){
        String resposta = "";
        
        while (!(resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("N"))){
            System.out.println("Estas informações estão corretas? (S/N)");
            resposta = input.nextLine();
            if (!(resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("N"))){
            System.out.println("A resposta deve ser S ou N, apenas.");
            }
        }
    return resposta;
    }
    
    private static void cadastroPessoaFisica(){
        PessoaFisica pFisica = new PessoaFisica(-1,"","",-1);
        boolean finished = false;
        while (!finished){
            int id = -1;
            while (id < 0){
                id = pedirId();
                if ( id == 0 ){
                    return;
                }
                if (verificarIdExiste(id)){
                    id = -1;
                    System.out.println("Esse ID ja existe, insira um novo numero.");
                }
                else{
                    pFisica.setId(id);
                }
            }
            
            pFisica.setNome(pedirNomePessoa());
            
            pFisica.setCpf(pedirCpf());
            
            pFisica.setIdade(pedirIdade());
            
            System.out.println("---------------------");
            pFisica.exibir();
            System.out.println("---------------------");
            
            String resposta = verificarInformacoes();
            if (resposta.equalsIgnoreCase("S")){
                finished = true;
                pessoasFisicas.inserir(pFisica);
            }
            else if(resposta.equalsIgnoreCase("N")){
                System.out.println("Tente novamente.");
            }
        }
    }
    private static void cadastroPessoaJuridica(){
        PessoaJuridica pJuridica = new PessoaJuridica(-1,"","");
        boolean finished = false;
        while (!finished){
            int id = -1;
            while (id < 0){
                id = pedirId();
                if ( id == 0 ){
                    return;
                }
                if (verificarIdExiste(id)){
                    id = -1;
                    System.out.println("Esse ID ja existe, insira um novo numero.");
                }
                else{
                    pJuridica.setId(id);
                }
            }
            
            
            pJuridica.setNome(pedirNomeEmpresa());
            
            pJuridica.setCnpj(pedirCnpj());
            
            System.out.println("---------------------");
            pJuridica.exibir();
            System.out.println("---------------------");
            String resposta = verificarInformacoes();
            
            if (resposta.equalsIgnoreCase("S")){
                finished = true;
                pessoasJuridicas.inserir(pJuridica);
            }
            else if(resposta.equalsIgnoreCase("N")){
                System.out.println("Tente novamente.");
            }
            
        }
        
    }
    
    private static void alterarCadastro(){
        int opcao = verificarTipoPessoa();
        switch (opcao){
            case 1 -> {
                alterarPessoaFisica();
            }
            case 2 -> {
                alterarPessoaJuridica();
            }
            default ->{
                System.out.println("Retornando ao menu.");
            }
        }
    }
    
    private static void alterarPessoaFisica(){
        int id = -1;
        while (id <0 | !verificarIdFisicoExiste(id)){
            System.out.println( "Insira o ID da pessoa que deseja alterar:" );
            String resposta = input.nextLine();
            try{
                id = Integer.parseInt(resposta);
            }
            catch(NumberFormatException exception){
                System.out.println("O ID deve ser um numero. Tente novamente.");
            }
            if (!verificarIdFisicoExiste(id)){
                System.out.println("O ID inserido não existe ou não é de uma Pessoa Fisica.");
            }
        }
        System.out.println("Os dados atuais para este id são:");
        PessoaFisica pFisica = pessoasFisicas.obter(id);
        System.out.println("-----------------");
        pFisica.exibir();
        System.out.println("-----------------");
        System.out.println("Entre com os dados novos:");
        pFisica.setNome(pedirNomePessoa());
        pFisica.setCpf(pedirCpf());
        pFisica.setIdade(pedirIdade());
        
        System.out.println("As novas informações são:");       
        System.out.println("---------------------");
        pFisica.exibir();
        System.out.println("---------------------");
            
        String resposta = verificarInformacoes();
        if (resposta.equalsIgnoreCase("S")){
            pessoasFisicas.alterar(pFisica);
        }
        else if (resposta.equalsIgnoreCase("N")){
            System.out.println("Alteração cancelada. Retornando ao menu.");
        }
        
        
        
    }
    private static void alterarPessoaJuridica(){
        int id = -1;
        while (id <0 | !verificarIdJuridicoExiste(id)){
            System.out.println( "Insira o ID da empresa que deseja alterar:" );
            String resposta = input.nextLine();
            try{
                id = Integer.parseInt(resposta);
            }
            catch(NumberFormatException exception){
                System.out.println("O ID deve ser um numero. Tente novamente.");
            }
            if (!verificarIdJuridicoExiste(id)){
                System.out.println("O ID inserido não existe ou não é de uma Pessoa Fisica.");
            }
        }
        System.out.println("Os dados atuais para este id são:");
        PessoaJuridica pJuridica = pessoasJuridicas.obter(id);
        System.out.println("-----------------");
        pJuridica.exibir();
        System.out.println("-----------------");
        System.out.println("Entre com os dados novos:");
        pJuridica.setNome(pedirNomeEmpresa());
        pJuridica.setCnpj(pedirCnpj());
        
        System.out.println("As novas informações são:");
        System.out.println("-----------------");
        pJuridica.exibir();
        System.out.println("-----------------");
        
        String resposta = verificarInformacoes();
        if (resposta.equalsIgnoreCase("S")){
            pessoasJuridicas.alterar(pJuridica);
        }
        else if (resposta.equalsIgnoreCase("N")){
            System.out.println("Alteração cancelada. Retornando ao menu.");
        }
    }
    
    private static void excluirCadastro(){
        int opcao = verificarTipoPessoa();
        switch (opcao){
            case 1 -> {
                excluirCadastroFisico();
            }
            case 2 -> {
                excluirCadastroJuridico();
            }
            default ->{
                System.out.println("Retornando ao menu.");
            }

        }
    }
    private static void excluirCadastroFisico(){
        int id = pedirId();
        if (verificarIdFisicoExiste(id)){
            System.out.println("Excluindo Pessoa Física de ID: "+ id + ": " + pessoasFisicas.obter(id).getNome());
            pessoasFisicas.excluir(id);            
        }
        else {
            System.out.println("O ID fornecido não existe ou não é de uma Pessoa Física. Retornando ao menu.");
        }
    }
    private static void excluirCadastroJuridico(){
        int id = pedirId();
        if (verificarIdJuridicoExiste(id)){
            System.out.println("Excluindo Pessoa Juridica de ID: "+ id + ": " + pessoasJuridicas.obter(id).getNome());
            pessoasJuridicas.excluir(id);
        }
        else{
            System.out.println("O ID fornecido não existe ou não é de uma Pessoa Jurídica. Retornando ao menu.");
        }
    }
    static void procurarID(){
        int opcao = verificarTipoPessoa();
        if (opcao == 0) {
            return;
        }
        int id = pedirId();
        
        
        switch (opcao){
            case 1 ->{
                if (verificarIdFisicoExiste(id)){
                    pessoasFisicas.obter(id).exibir();
                    return;
                }
            }
            case 2 ->{
                if (verificarIdJuridicoExiste(id)){
                    pessoasJuridicas.obter(id).exibir();
                    return;
                }
            }
        }
        System.out.println( "O ID fornecido não foi encontrado." );
        }
    
    static void exibirTodos(){
        int tipoPessoa = verificarTipoPessoa();
        switch(tipoPessoa){
            case 1 -> {
                System.out.println("As Pessoas Fisicas registradas são: \n");
                pessoasFisicas.obterTodos().stream().forEach(p -> p.exibir());
            }
            case 2 -> {
                System.out.println("As Pessoas Juridicas registradas são: \n");
                pessoasJuridicas.obterTodos().stream().forEach(p -> p.exibir());
            }
            default -> {
                return;
            }
        }
    }
    
    static void salvarArquivo(){
        System.out.println("Digite o prefixo do nome para o arquivo:");
        String resposta = input.nextLine();
        try{
            String name = resposta + ".fisica.bin";
            pessoasFisicas.persistir(name);
            System.out.println("Dados das pessoas fisicas salvos com sucesso no arquivo " + name +".");
        }
        catch(IOException exception){
            System.out.println("Não foi possível salvar os dados das pessoas fisicas.\n Erro: " + exception.toString());
        }
        try{
            String name = resposta + ".juridica.bin";
            pessoasJuridicas.persistir(name);
            System.out.println("Dados das pessoas juridicas salvos com sucesso no arquivo "+ name +".");
        }
        catch(IOException exception){
            System.out.println("Não foi possível salvar os dados pessoas juridicas.\n Erro: " + exception.toString());
        }
    }
    
    static void recuperarArquivo(){
        System.out.println("Digite o prefixo do arquivo:");
        String resposta = input.nextLine();
        try{
            String name = resposta + ".fisica.bin";
            pessoasFisicas.recuperar(name);
            System.out.println("Dados das pessoas fisicas recuperados com sucesso do arquivo "+ name +".");
        }
        catch(IOException | ClassNotFoundException exception){
            System.out.println("Houve um erro tentando recuperar as Pessoas Fisicas. Erro: "+ exception.toString());
        }
        try{
            String name = resposta + ".juridica.bin";
            pessoasJuridicas.recuperar(name);
            System.out.println("Dados das pessoas juridicas recuperados com sucesso do arquivo " + name +".");
        }
        catch(IOException | ClassNotFoundException exception){
            System.out.println("Houve um erro tentando recuperar as Pessoas Juridicas. Erro: "+ exception.toString());
        }
    }
    
    
    static boolean verificarIdExiste(int id){
        return  verificarIdFisicoExiste(id) | verificarIdJuridicoExiste(id);
    }
    
    private static boolean verificarIdFisicoExiste(int id){
        ArrayList<PessoaFisica> pFisicas = pessoasFisicas.obterTodos();
        return ( pFisicas.stream().anyMatch( p-> p.getId() == id ));
    }
    
    private static boolean verificarIdJuridicoExiste(int id){
        ArrayList<PessoaJuridica> pJuridicas = pessoasJuridicas.obterTodos();
        return (pJuridicas.stream().anyMatch( p-> p.getId() == id) );
    }
    
}