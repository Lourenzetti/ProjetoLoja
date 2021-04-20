package br.com.senai.model;

public class ProdutoModel {
	// ATRIBUTOS
	private String nomeDoProduto;
	private double precoDoProduto;
	private int quantidadedeProduto;
	private double saldoEmEstoque;
	
	public ProdutoModel() {
		
	}
	
	public ProdutoModel(String nomeDoProduto, double precoDoProduto, int quantidadedeProduto, double saldoEmEstoque) {
		super();
		this.nomeDoProduto = nomeDoProduto;
		this.precoDoProduto = precoDoProduto;
		this.quantidadedeProduto = quantidadedeProduto;
		this.saldoEmEstoque = saldoEmEstoque;
	}

	// METODOS PADRAO
	public String getNomeDoProduto() {
		return nomeDoProduto;
	}

	public void setNomeDoProduto(String nomeDoProduto) {
		this.nomeDoProduto = nomeDoProduto;
	}

	public double getPrecoDoProduto() {
		return precoDoProduto;
	}

	public void setPrecoDoProduto(double precoDoProduto) {
		this.precoDoProduto = precoDoProduto;
	}

	public int getQuantidadeDeProduto() {
		return quantidadedeProduto;
	}

	public void setQuantidadedeProduto(int quantidadedeProduto) {
		this.quantidadedeProduto = quantidadedeProduto;
	}

	public double getSaldoEmEstoque() {
		return saldoEmEstoque;
	}

	public void setSaldoEmEstoque(double saldoEmEstoque) {
		this.saldoEmEstoque = saldoEmEstoque;
	}

}
