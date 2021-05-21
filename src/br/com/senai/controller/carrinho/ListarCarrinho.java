package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.dao.DataBaseConnection;

public class ListarCarrinho {

	private Connection connection;
	
	public ListarCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public void listarCarrinho(String cliente) {

		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM carrinho WHERE cliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cliente);
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("\n----- CARRINHO -----\n");
			System.out.printf("| %-2s | %-20s | %-10s | %-5s | %-18s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");

			if (!resultSet.next()) {
				System.out.println("O carrinho está vazio.");
				return;
			}
			resultSet.previous();

			while (resultSet.next()) {
				System.out.printf("| %-2s | %-20s | %-10s | %-5s | %-18.2f |\n", resultSet.getInt("codigoDoProduto"),
						resultSet.getString("nomeDoProduto"), resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeProduto"), resultSet.getDouble("saldoEmEstoque"));

			}

			return;
		} catch (Exception e) {
			return;
		}

	}

}
