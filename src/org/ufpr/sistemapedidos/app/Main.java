package org.ufpr.sistemapedidos.app;

import java.io.IOException;

import org.ufpr.sistemapedidos.controller.ClienteController;
import org.ufpr.sistemapedidos.model.Cliente;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

	public Main() {
		clientes.add(new Cliente(1, "400000", "Caio", "Calo"));
		clientes.add(new Cliente(2, "500000", "André", "Feijó"));
		clientes.add(new Cliente(3, "600000", "Victor", "Vieira"));
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
			
			ClienteController cc = loader.getController();
			cc.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
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

	public ObservableList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ObservableList<Cliente> clientes) {
		this.clientes = clientes;
	}

}
