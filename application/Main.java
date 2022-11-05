package application;

import controllers.EditarMoradorController;
import controllers.CadastrarEmpregadoController;
import controllers.CadastrarMoradorAdController;
import controllers.CadastrarVeiculoController;
import controllers.CadastrarAnimalController;
import controllers.CadastrarContatoController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application {
	
	private static Stage mainStage;
	
	public static void setStage(Stage palco) {
		mainStage = palco;
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Gerenciador Condomínio");
		setStage(primaryStage);
		mainPage();
	}
	
	public static void mainPage() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainPage.fxml"));
			Pane mainPane = loader.load();
			
			Scene cena = new Scene(mainPane);
			
			mainStage.setScene(cena);
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pageCadastrarMorador() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("cadastrarMorador.fxml"));
			Pane mainPane = loader.load();
			
			Scene cena = new Scene(mainPane);
			
			mainStage.setScene(cena);
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pageCadastrarMoradorAdicional(String cpf) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("CadastrarMoradorAd.fxml"));
			Pane mainPane = loader.load();
			
			CadastrarMoradorAdController.setIdCpf(cpf);
			
			Scene cena = new Scene(mainPane);
			
			mainStage.setScene(cena);
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pageEditarMorador_A(String cpf) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditarMorador.fxml"));
			Pane mainPane = loader.load();
			
			EditarMoradorController.setIdCpf(cpf);
			
			Scene cena = new Scene(mainPane);
			
			mainStage.setScene(cena);
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pageCadastrarEmpregado(String cpf) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("CadastrarEmpregado.fxml"));
			Pane mainPane = loader.load();
			
			CadastrarEmpregadoController.setIdCpf(cpf);
			
			Scene cena = new Scene(mainPane);
			
			mainStage.setScene(cena);
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pageCadastrarVeiculo(String cpf) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("CadastrarVeiculo.fxml"));
			Pane mainPane = loader.load();
			
			CadastrarVeiculoController.setIdCpf(cpf);
			
			Scene cena = new Scene(mainPane);
			
			mainStage.setScene(cena);
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pageCadastrarAnimal(String cpf) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("CadastrarAnimal.fxml"));
			Pane mainPane = loader.load();
			
			CadastrarAnimalController.setIdCpf(cpf);
			
			Scene cena = new Scene(mainPane);
			
			mainStage.setScene(cena);
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pageCadastrarContato(String cpf) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("CadastrarContato.fxml"));
			Pane mainPane = loader.load();
			
			CadastrarContatoController.setIdCpf(cpf);
			
			Scene cena = new Scene(mainPane);
			
			mainStage.setScene(cena);
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
