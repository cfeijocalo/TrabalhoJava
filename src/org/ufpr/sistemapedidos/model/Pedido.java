package org.ufpr.sistemapedidos.model;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Pedido {

	private IntegerProperty id;
	private LocalDate data;
	private Cliente cliente;
	private List<ItemDoPedido> itens;

	public Pedido() {
		this(0, null, null, null);
	}

	public Pedido(int id, LocalDate data, Cliente cliente, List<ItemDoPedido> itens) {
		this.id = new SimpleIntegerProperty(id);
		this.data = data;
		this.cliente = cliente;
		this.itens = itens;
	}

	// GETTERS AND SETTERS
	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}
	
	public IntegerProperty idProperty() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemDoPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemDoPedido> itens) {
		this.itens = itens;
	}

}
