package model;

import java.io.Serializable;

public class PessoaJuridica extends Pessoa implements Serializable {
    
    private String cnpj;
    public PessoaJuridica(int id, String nome, String cnpj){
        super(id, nome);
        this.cnpj = cnpj;
    }
    
    public String getCnpj(){
        return this.cnpj;
    }
    
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }
    
    @Override
    public void exibir(){
        System.out.println(
                    "Id: " + this.getId() + 
                    "\nNome: " + this.getNome() + 
                    "\nCNPJ: " + this.getCnpj());
    }
    
}
