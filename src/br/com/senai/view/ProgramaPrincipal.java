package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.controller.Controller;
import br.com.senai.controller.carrinho.AdicionaItemNoCarrinho;
import br.com.senai.controller.carrinho.ListCarrinho;
import br.com.senai.controller.cliente.AdicionarCliente;
import br.com.senai.controller.produto.CadastrarProduto;
import br.com.senai.controller.produto.DeletarProduto;
import br.com.senai.controller.produto.EditarProduto;
import br.com.senai.controller.produto.ListarProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {
	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoModel> itensNoCarrinho = new ArrayList<CarrinhoModel>();

		Controller produtoController = new Controller();
		ListCarrinho listCarrinho = new ListCarrinho();
		ListarProduto listaProduto = new ListarProduto();
		EditarProduto editaProduto = new EditarProduto();
		DeletarProduto deletaProduto = new DeletarProduto();
		AdicionaItemNoCarrinho adicionaItemNoCarrinho = new AdicionaItemNoCarrinho();
		CadastrarProduto cadastraProduto = new CadastrarProduto();
		AdicionarCliente adicionaCliente = new AdicionarCliente();
		boolean sair = false;

		String cliente = adicionaCliente.definirCliente();
		
		do {
			produtoController.menu();
			int opc = produtoController.opcao();

			switch (opc) {
			case 1:
				produtos.add(cadastraProduto.cadastrarProduto());
				break;
				
			case 2:
				//listaProduto.listarProdutos(produtos);
				listaProduto.listarDados();
				break;
				
			case 3:
				editaProduto.editarProduto(produtos);
				break;
				
			case 4:
				deletaProduto.removerProduto(produtos);
				break;
				
			case 5:
				itensNoCarrinho.add(adicionaItemNoCarrinho.cadastrarItemNoCarrinho(produtos));
				break;
				
			case 6:
				listCarrinho.listarItensNoCarrinho(itensNoCarrinho);
				break;
				
			case 7:
				listCarrinho.gerarCupom(itensNoCarrinho, cliente);
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
