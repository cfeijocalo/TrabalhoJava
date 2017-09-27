package org.ufpr.sistemapedidos.controller;

import java.sql.SQLException;
import java.util.List;

import org.ufpr.sistemapedidos.app.Main;
import org.ufpr.sistemapedidos.dao.PedidoDAO;
import org.ufpr.sistemapedidos.model.ItemDoPedido;
import org.ufpr.sistemapedidos.model.Pedido;
import org.ufpr.sistemapedidos.util.Utilidades;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PedidoViewController {

	@FXML
	private TableView<Pedido> pedidoTable;
	
	@FXML
	private TableView<ItemDoPedido> itensTable;

	@FXML
	private TableColumn<Pedido, String> idColumn;

	@FXML
	private TableColumn<Pedido, String> dataColumn;

	@FXML
	private TableColumn<Pedido, String> nomeColumn;

	@FXML
	private TableColumn<Pedido, String> cpfColumn;

	@FXML
	private TableColumn<ItemDoPedido, String> quantidadeColumn;
	
	@FXML
	private TableColumn<ItemDoPedido, String> produtoColumn;
	
	@FXML
	private TextField pesquisaField;

	@FXML
	private DatePicker dataPesquisa;
	
	@FXML
	private Label nPedidoLabel;
	
	@FXML
	private Label dataLabel;
	
	@FXML
	private Label clienteLabel;
	
	@FXML
	private Label cpfLabel;

	private ObservableList<Pedido> pedidos = FXCollections.observableArrayList();

	private ObservableList<ItemDoPedido> itens = FXCollections.observableArrayList();
	
	private Main main;

	public PedidoViewController() {
		pedidoTable = new TableView<Pedido>();
		itensTable = new TableView<ItemDoPedido>();
		idColumn = new TableColumn<Pedido, String>();
		dataColumn = new TableColumn<Pedido, String>();
		nomeColumn = new TableColumn<Pedido, String>();
		cpfColumn = new TableColumn<Pedido, String>();
		quantidadeColumn = new TableColumn<ItemDoPedido, String>();
		produtoColumn = new TableColumn<ItemDoPedido, String>();
	}

	@FXML
	private void initialize() {

		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
		dataColumn.setCellValueFactory(
				cellData -> Utilidades.converteLocalDateToStringProperty(cellData.getValue().getData()));
		nomeColumn.setCellValueFactory(cellData -> cellData.getValue().getCliente().nomeCompletoProperty());
		cpfColumn.setCellValueFactory(cellData -> cellData.getValue().getCliente().cpfProperty());

		quantidadeColumn.setCellValueFactory(cellData -> cellData.getValue().quantidadeProperty().asString());
		produtoColumn.setCellValueFactory(cellData -> cellData.getValue().getProduto().descricaoProperty());
		
		showPedidosDados(null);

		pedidoTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPedidosDados(newValue));

	}

	public void setMain(Main main) {
		this.main = main;
		buscaPedidos();
		this.pedidoTable.setItems(pedidos);

	}

	private void showPedidosDados(Pedido pedido) {
		if (pedido != null) {
			nPedidoLabel.setText(String.valueOf(pedido.getId()));
			dataLabel.setText(Utilidades.converteLocalDateToString(pedido.getData()));
			clienteLabel.setText(pedido.getCliente().nomeCompletoProperty().get());
			cpfLabel.setText(pedido.getCliente().getCpf());
			if (!pedido.getItens().isEmpty()) {
				this.itens.clear();
				for (ItemDoPedido i : pedido.getItens()) {
					this.itens.add(i);
				}
			}
			this.itensTable.setItems(itens);
		} else {
			nPedidoLabel.setText("");
			dataLabel.setText("");
			clienteLabel.setText("");
			cpfLabel.setText("");
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
		PedidoDAO pDao = new PedidoDAO();
		this.pedidos.clear();
		List<Pedido> pedidos;
		try {
			pedidos = pDao.selectAll("");
			if (!pedidos.isEmpty()) {
				for (Pedido p : pedidos) {
					this.pedidos.add(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
