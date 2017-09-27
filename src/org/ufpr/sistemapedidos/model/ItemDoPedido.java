package org.ufpr.sistemapedidos.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ItemDoPedido {

	private IntegerProperty quantidade;
	private Produto produto;

	public ItemDoPedido() {
		this(0, null);
	}

	public ItemDoPedido(int quantidade, Produto produto) {
		this.quantidade = new SimpleIntegerProperty(quantidade);
		this.produto = produto;
	}

	// GETTERS AND SETTERS
	public int getQuantidade() {
		return quantidade.get();
	}

	public void setQuantidade(int quantidade) {
		this.quantidade.set(quantidade);
	}
	
	public IntegerProperty quantidadeProperty() {
		return quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDoPedido other = (ItemDoPedido) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

}
