package org.ufpr.sistemapedidos.controller;

import java.sql.SQLException;
import java.util.List;

import org.ufpr.sistemapedidos.app.Main;
import org.ufpr.sistemapedidos.dao.ProdutoDAO;
import org.ufpr.sistemapedidos.model.Produto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * 
 * @author Caio Calo
 */
public class ProdutoViewController {

	@FXML
	private TableView<Produto> produtoTable;

	@FXML
	private TableColumn<Produto, String> idColumn;

	@FXML
	private TableColumn<Produto, String> descricaoColumn;

	@FXML
	private Label descricaoLabel;

	private ObservableList<Produto> produtos = FXCollections.observableArrayList();

	private Main main;

	public ProdutoViewController() {
		produtoTable = new TableView<Produto>();
		idColumn = new TableColumn<Produto, String>();
		descricaoColumn = new TableColumn<Produto, String>();
	}

	@FXML
	private void initialize() {
		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
		descricaoColumn.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());

		showProdutosDados(null);

		produtoTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showProdutosDados(newValue));

	}

	public void setMain(Main main) {
		this.main = main;
		buscaProdutos();
		produtoTable.setItems(produtos);
	}

	private void showProdutosDados(Produto produto) {
		if (produto != null) {
			descricaoLabel.setText(produto.getDescricao());
		} else {
			descricaoLabel.setText("");
		}
	}

	@FXML
	private void novoProduto() {
		Produto auxProduto = new Produto();
		if (this.main.showProdutoDialog(auxProduto)) {
			ProdutoDAO pDao = new ProdutoDAO();
			try {
				pDao.insert(auxProduto);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			buscaProdutos();
		}
	}

	@FXML
	private void editaProduto() {

	}

	@FXML
	private void removeProduto() {

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

}
