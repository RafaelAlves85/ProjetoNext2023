package br.edu.cesarschool.next.oo.entidade;

import java.io.Serializable;

public class ContaCorrente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String numero;
    private double saldo; 
    private String nomeDoCorrentista;
    
    
    
    
    public ContaCorrente() {
    }


    public ContaCorrente(String numero, double saldo, String nomeDoCorrentista) {
        this.numero = numero;
        this.saldo = saldo;
        this.nomeDoCorrentista = nomeDoCorrentista;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public String getNumero() {
        return numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }


    public double getSaldo() {
        return saldo;
    }


   
    public String getNomeDoCorrentista() {
        return nomeDoCorrentista;
    }


    public void setNomeDoCorrentista(String nomeDoCorrentista) {
        this.nomeDoCorrentista = nomeDoCorrentista;
    }

    
    public void creditar (double valor){

        saldo = saldo + valor; 

    }
    
    public void debitar (double valor){

        saldo = saldo - valor;
    }

    @Override
    public String toString (){
        return "\nNúmero da conta: " + numero + "\nSaldo atual: " + saldo + "\nNome do correntista: " + nomeDoCorrentista;
        //Sobrescreve a função toString e formata conforme acima ("toString" é uma classe herdade da classe object)
    }

}
