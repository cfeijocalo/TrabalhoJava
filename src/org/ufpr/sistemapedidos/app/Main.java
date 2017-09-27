package org.ufpr.sistemapedidos.app;

import java.io.IOException;

import org.ufpr.sistemapedidos.controller.ClienteDialogController;
import org.ufpr.sistemapedidos.controller.ClienteViewController;
import org.ufpr.sistemapedidos.controller.PedidoDialogController;
import org.ufpr.sistemapedidos.controller.PedidoViewController;
import org.ufpr.sistemapedidos.controller.ProdutoDialogController;
import org.ufpr.sistemapedidos.controller.ProdutoViewController;
import org.ufpr.sistemapedidos.controller.RootLayoutController;
import org.ufpr.sistemapedidos.model.Cliente;
import org.ufpr.sistemapedidos.model.Pedido;
import org.ufpr.sistemapedidos.model.Produto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Caio Calo
 */
public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public Main() {
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Sistema de Pedidos");

		initRootLayout();

		showClienteView();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			RootLayoutController controller = loader.getController();
	        controller.setMain(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// CLIENTE
	public void showClienteView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ClienteView.fxml"));
			AnchorPane clienteView = (AnchorPane) loader.load();

			rootLayout.setCenter(clienteView);

			ClienteViewController cvc = loader.getController();
			cvc.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showClienteDialog(Cliente cliente) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ClienteDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cliente");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			ClienteDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCliente(cliente);

			dialogStage.showAndWait();

			return controller.isConfirm();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// PRODUTO
	public void showProdutoView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ProdutoView.fxml"));
			AnchorPane produtoView = (AnchorPane) loader.load();
			
			rootLayout.setCenter(produtoView);
			
			ProdutoViewController pvc = loader.getController();
			pvc.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean showProdutoDialog(Produto produto) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ProdutoDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Produto");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			ProdutoDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProduto(produto);
			
			dialogStage.showAndWait();
			
			return controller.isConfirm();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	// PEDIDO
	public void showPedidoView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/PedidoView.fxml"));
			AnchorPane pedidoView = (AnchorPane) loader.load();
			
			rootLayout.setCenter(pedidoView);
			
			PedidoViewController pvc = loader.getController();
			pvc.setMain(this);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean showPedidoDialog(Pedido pedido) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/PedidoDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Pedido");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			PedidoDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPedido(pedido);;
			
			dialogStage.showAndWait();
			
			return controller.isConfirm();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	// GETTERS AND SETTERS
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
