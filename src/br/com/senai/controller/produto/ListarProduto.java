package br.com.senai.controller.produto;

import java.util.List;

import br.com.senai.model.ProdutoModel;

public class ListarProduto {

	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.println("\n----- PRODUTOS CADASTRADOS -----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Pre�o", "Qtd", "R$ Total");
		
		for (int i = 0; i < produtos.size(); i++) {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | %9s |\n",
					i + 1,
					produtos.get(i).getNomeDoProduto(),
					produtos.get(i).getPrecoDoProduto(),
					produtos.get(i).getQuantidadeDeProduto(),
					produtos.get(i).getSaldoEmEstoque());
		}
		
		return produtos;
	}
	
}