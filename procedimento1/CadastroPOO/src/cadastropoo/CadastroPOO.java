package cadastropoo;

import model.*;
import java.io.*;

public class CadastroPOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();

            repo1.inserir(new PessoaFisica(1,"Ana","11111111111",25));
            repo1.inserir(new PessoaFisica(2,"Carlos","22222222222",52));
            try{
                repo1.persistir( "RepositorioPessoasFisicas" );
            }
            catch(IOException exception){
                System.out.println(exception.toString());
            }
            
            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            try{
                repo2.recuperar( "RepositorioPessoasFisicas" );
            }
            catch(IOException | ClassNotFoundException exception){
                System.out.println(exception.toString());
            }
            
            for (PessoaFisica pessoa : repo2.obterTodos()){
                pessoa.exibir();
            }
            
            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
            repo3.inserir(new PessoaJuridica(3,"XPTO Sales","33333333333333"));
            repo3.inserir(new PessoaJuridica(4,"XPTO Solutions","44444444444444444"));
            
            try{
                repo3.persistir( "RepositorioPessoasJuridicas" );
            }
            catch(IOException exception){
                System.out.println(exception.toString());
            }
            
            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            try{
                repo4.recuperar( "RepositorioPessoasJuridicas" );
            }
            catch( IOException | ClassNotFoundException exception){
                System.out.println(exception.toString());
            }
            
            for (PessoaJuridica pessoa : repo4.obterToddos()){
                pessoa.exibir();
            }
        }
    }