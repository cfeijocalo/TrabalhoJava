package org.ufpr.sistemapedidos.controller;

import org.ufpr.sistemapedidos.model.Produto;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
		descricaoField.textProperty().addListener((ov, oldValue, newValue) -> {
			descricaoField.setText(newValue.toUpperCase());
		});
	}

	@FXML
	private void handleOk() {
		if (isValid()) {
			this.produto.setDescricao(descricaoField.getText());
			confirm = true;
			dialogStage.close();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			switch (invalidType) {
				case 1:
					descricaoField.requestFocus();
					alert.setTitle("Campo vazio");
					alert.setHeaderText("Campo descrição vazio.");
					alert.setContentText("Por favor, preencha o campo com a descrição do produto.");
					alert.showAndWait();
					break;
				case 2:
					descricaoField.requestFocus();
					alert.setTitle("Tamanho máximo");
					alert.setHeaderText("Descrição muito grande.");
					alert.setContentText("Máximo de caracteres permitidos Descrição(45)");
					alert.showAndWait();
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
		if (descricaoField.getText() == null || descricaoField.getText().isEmpty()) {
			invalidType = 1;
			return false;
		} else if (descricaoField.getText().length() > 45) {
			invalidType = 2;
			return false;
		}
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
		
		if (produto != null) {
			if (produto.getDescricao() != null) {
				this.descricaoField.setText(produto.getDescricao());
			}
		}
		
	}

}
