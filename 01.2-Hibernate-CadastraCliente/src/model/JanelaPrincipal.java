package model;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JanelaPrincipal extends Stage {

	private Label lblcpf;
	private Label lblnome;
	private Label lblsobrenome;

	private TextField txtcpf;
	private TextField txtnome;
	private TextField txtsobrenome;

	private Button btnsalvar;

	public JanelaPrincipal() {
		this.lblcpf = new Label("Cpf: ");
		this.lblnome = new Label("Nome: ");
		this.lblsobrenome = new Label("Sobrenome: ");

		this.txtcpf = new TextField();
		this.txtnome = new TextField();
		this.txtsobrenome = new TextField();

		this.btnsalvar = new Button("Salvar");
		this.btnsalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Cliente c = new Cliente();
				c.setCpf(txtcpf.getText());
				c.setNome(txtnome.getText());
				c.setSobrenome(txtsobrenome.getText());

				if (existeCliente(c)) {
					new Alert(AlertType.ERROR, "O cliente já existe!").show();
					return;
				}

				salvaCliente(c);
			}
		});

		GridPane layout = new GridPane();
		layout.add(lblcpf, 0, 0);
		layout.add(txtcpf, 1, 0);

		layout.add(lblnome, 0, 1);
		layout.add(txtnome, 1, 1);

		layout.add(lblsobrenome, 0, 2);
		layout.add(txtsobrenome, 1, 2);

		layout.add(btnsalvar, 0, 3, 2, 1);

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

	private void salvaCliente(Cliente c) {
		EntityManager em = BancoDeDados.criaGerenciador();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.clear();
		em.close();
	}

	private boolean existeCliente(Cliente c) {
		boolean resultado = false;

		EntityManager em = BancoDeDados.criaGerenciador();

		Query q = em.createQuery("from Cliente where cpf=:cpf");
		q.setParameter("cpf", c.getCpf());

		if (q.getResultList().size() > 0) {
			resultado = true;
		}

		em.clear();
		em.close();

		return resultado;
	}
}
