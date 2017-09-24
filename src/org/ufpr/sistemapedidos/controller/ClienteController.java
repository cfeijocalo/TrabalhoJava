package org.ufpr.sistemapedidos.controller;

import org.ufpr.sistemapedidos.app.Main;
import org.ufpr.sistemapedidos.model.Cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClienteController {

	@FXML
	private TableView<Cliente> clienteTable;

	@FXML
	private TableColumn<Cliente, String> idColumn;

	@FXML
	private TableColumn<Cliente, String> nomeColumn;

	@FXML
	private TableColumn<Cliente, String> sobreNomeColumn;

	@FXML
	private TableColumn<Cliente, String> cpfColumn;
	
	@FXML
	private Label nomeLabel;

	@FXML
	private Label sobreNomeLabel;

	@FXML
	private Label cpfLabel;

	@SuppressWarnings("unused")
	private Main main;

	public ClienteController() {
		clienteTable = new TableView<Cliente>();
		idColumn = new TableColumn<Cliente, String>();
		nomeColumn = new TableColumn<Cliente, String>();
		sobreNomeColumn = new TableColumn<Cliente, String>();
		cpfColumn = new TableColumn<Cliente, String>();
	}

	@FXML
	private void initialize() {
		
		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
		nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		sobreNomeColumn.setCellValueFactory(cellData -> cellData.getValue().sobreNomeProperty());
		cpfColumn.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
		
		showClienteDados(null);
		
		clienteTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showClienteDados(newValue));
		
	}
	
	public void setMain(Main main) {
		this.main = main;
		clienteTable.setItems(main.getClientes());
	}

	private void showClienteDados(Cliente cliente) {
		if (cliente != null) {
			nomeLabel.setText(cliente.getNome());
			sobreNomeLabel.setText(cliente.getSobreNome());
			cpfLabel.setText(cliente.getCpf());
		} else {
			nomeLabel.setText("");
			sobreNomeLabel.setText("");
			cpfLabel.setText("");
		}
	}
	
	@FXML
	private void removeCliente() {
		int selectedIndex = clienteTable.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmar remoção");
			alert.setHeaderText("Deseja remover o cliente: ");
			alert.setContentText(clienteTable.getItems().get(selectedIndex).getNome() + " " + clienteTable.getItems().get(selectedIndex).getSobreNome());
			
			if (alert.showAndWait().get().getButtonData() == ButtonData.OK_DONE) {
				System.out.println("Aeeee");
				clienteTable.getItems().remove(selectedIndex);			
			}
			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum cliente foi selecionado");
			alert.setContentText("Por favor, selecione um cliente na tabela.");
			
			alert.showAndWait();
		}
	}
	
	
	
}
