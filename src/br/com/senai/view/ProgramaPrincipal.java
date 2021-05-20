package br.com.senai.view;

import br.com.senai.controller.Controller;
import br.com.senai.controller.carrinho.AdicionaItemNoCarrinho;
import br.com.senai.controller.carrinho.GerarCupomFiscal;
import br.com.senai.controller.carrinho.ListarCarrinho;
import br.com.senai.controller.carrinho.RemoverItemDoCarrinho;
import br.com.senai.controller.cliente.AdicionarCliente;
import br.com.senai.controller.produto.CadastrarProduto;
import br.com.senai.controller.produto.DeletarProduto;
import br.com.senai.controller.produto.EditarProduto;
import br.com.senai.controller.produto.ListarProduto;

public class ProgramaPrincipal {
	public static void main(String[] args) {


		Controller produtoController = new Controller();
		ListarCarrinho listaCarrinho = new ListarCarrinho();
		ListarProduto listaProduto = new ListarProduto();
		EditarProduto editaProduto = new EditarProduto();
		DeletarProduto deletaProduto = new DeletarProduto();
		AdicionaItemNoCarrinho adicionaItemNoCarrinho = new AdicionaItemNoCarrinho();
		RemoverItemDoCarrinho removerItemDoCarrinho = new RemoverItemDoCarrinho();
		CadastrarProduto cadastraProduto = new CadastrarProduto();
		AdicionarCliente adicionaCliente = new AdicionarCliente();
		GerarCupomFiscal cupom = new GerarCupomFiscal();
		boolean sair = false;

		String cliente = adicionaCliente.definirCliente();
		
		do {
			produtoController.menu();
			int opc = produtoController.opcao();

			switch (opc) {
			case 1:
				cadastraProduto.cadastrarProduto();
				break;
				
			case 2:
				listaProduto.listarProdutos();
				break;
				
			case 3:
				editaProduto.editarProduto();
				break;
				
			case 4:
				deletaProduto.removerProduto();
				break;
				
			case 5:
				adicionaItemNoCarrinho.cadastrarItemNoCarrinho(cliente);
				break;
				
			case 6:
				listaCarrinho.listarCarrinho(cliente);
				break;
				
			case 7:
				removerItemDoCarrinho.removerItem(cliente);
				break;
				
			case 8:
				cupom.gerarCupom(cliente);
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
