package org.ufpr.sistemapedidos.controller;

import java.sql.SQLException;

import org.ufpr.sistemapedidos.dao.ClienteDAO;
import org.ufpr.sistemapedidos.model.Cliente;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

	private int invalidType;

	@FXML
	private void initialize() {
		nomeField.textProperty().addListener((ov, oldValue, newValue) -> {
		     nomeField.setText(newValue.toUpperCase());
		});
		
		sobreNomeField.textProperty().addListener((ov, oldValue, newValue) -> {
		     sobreNomeField.setText(newValue.toUpperCase());
		});
		
		cpfField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					cpfField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	@FXML
	private void handleOk() {
		if (isValid()) {
			this.cliente.setCpf(cpfField.getText());
			this.cliente.setNome(nomeField.getText());
			this.cliente.setSobreNome(sobreNomeField.getText());
			confirm = true;
			dialogStage.close();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			switch (invalidType) {
				case 1:
					nomeField.requestFocus();
					alert.setTitle("Campo vazio");
					alert.setHeaderText("Campo nome vazio.");
					alert.setContentText("Por favor, preencha o campo com o nome do cliente.");
					alert.showAndWait();
					break;
				case 2:
					sobreNomeField.requestFocus();
					alert.setTitle("Campo vazio");
					alert.setHeaderText("Campo sobrenome vazio.");
					alert.setContentText("Por favor, preencha o campo com o sobrenome do cliente.");
					alert.showAndWait();
					break;
				case 3:
					alert.setTitle("Tamanho máximo");
					alert.setHeaderText("Nome ou sobrenome muito grande.");
					alert.setContentText("Máximo de caracteres permitidos Nome(30) Sobrenome(50)");
					alert.showAndWait();
					break;
				case 4:
					cpfField.requestFocus();
					alert.setTitle("Campo vazio");
					alert.setHeaderText("Campo CPF vazio.");
					alert.setContentText("Por favor, preencha o campo com o CPF do cliente.");
					alert.showAndWait();
					break;
				case 5:
					cpfField.requestFocus();
					alert.setTitle("CPF");
					alert.setHeaderText("CPF já cadastrado.");
					alert.setContentText("Existe um cliente com o CPF " + cpfField.getText() + " cadastrado.");
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
		ClienteDAO cDao = new ClienteDAO();
		if (nomeField.getText() == null || nomeField.getText().isEmpty()) {
			this.invalidType = 1;
			return false;
		} else if (sobreNomeField.getText() == null || sobreNomeField.getText().isEmpty()) {
			this.invalidType = 2;
			return false;
		} else if (nomeField.getText().length() > 30 || sobreNomeField.getText().length() > 50) {
			this.invalidType = 3;
			return false;
		} else if (cpfField.getText() == null || cpfField.getText().isEmpty()) {
			this.invalidType = 4;
			return false;
		} else
			try {
				if (!cpfField.isDisable()) {
					if (!cDao.selectAll("WHERE cpf = '" + cpfField.getText() + "';").isEmpty()) {
						this.invalidType = 5;
						return false;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;

		if (cliente != null) {
			if (cliente.getNome() != null) {
				this.nomeField.setText(cliente.getNome());
			}
			if (cliente.getSobreNome() != null) {
				this.sobreNomeField.setText(cliente.getSobreNome());
			}
			if (cliente.getCpf() != null) {
				this.cpfField.setText(cliente.getCpf());
				this.cpfField.setDisable(true);
			}
		}

	}

}
