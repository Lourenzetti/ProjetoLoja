package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import br.com.dao.DataBaseConnection;
import br.com.senai.controller.produto.ListarProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionaItemNoCarrinho {

	private Scanner entrada = new Scanner(System.in);
	private Connection connection;
	private ListarProduto listaProduto = new ListarProduto();

	public AdicionaItemNoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void cadastrarItemNoCarrinho(String cliente) {
		CarrinhoModel carrinhoModel = new CarrinhoModel();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		listaProduto.listarProdutos();

		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		int idDoProduto = entrada.nextInt();
		int quantEstoque;
		
		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe.");
				return;
			} else {
				quantEstoque = resultSet.getInt("quantidadeDeProduto");
				carrinhoModel.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				carrinhoModel.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nErro!\nFalha ao localizar produto. Contate o suporte.");
			return;
		}

		System.out.print("Informe a quantidade desejada: ");
		carrinhoModel.setQuantidadeDeProduto(entrada.nextInt());
		carrinhoModel.setSaldoEmEstoque(carrinhoModel.getQuantidadeDeProduto() * carrinhoModel.getPrecoDoProduto());

		try {
			String sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ? and cliente = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.setString(2, cliente);
			
			resultSet = preparedStatement.executeQuery();

			if(quantEstoque < carrinhoModel.getQuantidadeDeProduto()) {
				System.out.println("Quantidade inválida: Não há produtos suficientes no estoque!");
				return;
			}
			
			if (!resultSet.next()) {

				sql = "INSERT INTO carrinho (codigoDoProduto, nomeDoProduto, precoDoProduto, quantidadeDeProduto, saldoEmEstoque, cliente) "
						+ "VALUES (?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.setString(2, carrinhoModel.getNomeDoProduto());
				preparedStatement.setDouble(3, carrinhoModel.getPrecoDoProduto());
				preparedStatement.setInt(4, carrinhoModel.getQuantidadeDeProduto());
				preparedStatement.setDouble(5, carrinhoModel.getSaldoEmEstoque());
				preparedStatement.setString(6, cliente);

				preparedStatement.execute();
				
				ResultSet upEstoque;
				PreparedStatement statEstoque;
				
				sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
				statEstoque = connection.prepareStatement(sql);
				statEstoque.setInt(1, idDoProduto);
				
				upEstoque = statEstoque.executeQuery();
				upEstoque.next();
				
				ProdutoModel produto = new ProdutoModel();
				
				produto.setPrecoDoProduto(upEstoque.getDouble("precoDoProduto"));
				produto.setQuantidadeDeProduto(upEstoque.getInt("quantidadeDeProduto") - carrinhoModel.getQuantidadeDeProduto());
				produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());
				
				sql = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
				statEstoque = connection.prepareStatement(sql);

				statEstoque.setInt(1,produto.getQuantidadeDeProduto());
				statEstoque.setDouble(2, produto.getSaldoEmEstoque());
				statEstoque.setInt(3, idDoProduto);
				
				statEstoque.execute();
				
			} else {
				PreparedStatement updateEstoque;
				ResultSet estoqueResult;
				ResultSet carrResult;
				
				sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
				updateEstoque = connection.prepareStatement(sql);
				updateEstoque.setInt(1, idDoProduto);
				estoqueResult = updateEstoque.executeQuery();
				estoqueResult.next();
				
				ProdutoModel produto = new ProdutoModel();
				
				produto.setPrecoDoProduto(estoqueResult.getDouble("precoDoProduto"));
				produto.setQuantidadeDeProduto(estoqueResult.getInt("quantidadeDeProduto") - carrinhoModel.getQuantidadeDeProduto());
				produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());
									
				sql = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
				updateEstoque = connection.prepareStatement(sql);

				updateEstoque.setInt(1, produto.getQuantidadeDeProduto());
				updateEstoque.setDouble(2, produto.getSaldoEmEstoque());
				updateEstoque.setInt(3, idDoProduto);
				
				updateEstoque.execute();					
				
				sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ? and cliente = ?";
				updateEstoque = connection.prepareStatement(sql);
				updateEstoque.setInt(1, idDoProduto);
				updateEstoque.setString(2, cliente);
				carrResult = updateEstoque.executeQuery();
				carrResult.next();
				
				carrinhoModel.setQuantidadeDeProduto(carrResult.getInt("quantidadeDeProduto") + carrinhoModel.getQuantidadeDeProduto());
				carrinhoModel.setSaldoEmEstoque(carrinhoModel.getPrecoDoProduto() * carrinhoModel.getQuantidadeDeProduto());
				
				sql = "UPDATE carrinho SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ? and cliente = ?";
				updateEstoque = connection.prepareStatement(sql);

				updateEstoque.setInt(1, carrinhoModel.getQuantidadeDeProduto());
				updateEstoque.setDouble(2, carrinhoModel.getSaldoEmEstoque());
				updateEstoque.setInt(3, idDoProduto);
				updateEstoque.setString(4, cliente);
				
				updateEstoque.execute();	

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nErro!\nFalha ao adicionar o produto. Contate o suporte.");
			return;
		}

	}

}
