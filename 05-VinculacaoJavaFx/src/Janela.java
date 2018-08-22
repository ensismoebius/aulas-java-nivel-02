import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Janela extends Stage {

	private TextField txtValor1;
	private TextField txtValor2;

	public Janela() {
		this.txtValor1 = new TextField();
		this.txtValor2 = new TextField();

		this.txtValor1.textProperty().bind(this.txtValor2.textProperty());

		VBox layout = new VBox();

		layout.getChildren().addAll(txtValor1, txtValor2);

		Scene cena = new Scene(layout);
		this.setScene(cena);
		this.show();

	}
}
