package br.edu.cesarschool.next.oo.negocio;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.edu.cesarschool.next.oo.dao.DAOProduto;
import br.edu.cesarschool.next.oo.entidade.Produto;

public class MediatorProduto {
	private DAOProduto daoProd = new DAOProduto();
	public String incluir(Produto prod) {
		if (prod == null) {
			return "Produto n�o informado"; 
		} else if (stringNulaOuVazia(prod.getCodigo())) {
			return "C�digo do produto n�o informado";
		} else if (stringNulaOuVazia(prod.getNome())) {
			return "Nome do produto n�o informado"; 
		} else if (prod.getPreco() <= 0) {
			return "Pre�o inv�lido";
		} else {
			boolean ret = daoProd.incluir(prod);
			if (!ret) {
				return "Produto j� existente";
			} else {
				return null;
			}
		}		
	}
	public String alterarPreco(double perc, String codigo) {
		if (stringNulaOuVazia(codigo)) {
			return "C�digo do produto n�o informado"; 
		} else if (perc < 0) {
			return "Perecentual de aumento inv�lido";
		} else {
			Produto prod = daoProd.buscar(codigo);
			if (prod == null) {
				return "Produto n�o existente";
			} else {
				prod.aumentarPreco(perc);
				daoProd.alterar(prod);
				return null;
			}
		}		
	}	
	public Produto buscar(String codigo) {
		if (stringNulaOuVazia(codigo)) {
			return null; 
		} else {		
			return daoProd.buscar(codigo);
		}
	}
	public List<Produto> gerarRelatorioGeral() {
		Produto[] prods = daoProd.buscarTodos();
		List<Produto> listaProd = Arrays.asList(prods);
		Collections.sort(listaProd, new ComparadorProdutoNome());
		return listaProd;
		
	}
	private boolean stringNulaOuVazia(String valor) {
		return valor == null || valor.trim().equals("");
	}
}
