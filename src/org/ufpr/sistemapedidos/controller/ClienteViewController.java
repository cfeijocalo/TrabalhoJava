package org.ufpr.sistemapedidos.controller;

import java.sql.SQLException;
import java.util.List;

import org.ufpr.sistemapedidos.app.Main;
import org.ufpr.sistemapedidos.dao.ClienteDAO;
import org.ufpr.sistemapedidos.dao.PedidoDAO;
import org.ufpr.sistemapedidos.model.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * 
 * @author Caio Calo
 */
public class ClienteViewController {

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

	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

	private Main main;

	public ClienteViewController() {
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

		showClientesDados(null);

		clienteTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showClientesDados(newValue));

	}

	public void setMain(Main main) {
		this.main = main;
		buscaClientes();
		clienteTable.setItems(clientes);
	}

	private void showClientesDados(Cliente cliente) {
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
	private void novoCliente() {
		Cliente auxCliente = new Cliente();
		if (main.showClienteDialog(auxCliente)) {
			ClienteDAO cDao = new ClienteDAO();
			try {
				cDao.insert(auxCliente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			buscaClientes();
		}
	}

	@FXML
	private void editaCliente() {
		Cliente auxCliente = clienteTable.getSelectionModel().getSelectedItem();

		if (auxCliente != null) {

			if (main.showClienteDialog(auxCliente)) {
				ClienteDAO cDao = new ClienteDAO();
				try {
					cDao.update(auxCliente);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				buscaClientes();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum cliente foi selecionado");
			alert.setContentText("Por favor, selecione um cliente na tabela.");

			alert.showAndWait();
		}

	}

	@FXML
	private void removeCliente() {
		Cliente auxcliente = clienteTable.getSelectionModel().getSelectedItem();

		if (auxcliente != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmar remoção");
			alert.setHeaderText("Deseja remover o cliente: ");
			alert.setContentText(auxcliente.getNome() + " " + auxcliente.getSobreNome());

			//TODO tratar cliente com pedido.
			if (alert.showAndWait().get().getButtonData() == ButtonData.OK_DONE) {
				ClienteDAO cDao = new ClienteDAO();
				PedidoDAO pDao = new PedidoDAO();
				try {
					if (pDao.selectAll("WHERE id_cliente = " + auxcliente.getId()).isEmpty()) {
						if (cDao.delete(auxcliente)) {
							buscaClientes();
						}
					} else {
						Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setTitle("Erro Delete");
						alert2.setHeaderText("Cliente já possui pedidos");
						alert2.setContentText("Para deletar este cliente é necessário deletar TODOS seus pedidos primeiro.");

						alert2.showAndWait();
					}	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum cliente foi selecionado");
			alert.setContentText("Por favor, selecione um cliente na tabela.");

			alert.showAndWait();
		}
	}

	private void buscaClientes() {
		ClienteDAO cDao = new ClienteDAO();
		this.clientes.clear();
		List<Cliente> clientes;
		try {
			clientes = cDao.selectAll("");
			if (!clientes.isEmpty()) {
				for (Cliente c : clientes) {
					this.clientes.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
