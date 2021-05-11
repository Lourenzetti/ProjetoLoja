package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.List;



import br.com.dao.DataBaseConnection;
//import br.com.senai.model.ProdutoModel;

public class ListarProduto {

	private Connection connection;
	private ResultSet resultSet;
	
	public ListarProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
		this.resultSet = listarDados();
	}
	
	public ResultSet listarDados() {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM produto");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.println("\n----- PRODUTOS CADASTRADOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");
			
			while(resultSet.next()) {
				System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n",
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
	
//	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
//		System.out.println("\n----- PRODUTOS CADASTRADOS -----\n");
//		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");
//		
//		for (int i = 0; i < produtos.size(); i++) {
//			System.out.printf("| %2s | %10s | R$%6.2f | %4s | %9s |\n",
//					i + 1,
//					produtos.get(i).getNomeDoProduto(),
//					produtos.get(i).getPrecoDoProduto(),
//					produtos.get(i).getQuantidadeDeProduto(),
//					produtos.get(i).getSaldoEmEstoque());
//		}
//		
//		return produtos;
//	}
//	
}
