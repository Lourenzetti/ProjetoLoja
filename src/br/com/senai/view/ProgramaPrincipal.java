package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.controller.ProdutoController;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {
	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoModel> itensNoCarrinho = new ArrayList<CarrinhoModel>();

		ProdutoController produtoController = new ProdutoController();

		boolean sair = false;

		String cliente = produtoController.definirCliente();
		
		do {
			produtoController.menu(itensNoCarrinho);
			int opc = produtoController.opcao();

			switch (opc) {
			case 1:
				produtos.add(produtoController.cadastrarProduto());
				break;
			case 2:
				produtoController.listarProdutos(produtos);
				break;
			case 3:
				produtoController.editarProduto(produtos);
				break;
			case 4:
				produtoController.removerProduto(produtos);
				break;
			case 5:
				itensNoCarrinho.add(produtoController.cadastrarItemNoCarrinho(produtos));
				break;
				
			case 6:
				produtoController.listarItensNoCarrinho(itensNoCarrinho);
				break;
				
			case 7:
				produtoController.gerarCupom(itensNoCarrinho, cliente);
				break;
				
			case 9:
				sair = true;
				break;

			default:
				System.out.println("Opção inválida!!!");
				break;
			}
		} while (!sair);

		System.out.println("Sistema encerrado!!!");
	}
}
