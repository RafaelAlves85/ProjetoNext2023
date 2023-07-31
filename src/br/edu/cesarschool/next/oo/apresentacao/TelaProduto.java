package br.edu.cesarschool.next.oo.apresentacao;

import java.util.List;
import java.util.Scanner;

import br.edu.cesarschool.next.oo.entidade.Produto;
import br.edu.cesarschool.next.oo.negocio.MediatorProduto;

public class TelaProduto {
	
	private static final Scanner ENTRADA = new Scanner(System.in);
	private MediatorProduto mediatorProd = new MediatorProduto();
	public void iniciarTela() {
		int opcao = 0;
		do {
			System.out.println("Escolha uma op��o: ");
			System.out.println("1- Incluir produto");
			System.out.println("2- Alterar pre�o");
			System.out.println("3- Consultar produto");
			System.out.println("4- Gerar relat�rio geral de produtos");
			System.out.println("5- Sair");
			opcao = ENTRADA.nextInt();
			if (opcao == 1) {
				incluir();
			} else if (opcao == 2) {
				alterarPreco();
			} else if (opcao == 3) {
				consultarProduto();
			} else if (opcao == 4) {
				gerarRelatorioGeralProdutos();
			}
		} while(opcao != 5);
	}
	private void incluir() {
		String codigo = null;
		String nome = null;
		double preco = 0;
		System.out.println("Digite o c�digo: ");
		codigo = ENTRADA.next();
		System.out.println("Digite o nome: ");
		nome = ENTRADA.next();
		System.out.println("Digite o pre�o: ");
		preco = ENTRADA.nextDouble();
		Produto prod = new Produto(codigo, nome, preco);
		String mensagem = mediatorProd.incluir(prod);
		if (mensagem == null) {
			System.out.println("Produto inclu�do com sucesso!");
		} else {
			System.out.println(mensagem); 
		}
	}
	private void alterarPreco() {
		String codigo = null;
		double preco = 0;
		System.out.println("Digite o c�digo: ");
		codigo = ENTRADA.next();
		System.out.println("Digite o percentual de aumento: ");
		preco = ENTRADA.nextDouble();
		String mensagem = mediatorProd.alterarPreco(preco, codigo);
		if (mensagem == null) {
			System.out.println("Pre�o alterado com sucesso!");
		} else {
			System.out.println(mensagem); 
		}		
	}
	private void consultarProduto() {
		String codigo = null;
		System.out.println("Digite o c�digo: ");
		codigo = ENTRADA.next();
		Produto prod = mediatorProd.buscar(codigo);
		if (prod == null) {
			System.out.println("Produto n�o encontrado!");
		} else {
			System.out.println("Produto encontrado"); 
			System.out.println("C�digo: " + prod.getCodigo());
			System.out.println("Nome: " + prod.getNome());
			System.out.println("Pre�o: " + prod.getPreco());
		}		
	}	
	private void gerarRelatorioGeralProdutos() {
		List<Produto> produtos = mediatorProd.gerarRelatorioGeral();
		for (Produto produto : produtos) {
			System.out.println(produto);
		}
	}
}
