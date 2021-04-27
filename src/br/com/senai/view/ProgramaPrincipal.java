package br.com.senai.view;

import java.util.List;
import java.util.ArrayList;

import br.com.senai.controller.ProdutoController;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<ProdutoModel> carrinho = new ArrayList<ProdutoModel>();

		// Objeto Controller do sistema
		ProdutoController lojaController = new ProdutoController();
		ProdutoController carrinhoController = new ProdutoController();

		// Controle do loop de saida
		boolean sair = false;

		do {
			lojaController.menu(carrinho);
			int opcao = lojaController.opcao();

			switch (opcao) {
			case 1:
				produtos.add(lojaController.cadastrarProduto());
				break;

			case 2:
				lojaController.listarProdutos(produtos);
				break;

			case 3:
				lojaController.editarProduto(produtos);
				break;
				
			case 4:
				lojaController.removerProduto(produtos);
				break;
			
			case 5:
				carrinho.add(carrinhoController.adicionarAoCarrinho(produtos, carrinho));
				break;
				
			case 9:
				sair = true;
				break;

			default:
				System.out.println("Opção Invalida!!!");
				break;
			}

		} while (!sair);
		System.out.println();
	}

}
