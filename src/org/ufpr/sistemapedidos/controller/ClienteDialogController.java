package org.ufpr.sistemapedidos.controller;

import org.ufpr.sistemapedidos.model.Cliente;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * @author Caio Calo
 */
public class ClienteDialogController {

	@FXML
	private TextField nomeField;

	@FXML
	private TextField sobreNomeField;

	@FXML
	private TextField cpfField;

	private Stage dialogStage;
	private Cliente cliente;
	private boolean confirm = false;

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleOk() {
		if (isValid()) {
			this.cliente.setCpf(cpfField.getText());
			this.cliente.setNome(nomeField.getText());
			this.cliente.setSobreNome(sobreNomeField.getText());
			confirm = true;
		}
		dialogStage.close();
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;

		if (cliente != null) {
			this.nomeField.setText(cliente.getNome());
			this.sobreNomeField.setText(cliente.getSobreNome());
			this.cpfField.setText(cliente.getCpf());
		}

	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

}
