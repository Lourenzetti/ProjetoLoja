package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class ProdutoController {
	
	private Scanner scanner;
	
	public ProdutoController() {
		scanner = new Scanner(System.in);
	}
	
	public int opcao() {
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	public void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item ");
		System.out.println("5) Realizar item");
		System.out.println("9) Encerrar");
		
	}

	public ProdutoModel cadastrarProduto() {
		ProdutoModel ProdutoModel = new ProdutoModel();
		System.out.println("--- CADASTRAR ITENS ---");
		System.out.print("Produto: ");
		scanner.nextLine();
		ProdutoModel.setNomeDoProduto(scanner.nextLine());
		System.out.print("Preço: ");
		ProdutoModel.setPrecoDoProduto(scanner.nextDouble());
		System.out.print("Quantidade: ");
		ProdutoModel.setQuantidadedeProduto(scanner.nextInt());
		ProdutoModel.setSaldoEmEstoque(ProdutoModel.getQuantidadedeProduto() * ProdutoModel.getPrecoDoProduto());
		
		return ProdutoModel;
	}
	
	public void consultarProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- PRODUTOS CADASTRADOS ---");
		for (ProdutoModel produtoModel : produtos) {
			System.out.println(produtoModel);
		}
		
	}
}
