package principal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Dependente;

public class AddDependente extends Stage {

	private Label lblNomeDependente;
	private TextField txtNome;
	private TextField txtMatricula;
	private Button btnSalvar;

	public AddDependente() {
		this.lblNomeDependente = new Label("Nome: ");
		this.txtNome = new TextField();
		this.txtMatricula = new TextField();
		this.btnSalvar = new Button("Salvar");
		this.btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Dependente d = new Dependente();
				d.setNome(txtNome.getText());
				d.setMatricula(Integer.parseInt(txtMatricula.getText()));
				
				ArmazenadorDeDependentesTemporario.addDependente(d);
				new Alert(AlertType.INFORMATION, "Adicionado com sucesso!").show();
				AddDependente.this.close();
			}
		});

		VBox layout = new VBox(10);
		layout.getChildren().add(this.lblNomeDependente);
		layout.getChildren().add(this.txtNome);
		layout.getChildren().add(this.txtMatricula);
		layout.getChildren().add(this.btnSalvar);

		Scene cena = new Scene(layout);
		this.setScene(cena);
	}
}
