package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Dependente;
import model.Responsavel;

public class JanelaPrincipal extends Stage {

	private Label lblNomeResponsavel;
	private TextField txtNome;
	private Button btnAdd;
	private Button btnSalvar;
	private AddDependente janelaDependente;

	public JanelaPrincipal() {

		this.janelaDependente = new AddDependente();

		this.lblNomeResponsavel = new Label("Nome: ");
		this.txtNome = new TextField();
		this.btnSalvar = new Button("Salvar");
		this.btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Responsavel r = new Responsavel();
				r.setNome(txtNome.getText());
				r.setDependentes(ArmazenadorDeDependentesTemporario.getLista());

				EntityManager gerenciador = BancoDeDados.criarGerenciador();

				gerenciador.getTransaction().begin();
				gerenciador.persist(r);
				gerenciador.getTransaction().commit();
				gerenciador.clear();
				gerenciador.close();
				
				ArmazenadorDeDependentesTemporario.limpar();
			}
		});

		this.btnAdd = new Button("+ Dependente");
		this.btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				JanelaPrincipal.this.janelaDependente.show();
			}
		});

		VBox layout = new VBox(10);
		layout.getChildren().add(this.lblNomeResponsavel);
		layout.getChildren().add(this.txtNome);
		layout.getChildren().add(this.btnAdd);
		layout.getChildren().add(this.btnSalvar);

		Scene cena = new Scene(layout);
		this.setScene(cena);
		this.show();
		
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				BancoDeDados.fechar();
				
			}
		});
		
	}
}
