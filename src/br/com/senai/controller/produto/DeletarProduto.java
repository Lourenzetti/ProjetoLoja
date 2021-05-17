package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import br.com.dao.DataBaseConnection;

public class DeletarProduto {

	private Connection connection;

	public DeletarProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	private Scanner entrada = new Scanner(System.in);
	private ListarProduto listaProduto;

	public void removerProduto() {
		PreparedStatement preparedStatement;
		listaProduto = new ListarProduto();
		System.out.println("--- REMOVER PRODUTO ---");

		listaProduto.listarProdutos();

		System.out.print("Informe o ID do produto a ser removido: ");
		int idDoProduto = entrada.nextInt();

		try {
			
			if(!verificaSeExistemProdutos(idDoProduto)) {
				return;
			}
			
			String sql = "DELETE FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nErro!\nFalha ao deletar os dados. Contate o suporte.");
			return;
		}
	}

	public boolean verificaSeExistemProdutos(int idDoProduto) {
		PreparedStatement preparedStatement;
		try {
			
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe.");
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
