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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	
	private Callback<ListView<Cliente>, ListCell<Cliente>> clienteCell;
	private Callback<ListView<Produto>, ListCell<Produto>> produtoCell;

	@FXML
	private void initialize() {
		quantidadeColumn.setCellValueFactory(cellData -> cellData.getValue().quantidadeProperty().asString());
		produtoColumn.setCellValueFactory(cellData -> cellData.getValue().getProduto().descricaoProperty());

		clienteCell = new Callback<ListView<Cliente>, ListCell<Cliente>>() {
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

		produtoCell = new Callback<ListView<Produto>, ListCell<Produto>>() {
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
		
		quantidadeField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					quantidadeField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		showClienteDados(null);
		
		clienteComboBox.setCellFactory(clienteCell);

		produtoComboBox.setCellFactory(produtoCell);

		clienteComboBox.setButtonCell(clienteCell.call(null));
		produtoComboBox.setButtonCell(produtoCell.call(null));
		
		clienteComboBox.getSelectionModel().selectedItemProperty()
			.addListener((observable, oldValue, newValue) -> showClienteDados(newValue));
		
		itensTable.getSelectionModel().selectedItemProperty()
			.addListener((observable, oldValue, newValue) -> showProdutoDados(newValue));

	}

	@FXML
	private void handleOk() {
		if (isValid()) {
			this.pedido.setCliente(clienteComboBox.getSelectionModel().getSelectedItem());
			this.pedido.setItens(itensTable.getItems());
			confirm = true;
			dialogStage.close();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			switch (invalidType) {
				case 1:
					alert.setTitle("Campo vazio");
					alert.setHeaderText("Não há produtos.");
					alert.setContentText("Por favor, insira produtos para efetuar o pedido.");
					alert.showAndWait();
					break;
				case 2:
					alert.setTitle("Campo vazio");
					alert.setHeaderText("Selecione um cliente.");
					alert.setContentText("Por favor, selecione um cliente.");
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

	@FXML
	private void handleAdicionar() {
		ItemDoPedido item = new ItemDoPedido(
				(int) Integer.parseInt(quantidadeField.getText()), 
				produtoComboBox.getSelectionModel().getSelectedItem());
		
		if (itens.contains(item)) {
			itens.remove(item);
			itens.add(item);
		} else {
			itens.add(item);
		}
		
		if (produtoComboBox.isDisable()) {
			produtoComboBox.setDisable(false);
		}
		
		produtoComboBox.getItems().remove(item.getProduto());
			
		this.itensTable.setItems(itens);
	}

	@FXML
	private void handleRemover() {
		itens.remove(this.itensTable.getSelectionModel().getSelectedItem());
		this.itensTable.setItems(itens);
	}

	private boolean isValid() {
		if (this.itens.isEmpty()) {
			invalidType = 1;
			return false;
		} else if (this.clienteComboBox.getSelectionModel().isEmpty()) {
			invalidType = 2;
			return false;
		}
		return true;
	}
	
	private void showClienteDados(Cliente cliente) {
		if (cliente != null) {
			cpfLabel.setText(cliente.getCpf());
		} else {
			cpfLabel.setText("");
		}
	}
	
	private void showProdutoDados(ItemDoPedido item) {
		if (item != null) {
			produtoComboBox.getSelectionModel().select(item.getProduto());
			produtoComboBox.setDisable(true);
			quantidadeField.setText(item.getQuantidade() + "");
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
		buscaProdutos();
		if (pedido != null) {
			if (pedido.getCliente() != null) {
				dataLabel.setText(pedido.getCliente().getNome() 
						+ " " + pedido.getCliente().getSobreNome());
				cpfLabel.setText(pedido.getCliente().getCpf());
				this.clienteComboBox.getSelectionModel().select(pedido.getCliente());
			}
			if (pedido.getItens() != null) {
				if (!pedido.getItens().isEmpty()) {
					for (ItemDoPedido i : pedido.getItens()) {
						itens.add(i);
						produtos.remove(i.getProduto());
					}
					this.itensTable.setItems(itens);
				}
			}
			if (pedido.getData() != null) {
				dataLabel.setText(Utilidades.converteLocalDateToString(pedido.getData()));
			} else {
				dataLabel.setText(Utilidades.converteLocalDateToString(LocalDate.now()));
			}
		} else {
			dataLabel.setText(Utilidades.converteLocalDateToString(LocalDate.now()));
		}
		
		this.clienteComboBox.setItems(clientes);
		this.produtoComboBox.setItems(produtos);
	}

}
