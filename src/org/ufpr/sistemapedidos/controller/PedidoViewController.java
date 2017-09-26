package org.ufpr.sistemapedidos.controller;

import org.ufpr.sistemapedidos.app.Main;
import org.ufpr.sistemapedidos.model.Pedido;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class PedidoViewController {

	private TableView<Pedido> pedidoTable;
	
	private ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
	
	private Main main;
	
	public PedidoViewController() {
	
	}
	
	@FXML
	private void initialize() {
		showPedidosDados(null);
	}
	
	public void setMain(Main main) {
		this.main = main;
		buscaPedidos();
		this.pedidoTable.setItems(pedidos);
		
	}
	
	private void showPedidosDados(Pedido pedido) {
		if (pedido != null) {
			
		} else {
			
		}
	}
	
	@FXML
	private void novoPedido() {
		
	}
	
	@FXML
	private void editaPedido() {
		
	}
	
	@FXML
	private void removePedido() {
		
	}
	
	private void buscaPedidos() {
		
	}
	
}
