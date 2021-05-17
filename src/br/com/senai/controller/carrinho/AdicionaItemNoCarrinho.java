package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import br.com.dao.DataBaseConnection;
import br.com.senai.controller.produto.ListarProduto;
import br.com.senai.model.CarrinhoModel;

public class AdicionaItemNoCarrinho {

	private Scanner entrada = new Scanner(System.in);
	private Connection connection;
	private ListarProduto listaProduto = new ListarProduto();

	public AdicionaItemNoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	@SuppressWarnings("resource")
	public void cadastrarItemNoCarrinho() {
		CarrinhoModel carrinhoModel = new CarrinhoModel();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		listaProduto.listarProdutos();

		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		int idDoProduto = entrada.nextInt();

		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe.");
				return;
			} else {
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
			String sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {

				sql = "INSERT INTO carrinho (codigoDoProduto, nomeDoProduto, precoDoProduto, quantidadeDeProduto, saldoEmEstoque) "
						+ "VALUES (?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.setString(2, carrinhoModel.getNomeDoProduto());
				preparedStatement.setDouble(3, carrinhoModel.getPrecoDoProduto());
				preparedStatement.setInt(4, carrinhoModel.getQuantidadeDeProduto());
				preparedStatement.setDouble(5, carrinhoModel.getSaldoEmEstoque());

				preparedStatement.execute();

				sql = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? " + "WHERE codigoDoProduto = ?";
				preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setInt(1,
						resultSet.getInt("quantidadeDeProduto") - carrinhoModel.getQuantidadeDeProduto());
				preparedStatement.setDouble(2,
						resultSet.getDouble("precoDoProduto") * resultSet.getInt("quantidadeDeProduto"));
				preparedStatement.setInt(3, idDoProduto);

				preparedStatement.execute();

			} else {

				sql = "UPDATE carrinho SET quantidadeDeProduto = ?, saldoEmEstoque = ? " + "WHERE codigoDoProduto = ?";
				preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setInt(1, resultSet.getInt("quantidadeDeProduto") + carrinhoModel.getQuantidadeDeProduto());
				
				preparedStatement.setDouble(2, resultSet.getDouble("precoDoProduto") * (resultSet.getInt("quantidadeDeProduto") + carrinhoModel.getQuantidadeDeProduto()));
				
				preparedStatement.setInt(3, idDoProduto);
				
				preparedStatement.execute();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nErro!\nFalha ao localizar produto. Contate o suporte.");
			return;
		}

	}

}
