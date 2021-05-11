package br.com.senai.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.senai.controller.produto.EditarProduto;
import br.com.senai.controller.produto.ListarProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionaItemNoCarrinho {

	Scanner entrada = new Scanner(System.in);
	
	ListarProduto listaProduto = new ListarProduto();
	EditarProduto editaProduto = new EditarProduto();
	
	public CarrinhoModel cadastrarItemNoCarrinho(List<ProdutoModel> produtos) {
		CarrinhoModel carrinhoModel = new CarrinhoModel();
		
		if(produtos.size() <= 0) {
			System.out.println("As estantes estão todas vazias, quer comprar o que retardado?");
			return null;
		}
		
		listaProduto.listarProdutos();
		
		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		carrinhoModel.setIdDoProduto(entrada.nextInt());
		int idDoProduto = carrinhoModel.getIdDoProduto() - 1;
		if(carrinhoModel.getIdDoProduto() > produtos.size()) {
			System.out.println("Este produto não está cadastrado.");
			return null;
		}
		
		System.out.print("Informe a quantidade desejada: ");
		carrinhoModel.setQuantidadeDeItensNoCarrinho(entrada.nextInt());
			
		if(carrinhoModel.getQuantidadeDeItensNoCarrinho() > produtos.get(idDoProduto).getQuantidadeDeProduto()) {
			System.out.println("Tá em falta cara, dá uma segurada.");
			return null;
		}
		
		carrinhoModel.setProdutoModel(produtos.get(idDoProduto));
		
		carrinhoModel.setValorTotalDoItem(carrinhoModel.getQuantidadeDeItensNoCarrinho()
				* produtos.get(idDoProduto).getPrecoDoProduto());
		
		editaProduto.atualizarQuantidadeEValorTotal(produtos, carrinhoModel.getQuantidadeDeItensNoCarrinho(), idDoProduto);
		
		return carrinhoModel;
	}
	
	
}
