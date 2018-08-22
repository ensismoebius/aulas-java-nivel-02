import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Janela extends Stage {

	private ObservableList<Produto> listaDeProduto;
	private TableView<Produto> tabela;
	private Button btnAdd;
	private VBox layout;

	public Janela() {
		this.listaDeProduto = FXCollections.observableArrayList();
		this.tabela = new TableView<Produto>();
		this.btnAdd = new Button("Adicionar");
		this.layout = new VBox();

		TableColumn<Produto, Integer> cod = new TableColumn<Produto, Integer>("Código");
		TableColumn<Produto, String> nome = new TableColumn<Produto, String>("Nome");
		TableColumn<Produto, String> descr = new TableColumn<Produto, String>("Descrição");

		this.tabela.getColumns().addAll(cod, nome, descr);
		this.tabela.setItems(this.listaDeProduto);

		cod.setCellValueFactory(new PropertyValueFactory("cod"));
		nome.setCellValueFactory(new PropertyValueFactory("nome"));
		descr.setCellValueFactory(new PropertyValueFactory("descricao"));

		cod.setCellFactory(new EditorDeNumero());

		this.btnAdd.setOnAction(evento -> {

			Produto p = new Produto();
			p.setCod((int) (Math.random() * 10));
			p.setNome("Uva");
			p.setDescricao("Uva passa");

			this.listaDeProduto.add(p);

		});

		this.layout.getChildren().addAll(tabela, btnAdd);
		Scene cena = new Scene(layout);
		this.setScene(cena);
		this.show();
	}

}
