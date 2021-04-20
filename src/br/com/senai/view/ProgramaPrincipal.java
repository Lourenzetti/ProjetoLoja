package br.com.senai.view;

import java.util.List;
import java.util.ArrayList;

import br.com.senai.controller.ProdutoController;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		
		//Objeto Controller do sistema
		ProdutoController lojaController = new ProdutoController();
		
		//Controle do loop de saida
		boolean sair = false;
		
		do {
			lojaController.menu();
			int opcao = lojaController.opcao();
			
			switch (opcao) {
			case 1:
				produtos.add(lojaController.cadastrarProduto());
				break;
				
			case 2:
				lojaController.consultarProdutos(produtos);
				break;

			case 9:
				sair = true;
				break;
				
			default:
				System.out.println("Opção Invalida!!!");
				break;
			}
			
		} while(!sair);
		System.out.println();
	}

}
