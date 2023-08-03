package br.edu.cesarschool.next.oo.negocio;

import java.util.Arrays;
import java.util.List;

import br.edu.cesarschool.next.oo.dao.DAOContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;

public class MediatorContaCorrente {

    DAOContaCorrente daoContaCorrente = new DAOContaCorrente();

    // Esse construtor já está implicto na criação da classe
    // Mas, por boa prática, deve-se incluir
    public MediatorContaCorrente() {

    }

    public String incluir(ContaCorrente conta) { // Método incluir

        if (stringNulaOuVazia(conta.getNumero())) {
            return "Número de conta inválido";
            // Checa se o nº da conta é nulo ou vazio, usando a chamada do método
        } else if (conta.getNumero().length() < 5 || conta.getNumero().length() > 8) {
            return "Número de caracteres inválido";
        } else if (conta.getSaldo() < 0) {
            return "Valor do saldo não pode ser menor que zero";
        } else if (stringNulaOuVazia(conta.getNomeDoCorrentista())) {
            return "Nome inválido";
        } else if (conta.getNomeDoCorrentista().length() > 60) {
            return "O Nome do correntista não pode ter mais que 60 caracteres";
        } else if (conta instanceof ContaPoupanca) {
            // Faz um cast da conta para Conta Pounpança para poder acessar o "get"
            // percentual de bônus
            if (((ContaPoupanca) conta).getPercentualDeBonus() < 0) {
                return "O percentual de bônus não pode ser menor do que zero";
            }
        } else {
            boolean ret = daoContaCorrente.incluir(conta);
            if (!ret) { // salva na variável "ret" o retorno "true ou false" da método incluir da Classe
                        // DaoContaCorrente;
                return "Conta ja existente";
            }

        }

        return null;

    }

    public String creditar(double valor, String numero) {
        if (stringNulaOuVazia(numero)) { // checando se o valor nulo ou vazio;
            return "Número de conta inválido";
        } else if (valor < 0) { // checando se o valor é maior ou igual a zero;
            return "Valor não poder ser menor que zero";
        } else {
            ContaCorrente conta = daoContaCorrente.buscar(numero); // retorna uma conta corrente que foi originada do
                                                                   // método daoContaCorrente
            if (conta == null) { // checa se a conta extraída acima se ela existe (diferente de nulo)
                return "Conta não existe";
            } else {
                conta.creditar(valor); // chama a função e credita o valor na conta
                daoContaCorrente.alterar(conta); // ele creditou o valor na conta, e usa o "dao" para alterar a conta
                                                 // (usando propriedades da biblioteca "persistenciaobjetos")

                return null; // pra finalizar o métod creditar
            }

        }

    }

    public String debitar(double valor, String numero) {
        if (stringNulaOuVazia(numero)) { // checando se o valor nulo ou vazio;
            return "Número de conta inválido";
        } else if (valor < 0) { // checando se o valor é maior ou igual a zero;
            return "Valor não poder ser menor que zero";
        } else {
            ContaCorrente conta = daoContaCorrente.buscar(numero); // retorna uma conta corrente que foi originada do
                                                                   // método daoContaCorrente
            if (conta == null) { // checa se a conta extraída acima se ela existe (diferente de nulo)
                return "Conta não existe";
            } else {
                if (conta.getSaldo() < valor) { // checa o saldo contido na conta e compara com o valor que está sendo
                                                // debitado
                    return "Saldo insuficiente";
                } else {
                    conta.debitar(valor); // chama a função e credita o valor na conta
                    daoContaCorrente.alterar(conta); // ele creditou o valor na conta, e usa o "dao" para alterar a
                                                     // conta (usando propriedades da biblioteca "persistenciaobjetos")

                    return null;
                }

            }

        }

    }

    public ContaCorrente buscar(String numero) {  //método buscar

        if (stringNulaOuVazia(numero)) {
            return null;

        } else {
            return daoContaCorrente.buscar(numero);

        }

    }


    public List<ContaCorrente> gerarRelatorioGeral() {  //o retorno desse método é do tipo lista conta corrente
        ContaCorrente[] contas = daoContaCorrente.buscarTodos(); //retorna uma lista de contas correntes
        List<ContaCorrente> listaContas = Arrays.asList(contas);
        listaContas.sort(new ComparadorContaCorrenteSaldo());
        return listaContas;


    }







    private boolean stringNulaOuVazia(String numero) {
        return numero == null || numero.trim().equals("");
        // return numero == null || numero.trim().isEmpty(); --> Faz a mesma coisa da
        // linha de cima
    }

}
