package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.List;



import br.com.dao.DataBaseConnection;
//import br.com.senai.model.ProdutoModel;

public class ListarProduto {

	private Connection connection;
	
	public ListarProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ResultSet listarProdutos() {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM produto";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.println("\n----- PRODUTOS CADASTRADOS -----\n");
			System.out.printf("| %-2s | %-20s | %-10s | %-5s | %-18s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");
			
			if(!resultSet.next()) {
				System.out.println("Não existem dados cadastrados.");
				return null;
			}
			resultSet.previous();
			
			while(resultSet.next()) {
				System.out.printf("| %-2s | %-20s | %-10s | %-5s | %-18.2f |\n",
					resultSet.getInt("codigoDoProduto"),
					resultSet.getString("nomeDoProduto"),
					resultSet.getDouble("precoDoProduto"),
					resultSet.getInt("quantidadeDeProduto"),
					resultSet.getDouble("saldoEmEstoque")
					);
			
			}
			
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
	
}
