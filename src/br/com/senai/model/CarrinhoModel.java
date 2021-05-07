package br.com.senai.model;

public class CarrinhoModel {
	private int quantidadeDeItensNoCarrinho;
	private ProdutoModel produtoModel;
	private double valorTotalDoItem;
	private int idDoProduto;
	
	
	public CarrinhoModel(int quantidadeDeItensNoCarrinho, ProdutoModel produtoModel, double valorTotalNoCarrinho,
			int idDoProduto) {
		this.quantidadeDeItensNoCarrinho = quantidadeDeItensNoCarrinho;
		this.produtoModel = produtoModel;
		this.valorTotalDoItem = valorTotalNoCarrinho;
		this.idDoProduto = idDoProduto;
	}
	
	public CarrinhoModel() {
	}

	public int getQuantidadeDeItensNoCarrinho() {
		return quantidadeDeItensNoCarrinho;
	}
	public void setQuantidadeDeItensNoCarrinho(int quantidadeDeItensNoCarrinho) {
		this.quantidadeDeItensNoCarrinho = quantidadeDeItensNoCarrinho;
	}
	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}
	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}
	public double getValorTotalDoItem() {
		return valorTotalDoItem;
	}
	public void setValorTotalDoItem(double valorTotalDoItem) {
		this.valorTotalDoItem = valorTotalDoItem;
	}
	public int getIdDoProduto() {
		return idDoProduto;
	}
	public void setIdDoProduto(int idDoProduto) {
		this.idDoProduto = idDoProduto;
	}
	
}
