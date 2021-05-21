package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.dao.DataBaseConnection;

public class GerarCupomFiscal {

	private Connection connection;
	
	public GerarCupomFiscal() {
		connection = DataBaseConnection.getInstance().getConnection();	
	}
	
	private ListarCarrinho listaCarrinho;

	public void gerarCupom(String cliente) {
		PreparedStatement preparedStatement;
		listaCarrinho = new ListarCarrinho();
		System.out.println("--- CUPOM FISCAL ---");

		try {
			
			if(!verificaSeExistemProdutos()) {
				return;
			}
			
			String sql = "SELECT SUM(precoDoProduto) FROM carrinho";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			double total = resultSet.getDouble("SUM(precoDoProduto)");
			
			listaCarrinho.listarCarrinho(cliente);
			System.out.println("|------------------------------------------------|--------------------|");
			System.out.printf("| Cliente: | %-35s |Total: R$%-10.2f |\n", cliente, total);

			sql = "DELETE FROM carrinho WHERE cliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cliente);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nErro!\nFalha ao gerar cupom fiscal. Contate o suporte.");
			return;
		}
	}
	
	public boolean verificaSeExistemProdutos() {
		PreparedStatement preparedStatement;
		try {
			
			String sql = "SELECT * FROM carrinho";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("O carrinho está vazio.");
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
