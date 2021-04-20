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
		ProdutoModel.setSaldoEmEstoque(ProdutoModel.getQuantidadeDeProduto() * ProdutoModel.getPrecoDoProduto());

		return ProdutoModel;
	}

	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- PRODUTOS CADASTRADOS ---");
		System.out.printf("| %10s | %8s | %4s | %9s |\n", "Produto", "Preço", "Qtd", "R$ Total");

		produtos.forEach(produto -> {
			System.out.printf("| %10s | %8s | %4s | %9s |\n", produto.getNomeDoProduto(), produto.getPrecoDoProduto(),
					produto.getQuantidadeDeProduto(), produto.getSaldoEmEstoque());
		});

		return produtos;
	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;

		System.out.println("--- EDOTAR DADOS DE PRODUTO ---");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = scanner.nextInt();

		System.out.println("--- CAMPOS ---");
		System.out.println("1) Nome do produto");
		System.out.println("2) Preço unitário");
		System.out.println("3) Quantidade");
		System.out.println("Informe o campo que deseja editar: ");
		indexDoCampo = scanner.nextInt();

		switch (indexDoCampo) {
		case 1:
			System.out.print("Informe o novo nome para o produto: ");
			produto.setNomeDoProduto(scanner.next());

			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setQuantidadedeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());

			produtos.set(idDoProduto, produto);

			break;

		}

		return produto;
	}

}
