package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo{
    
    private ArrayList<PessoaJuridica> pessoasJuridicas;
    
    public PessoaJuridicaRepo(){
        this.pessoasJuridicas = new ArrayList<PessoaJuridica>();
    }
    
    public void inserir(PessoaJuridica pessoa){
        this.pessoasJuridicas.add(pessoa);
    }
    
    public void alterar(PessoaJuridica pessoa){
        this.excluir(pessoa.getId());
        this.inserir(pessoa);
    }
    
    public void excluir( int id ){
        this.pessoasJuridicas.removeIf( pessoa -> pessoa.getId() == id );
    }
    
    public PessoaJuridica obter( int id ){
        for (PessoaJuridica pessoa : pessoasJuridicas){
            if (pessoa.getId() == id){
                return pessoa;
            }
        }
        return null;
    }
    
    public ArrayList<PessoaJuridica> obterToddos(){
        return this.pessoasJuridicas;
    }
    
    public void persistir( String nomeArquivo ) throws IOException{
        FileOutputStream fos = new FileOutputStream(nomeArquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.pessoasJuridicas);
    }
    
    public void recuperar( String nomeArquivo) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeArquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.pessoasJuridicas = (ArrayList<PessoaJuridica>) ois.readObject();
        
    }
        
}
