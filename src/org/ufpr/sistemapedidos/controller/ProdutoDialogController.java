package org.ufpr.sistemapedidos.controller;

import org.ufpr.sistemapedidos.model.Produto;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * @author Caio Calo
 */
public class ProdutoDialogController {
	
	@FXML
	private TextField descricaoField;
	
	private Stage dialogStage;
	private Produto produto;
	private boolean confirm = false;
	
	private int invalidType;
	
	@FXML
	private void initialize() {
	}

	@FXML
	private void handleOk() {
		if (isValid()) {
			
		} else {
			switch (invalidType) {
				case 1:
					break;
				default:
					break;
			}
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isValid() {
		return true;
	}

	// GETTERS AND SETTERS
	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
