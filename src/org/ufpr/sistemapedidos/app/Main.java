package org.ufpr.sistemapedidos.app;

import java.io.IOException;

import org.ufpr.sistemapedidos.controller.ClienteDialogController;
import org.ufpr.sistemapedidos.controller.ClienteViewController;
import org.ufpr.sistemapedidos.model.Cliente;

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
			loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showClienteView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/ClienteView.fxml"));
			AnchorPane mainView = (AnchorPane) loader.load();

			rootLayout.setCenter(mainView);

			ClienteViewController cvc = loader.getController();
			cvc.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showClienteDialog(Cliente cliente) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/ClienteDialog.fxml"));
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
