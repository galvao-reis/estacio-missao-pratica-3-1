package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo{
    private ArrayList<PessoaFisica> pessoasFisicas;
    
    public PessoaFisicaRepo(){
        this.pessoasFisicas = new ArrayList<PessoaFisica>();
    }
    
    public void inserir( PessoaFisica pessoa ){
        this.pessoasFisicas.add(pessoa);
    }
    
    public void alterar( PessoaFisica pessoa ){
        this.excluir(pessoa.getId());
        this.inserir(pessoa);
    }
    
    public void excluir( int id ){
        this.pessoasFisicas.removeIf( pessoa -> pessoa.getId() == id);
    }
    
    public PessoaFisica obter( int id){
        for (PessoaFisica pessoa : this.pessoasFisicas){
            if (pessoa.getId() == id){
                return pessoa;
            }
        }
        return null;
    }
    
    public ArrayList<PessoaFisica> obterTodos(){
        return this.pessoasFisicas;
    }
    
    public void persistir(String nomeArquivo)throws IOException{
        FileOutputStream fos = new FileOutputStream(nomeArquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.pessoasFisicas);
    }
    
    public void recuperar ( String nomeArquivo) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(nomeArquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.pessoasFisicas = (ArrayList<PessoaFisica>) ois.readObject();
    }
}
