package br.edu.cesarschool.next.oo.dao;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class DAOContaCorrente {

    private CadastroObjetos cadastro = new CadastroObjetos(ContaCorrente.class); //clase CadastroObjetos está dentro da lib "persistenciaobjetos"

    public boolean incluir (ContaCorrente conta){
        ContaCorrente contaBusca = buscar(conta.getNumero());
        if (contaBusca != null){
            return false;
        }else {
            cadastro.incluir(conta, conta.getNumero());
            return true;
        }
    }

    public boolean alterar(ContaCorrente conta) {
        ContaCorrente contaBusca = buscar(conta.getNumero());
        if (contaBusca == null) {
            return false;
        } else {
            cadastro.alterar(conta, conta.getNumero());
            return true;
        }
    }

    public ContaCorrente buscar(String numero) {
        return (ContaCorrente) cadastro.buscar(numero); //chama função buscar contido na lib .jar (diponibilizada pelo prof.)
    }

    public ContaCorrente[] buscarTodos() {   //o tipo de retorno é um array de contas correntes
        Serializable[] rets = cadastro.buscarTodos(ContaCorrente.class); //usa o funçaõ buscarTodos da LIB
        ContaCorrente[] conta = new ContaCorrente[rets.length];  //criando um array de contas correntes
        for (int i = 0; i < rets.length; i++) {
            conta[i] = (ContaCorrente) rets[i];
        }
        return conta;
    }

}
