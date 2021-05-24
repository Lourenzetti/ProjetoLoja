package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class EditarProduto {
	
	private Scanner entrada = new Scanner(System.in);
	private ListarProduto listaProduto;
	private Connection connection;
	
	public EditarProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ProdutoModel editarProduto() {
		PreparedStatement preparedStatement;
		
		listaProduto = new ListarProduto();
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;
		
		if(listaProduto.listarProdutos().equals(null)) {
			System.out.println("N�o existem dados cadastrados.");
			return null;
		}
		
		System.out.println("--- EDITAR DADOS DE PRODUTO ---");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = entrada.nextInt();
		
		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Este produto n�o existe.");
				return null;
			}else {
				produto.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				produto.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				produto.setQuantidadeDeProduto(resultSet.getInt("quantidadeDeProduto"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		System.out.println("--- CAMPOS ---");
		System.out.println("1) Nome do produto");
		System.out.println("2) Pre�o unit�rio");
		System.out.println("3) Quantidade");
		System.out.print("Informe o campo que deseja editar: ");
		indexDoCampo = entrada.nextInt();
		
		switch (indexDoCampo) {
		case 1:
			editarNomeDoProduto(produto, idDoProduto);
			break;
		case 2:
			editarPrecoUnitario(produto, idDoProduto);
			break;
		case 3:
			editarQuantidadeDeProduto(produto, idDoProduto);
			break;
		default:
			System.out.println("Op��o inv�lida!!!");
			break;
		}
		
		return produto;
	}

	private void editarQuantidadeDeProduto(ProdutoModel produto, int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.print("Informe a quantidade do produto: ");
		produto.setQuantidadeDeProduto(entrada.nextInt());
		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());
		
		try {
			String sql = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? "
					+ "WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, produto.getQuantidadeDeProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nErro!\nFalha ao editar os dados. Contate o suporte.");
			return;
		}
		
	}

	private ProdutoModel editarPrecoUnitario(ProdutoModel produto, int idDoProduto) {
		PreparedStatement preparedStatement;
				
		System.out.print("Informe o novo pre�o para o produto: ");
		produto.setPrecoDoProduto(entrada.nextDouble());
		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());
		
		try {
			String sql = "UPDATE produto SET precoDoProduto = ?, saldoEmEstoque = ? "
					+ "WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, produto.getPrecoDoProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		

		return produto;
	}

	private ProdutoModel editarNomeDoProduto(ProdutoModel produto, int idDoProduto) {
	
		System.out.print("Informe o novo nome para o produto: ");
		entrada.nextLine();
		produto.setNomeDoProduto(entrada.nextLine());
		
		PreparedStatement preparedStatement;
		
		try {
			String sql = "UPDATE produto SET nomeDoProduto = ? WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, produto.getNomeDoProduto());
			preparedStatement.setInt(2, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			return null;
		}
		
		return produto;
	}
	
}
