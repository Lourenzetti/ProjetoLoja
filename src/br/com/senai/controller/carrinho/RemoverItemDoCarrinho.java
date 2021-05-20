package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;


public class RemoverItemDoCarrinho {

		private Connection connection;
		private ProdutoModel produto = new ProdutoModel();
		
		public RemoverItemDoCarrinho() {
			connection = DataBaseConnection.getInstance().getConnection();
		}

		private Scanner entrada = new Scanner(System.in);
		private ListarCarrinho listaCarrinho;

		public void removerItem(String cliente) {
			PreparedStatement preparedStatement;
			listaCarrinho = new ListarCarrinho();
			System.out.println("--- REMOVER PRODUTO DO CARRINHO ---");

			listaCarrinho.listarCarrinho(cliente);

			System.out.print("Informe o ID do produto a ser removido: ");
			int idDoProduto = entrada.nextInt();

			try {
				
				if(!verificaSeExistemProdutos(idDoProduto, cliente)) {
					return;
				}
				
				String sql = "SELECT * from carrinho WHERE codigoDoProduto = ? and cliente = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.setString(2, cliente);
				ResultSet carrset = preparedStatement.executeQuery();
				carrset.next();
				
				sql = "DELETE FROM carrinho WHERE codigoDoProduto = ? and cliente = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.setString(2, cliente);
				preparedStatement.execute();
				
				sql = "SELECT * from produto WHERE codigoDoProduto = ?";
				PreparedStatement estoqueStatement;
				estoqueStatement = connection.prepareStatement(sql);
				estoqueStatement.setInt(1, idDoProduto);
				ResultSet prodset = estoqueStatement.executeQuery();
				prodset.next();
				
				System.out.println(prodset.getDouble("precoDoProduto"));
				produto.setPrecoDoProduto(prodset.getDouble("precoDoProduto"));
				produto.setQuantidadeDeProduto(prodset.getInt("quantidadeDeProduto") + carrset.getInt("quantidadeDeProduto"));
				produto.setSaldoEmEstoque(produto.getQuantidadeDeProduto() * produto.getPrecoDoProduto());
				
				sql = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
				estoqueStatement = connection.prepareStatement(sql);
				estoqueStatement.setInt(3, idDoProduto);
				estoqueStatement.setInt(1, produto.getQuantidadeDeProduto());
				estoqueStatement.setDouble(2, produto.getSaldoEmEstoque());
				estoqueStatement.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nErro!\nFalha ao deletar os dados. Contate o suporte.");
				return;
			}
		}

		public boolean verificaSeExistemProdutos(int idDoProduto, String cliente) {
			PreparedStatement preparedStatement;
			
			try {
				
				String sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ? and cliente = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.setString(2, cliente);

				ResultSet resultSet = preparedStatement.executeQuery();

				if (!resultSet.next()) {
					System.out.println("Você não possui este produto no carrinho.");
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
