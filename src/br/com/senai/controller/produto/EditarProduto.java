package br.com.senai.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class EditarProduto {
	
	Scanner entrada = new Scanner(System.in);
	ListarProduto listaProduto;
	
	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		listaProduto = new ListarProduto();
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;
		
		if(produtos.size() <= 0) {
			System.out.println("Não possui produtos para serem editados.");
			return null;
		}
		
		listaProduto.listarDados();
		
		System.out.println("--- EDITAR DADOS DE PRODUTO ---");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = entrada.nextInt() - 1;
		
		if(idDoProduto >= produtos.size()) {
			System.out.println("Este produto não existe.");
			return null;
		}
		
		System.out.println("--- CAMPOS ---");
		System.out.println("1) Nome do produto");
		System.out.println("2) Preço unitário");
		System.out.println("3) Quantidade");
		System.out.print("Informe o campo que deseja editar: ");
		indexDoCampo = entrada.nextInt();
		
		switch (indexDoCampo) {
		case 1:
			editarNomeDoProduto(produtos, produto, idDoProduto);
			break;
		case 2:
			editarPreçoUnitario(produtos, produto, idDoProduto);
			break;
		case 3:
			editarQuantidadeDeProduto(produtos, produto, idDoProduto);
			break;
		default:
			System.out.println("Opção inválida!!!");
			break;
		}
		
		return produto;
	}

	private void editarQuantidadeDeProduto(List<ProdutoModel> produtos, ProdutoModel produto, int idDoProduto) {
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		
		System.out.print("Informe a quantidade do produto: ");
		produto.setQuantidadeDeProduto(entrada.nextInt());
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());
		
		produtos.set(idDoProduto, produto);
	}

	private List<ProdutoModel> editarPreçoUnitario(List<ProdutoModel> produtos, ProdutoModel produto, int idDoProduto) {
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
		
		System.out.print("Informe o novo preço para o produto: ");
		produto.setPrecoDoProduto(entrada.nextDouble());
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getQuantidadeDeProduto() * produto.getPrecoDoProduto());
		
		produtos.set(idDoProduto, produto);
		return produtos;
	}

	private List<ProdutoModel> editarNomeDoProduto(List<ProdutoModel> produtos, ProdutoModel produto, int idDoProduto) {
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
		
		System.out.print("Informe o novo nome para o produto: ");
		produto.setNomeDoProduto(entrada.next());
		
		produtos.set(idDoProduto, produto);
		return produtos;
	}
	
	public List<ProdutoModel> atualizarQuantidadeEValorTotal(List<ProdutoModel> produtos, int quantidade, int idDoProduto) {
		ProdutoModel produto = new ProdutoModel();
		produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto() - quantidade);
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produtos.set(idDoProduto, produto);
		return produtos;
	}
	
}
