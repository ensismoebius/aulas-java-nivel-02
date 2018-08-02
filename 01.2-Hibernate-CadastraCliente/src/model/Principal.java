package model;

import javafx.application.Application;
import javafx.stage.Stage;

public class Principal extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BancoDeDados.criaGerenciador();
		new JanelaPrincipal();
	}

}
