package org.ufpr.sistemapedidos.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.ufpr.sistemapedidos.dao.ClienteDAO;
import org.ufpr.sistemapedidos.dao.ProdutoDAO;
import org.ufpr.sistemapedidos.model.Cliente;
import org.ufpr.sistemapedidos.model.ItemDoPedido;
import org.ufpr.sistemapedidos.model.Pedido;
import org.ufpr.sistemapedidos.model.Produto;
import org.ufpr.sistemapedidos.util.Utilidades;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 
 * @author Caio Calo
 */
public class PedidoDialogController {

	@FXML
	private TableView<ItemDoPedido> itensTable;

	@FXML
	private TableColumn<ItemDoPedido, String> quantidadeColumn;

	@FXML
	private TableColumn<ItemDoPedido, String> produtoColumn;

	@FXML
	private ComboBox<Cliente> clienteComboBox;

	@FXML
	private ComboBox<Produto> produtoComboBox;

	@FXML
	private Label cpfLabel;

	@FXML
	private Label dataLabel;

	@FXML
	private TextField quantidadeField;

	private ObservableList<ItemDoPedido> itens = FXCollections.observableArrayList();
	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
	private ObservableList<Produto> produtos = FXCollections.observableArrayList();

	private Stage dialogStage;
	private Pedido pedido;
	private boolean confirm;

	private int invalidType;

	@FXML
	private void initialize() {
		quantidadeColumn.setCellValueFactory(cellData -> cellData.getValue().quantidadeProperty().asString());
		produtoColumn.setCellValueFactory(cellData -> cellData.getValue().getProduto().descricaoProperty());

		Callback<ListView<Cliente>, ListCell<Cliente>> clienteCell = new Callback<ListView<Cliente>, ListCell<Cliente>>() {
			@Override
			public ListCell<Cliente> call(ListView<Cliente> p) {
				return new ListCell<Cliente>() {
					@Override
					protected void updateItem(Cliente item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setGraphic(null);
						} else {
							setText(empty ? "" : item.getNome() + " " + item.getSobreNome());
						}
					}
				};
			}
		};

		Callback<ListView<Produto>, ListCell<Produto>> produtoCell = new Callback<ListView<Produto>, ListCell<Produto>>() {
			@Override
			public ListCell<Produto> call(ListView<Produto> p) {
				return new ListCell<Produto>() {
					@Override
					protected void updateItem(Produto item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setGraphic(null);
						} else {
							setText(empty ? "" : item.getDescricao());
						}
					}
				};
			}
		};

		showClientesDados(null);
		
		clienteComboBox.setCellFactory(clienteCell);

		produtoComboBox.setCellFactory(produtoCell);

		clienteComboBox.setButtonCell(clienteCell.call(null));
		produtoComboBox.setButtonCell(produtoCell.call(null));
		
		clienteComboBox.getSelectionModel().selectedItemProperty()
			.addListener((observable, oldValue, newValue) -> showClientesDados(newValue));

	}

	@FXML
	private void handleOk() {
		if (isValid()) {
			dialogStage.close();
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

	@FXML
	private void handleAdicionar() {

	}

	@FXML
	private void handleRemover() {

	}

	private boolean isValid() {
		return true;
	}
	
	private void showClientesDados(Cliente cliente) {
		if (cliente != null) {
			cpfLabel.setText(cliente.getCpf());
		} else {
			cpfLabel.setText("");
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

	private void buscaProdutos() {
		ProdutoDAO pDao = new ProdutoDAO();
		this.produtos.clear();
		List<Produto> produtos;
		try {
			produtos = pDao.selectAll("");
			if (!produtos.isEmpty()) {
				for (Produto p : produtos) {
					this.produtos.add(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
		buscaClientes();
		this.clienteComboBox.setItems(clientes);
		buscaProdutos();
		this.produtoComboBox.setItems(produtos);
		if (pedido != null) {
			if (pedido.getData() != null) {
				dataLabel.setText(Utilidades.converteLocalDateToString(pedido.getData()));
			} else {
				dataLabel.setText(Utilidades.converteLocalDateToString(LocalDate.now()));
			}
		} else {
			dataLabel.setText(Utilidades.converteLocalDateToString(LocalDate.now()));
		}
	}

}
