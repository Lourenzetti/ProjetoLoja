package br.com.senai.controller.carrinho;

import java.util.List;

import br.com.senai.model.CarrinhoModel;

public class ListCarrinho {

	public void gerarCupom(List<CarrinhoModel> itensNoCarrinho, String cliente) {
		
		if(itensNoCarrinho.size() <= 0) {
			System.out.println("Carrinho tá vazio bocó");
			return;
		}
		
		listarItensNoCarrinho(itensNoCarrinho);
		System.out.println("Cliente: " + cliente);
	}
	
	public List<CarrinhoModel> listarItensNoCarrinho(List<CarrinhoModel> itensNoCarrinho) {
		System.out.println("--- ITENS NO CARRINHO ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");
		
		if(itensNoCarrinho.size() <= 0) {
			return null;
		}
		
		itensNoCarrinho.forEach(item ->{
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n",
			item.getIdDoProduto(),
			item.getProdutoModel().getNomeDoProduto(),
			item.getProdutoModel().getPrecoDoProduto(),
			item.getQuantidadeDeItensNoCarrinho(),
			item.getValorTotalDoItem());
		});
		
		double valorTotalDoCarrinho = itensNoCarrinho.stream().mapToDouble(CarrinhoModel::getValorTotalDoItem).sum();
		System.out.println("Valor total: R$" + valorTotalDoCarrinho);
		
		return itensNoCarrinho;
	}
	
}

