package br.edu.cesarschool.next.oo.apresentacao;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;
import br.edu.cesarschool.next.oo.negocio.MediatorContaCorrente;

public class TelaContaCorrente {

    MediatorContaCorrente mediatorContaCorrente = new MediatorContaCorrente();

    private static final Scanner scanner = new Scanner(System.in); //Faz a leitura da entrada do usuário

    public TelaContaCorrente() {
    }

    public void iniciarTela (){

        int opcao = 0;
        do {
            System.out.println("1- Incluir conta");
            System.out.println("2- Creditar");
            System.out.println("3- Debitar");
            System.out.println("4- Buscar");
            System.out.println("5- Gerar Relatório Geral de Contas");
            System.out.println("6- Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            if (opcao == 1) {
                incluir();
            } else if (opcao == 2) {
                creditar();
            } else if (opcao == 3) {
                debitar();
            } else if (opcao == 4) {
                buscar();
            } else if (opcao == 5) {
                gerarRelatorioGeralDeContas();
            } else if (opcao == 6) {
                System.out.println("--- Programa encerrado ---");
                return; //return dentro do loop, sai do loop
            } else {
                System.out.println("Opçcão inválida. Tente novamente.\n");
            }
        } while(true);

    }
    
    private void incluir (){
        System.out.print("Informe o número da conta: ");
        String numero = scanner.next(); // já declara e recebe a info na variável - ".next": vai receber a próxima info que vier do sistema
        System.out.print("Informe o nome do titular da conta: ");
        String nomeCorrentista = scanner.next();
        System.out.print("Informe o saldo inicial da conta: ");
        double saldo = scanner.nextDouble();
        System.out.print("É uma conta poupanca? 1 - Sim  2 - Não ");
        String opcaoPoupanca = scanner.next();

        if(Objects.equals(opcaoPoupanca , "1")){
            //compara o que tem na opçaPoupanca com a String "1". Se for ok, ele entra no if

            System.out.println("Informe o percentual de bônus: ");
            double percentualDeBonus = scanner.nextDouble();
            ContaPoupanca contaPoupanca = new ContaPoupanca(numero, saldo, nomeCorrentista, percentualDeBonus);
            
            String mensagem = mediatorContaCorrente.incluir(contaPoupanca);
            //chamar o método incluir do mediator, e verificar se a mensagem retornada é nula, indicando inclusão realizada com sucesso.

            if( mensagem == null){
                System.out.println("Conta Poupança incluída com sucesso");
            }else{
                System.out.println(mensagem); //imprimir o que vier na variável mensagem
            }

        }else{
            ContaCorrente contaCorrente = new ContaCorrente(numero,saldo, nomeCorrentista);
            String mensagem = mediatorContaCorrente.incluir(contaCorrente);
            //chamar o método incluir do mediator, e verificar se a mensagem retornada é nula, indicando inclusão realizada com sucesso.

            if( mensagem == null){
                System.out.println("Conta Corrente incluída com sucesso");
            }else{
                System.out.println(mensagem); //imprimir o que vier na variável mensagem
            }
        }


    }

    private void creditar(){
        System.out.print("\nInforme o número da conta para crédito: ");
        String numero = scanner.next();
        System.out.print("Informe o valor a ser creditado: ");
        double valor = scanner.nextDouble();

        String mensagem = mediatorContaCorrente.creditar(valor, numero); //Mediator que faz o crédito
        if (mensagem == null) {
            System.out.println("Crédito realizado com sucesso.\n");
        } else {  
            System.out.println(mensagem);
        }    
    }

     private void debitar(){
        System.out.print("\nInforme o número da conta para débito: ");
        String numero = scanner.next();
        System.out.print("Informe o valor a ser debitado: ");
        double valor = scanner.nextDouble();

        String mensagem = mediatorContaCorrente.debitar(valor, numero); //Mediator que faz o débito
        if (mensagem == null) {
            System.out.println("Débito realizado com sucesso.\n");
        } else {  
            System.out.println(mensagem);
        }    
    }

    private void buscar (){
        System.out.println("Informe o número da conta a ser buscado: ");
        String numero = scanner.next();
        ContaCorrente conta = mediatorContaCorrente.buscar(numero); //Mediator que faz a busca
        //Todas as operações, de fato, são feitas pela camanda de negócio
        if (conta == null){
            System.out.println("Conta inexistente");
        }else{
            System.out.println(conta);
            //Imprime corretamente pq foi feita a sobrescirta do método toString da Classe Conta Corrente
        }

    }

    private void gerarRelatorioGeralDeContas (){
        List<ContaCorrente> contas = mediatorContaCorrente.gerarRelatorioGeral();
        //Esse método mediatorContaCorrente.gerarRelatorioGeral()  é quem gera a lista de contas
        for (ContaCorrente conta : contas){
            //Esse "for" faz o seguinte: para cada objeto do tipo ContaCorrente na lista chamada contas, faça uma determinada ação
            //Esse conta (sem o "s") pode ser qq nome. É uma referência para um objeto que está dentro da lista "contas" (depois dos ":")
            System.out.println(conta); //imprime cada item (conta) do array "contas"
        }

    }
}

