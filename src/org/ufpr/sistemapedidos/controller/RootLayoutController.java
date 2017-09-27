package org.ufpr.sistemapedidos.controller;

import org.ufpr.sistemapedidos.app.Main;

import javafx.fxml.FXML;

public class RootLayoutController {

	private Main main;
	
	@FXML
	private void telaCliente() {
		this.main.showClienteView();
	}
	
	@FXML
	private void telaPedido() {
		this.main.showPedidoView();
	}
	
	@FXML
	private void telaProduto() {
		this.main.showProdutoView();
	}
	
	// GETTERS AND SETTERS
	public void setMain(Main main) {
		this.main = main;
	}

}
