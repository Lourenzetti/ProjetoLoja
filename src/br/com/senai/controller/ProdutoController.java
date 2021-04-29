package br.com.senai.controller;

import java.util.Iterator;
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

	public void menu(List<ProdutoModel> carrinho) {
		
		System.out.println("\n--- CARRINHO ---\n");
		
		listarProdutos(carrinho);
		
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item ");
		System.out.println("5) Adicionar ao carrinho");
		System.out.println("6) Remover item do carrinho");
		System.out.println("7) Confirmar compra");
		System.out.println("8) Esvaziar carrinho");
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
		ProdutoModel.setQuantidadeDeProduto(scanner.nextInt());
		ProdutoModel.setSaldoEmEstoque(ProdutoModel.getQuantidadeDeProduto() * ProdutoModel.getPrecoDoProduto());

		return ProdutoModel;
	}

	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.println("-------------------------------------------------");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");

		for (int i = 0; i < produtos.size(); i++) {
			System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", i + 1, produtos.get(i).getNomeDoProduto(),
					produtos.get(i).getPrecoDoProduto(), produtos.get(i).getQuantidadeDeProduto(),
					produtos.get(i).getSaldoEmEstoque());
		}

		return produtos;
	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;

		if (produtos.size() <= 0) {
			System.out.println("Lista de produtos vazia.");
			return null;
		}

		listarProdutos(produtos);

		System.out.println("--- EDITAR DADOS DE PRODUTO ---");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = scanner.nextInt() - 1;

		if (idDoProduto > produtos.size()) {
			System.out.println("ID inválido.");
			return null;
		}

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
			produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());

			produtos.set(idDoProduto, produto);

			break;

		case 2:
			System.out.print("Informe o novo preço para o produto: ");
			produto.setPrecoDoProduto(scanner.nextDouble());

			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getQuantidadeDeProduto() * produto.getPrecoDoProduto());

			produtos.set(idDoProduto, produto);
			break;

		case 3:
			System.out.print("Informe a nova quantidade para o produto: ");
			produto.setQuantidadeDeProduto(scanner.nextInt());

			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());

			produtos.set(idDoProduto, produto);
			break;

		default:
			System.out.println("Opção inválida");
			break;
		}

		return produto;
	}

	public void removerProduto(List<ProdutoModel> produtos) {

		listarProdutos(produtos);

		if (produtos.size() <= 0) {
			System.out.println("Lista de produtos vazia.");
			return;
		}

		System.out.println("--- REMOVER PRODUTO ---");

		System.out.print("Informe o ID do produto: ");
		int idDoProduto = scanner.nextInt();

		if (idDoProduto > produtos.size()) {
			System.out.println("ID inválido.");
			return;
		}

		produtos.remove(idDoProduto - 1);

	}

	public ProdutoModel adicionarAoCarrinho(List<ProdutoModel> produtos, List<ProdutoModel> carrinho) {

		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, quantidade;
		
		System.out.println(" --- PRODUTOS DISPONÍVEIS ---");
		listarProdutos(produtos);
		
		System.out.println(" --- CARRINHO ---");
		listarProdutos(carrinho);

		if (produtos.size() <= 0) {
			System.out.println("Lista de produtos vazia.");
			return null;
		}

		System.out.print("Informe o ID do produto: ");
		idDoProduto = scanner.nextInt() - 1;

		if (idDoProduto > produtos.size()) {
			System.out.println("ID inválido.");
			return null;
		}

		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		
		System.out.print("Quantidade: ");
		quantidade = scanner.nextInt();
		produto.setQuantidadeDeProduto(quantidade);
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		if(produtos.get(idDoProduto).getQuantidadeDeProduto() - quantidade < 0) {
			System.out.println("Quantidade inválida.");
			return null;
		}else {
		produtos.get(idDoProduto).setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto() - quantidade);
		produtos.get(idDoProduto).setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produtos.get(idDoProduto).getQuantidadeDeProduto());
		
		System.out.println("\n Produto adicionado ao carrinho.");
		}
		return produto;
	}

	public ProdutoModel removerDoCarrinho(List<ProdutoModel> produtos, List<ProdutoModel> carrinho) {
		
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, quantidade;
		
		System.out.println(" --- CARRINHO ---");
		listarProdutos(carrinho);

		System.out.print("Informe o ID do produto: ");
		idDoProduto = scanner.nextInt() - 1;

		if (idDoProduto > produtos.size()) {
			System.out.println("ID inválido.");
			return null;
		}
		
		quantidade = produto.getQuantidadeDeProduto();
		
		produtos.get(idDoProduto).setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto() + quantidade);
		produtos.get(idDoProduto).setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produtos.get(idDoProduto).getQuantidadeDeProduto());
		
		return null;
	}
	
//	public int obterProduto(List<ProdutoModel> produtos) {
//		
//		for(int i = 0; i < produtos.size(); i++) {
//			produtos.get(i).get
//		}
//		
//		return -1;
//	}
	
}
