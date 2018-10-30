import javafx.concurrent.Task;
import javafx.event.EventHandler;import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Window extends Stage {
	public Window() {
		
		HBox h = new HBox();
		Button b = new Button("Teste");

		Tooltip t = new Tooltip();
		t.setText("Olá mundo!!");

		b.setTooltip(t);

		h.getChildren().add(b);

		Scene cena = new Scene(h);

		cena.setOnKeyTyped(evento -> {
		});
		
		this.setScene(cena);

		this.show();

		Task task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				while (true) {
					System.out.println("Olá!!!");
					Thread.sleep(2000);
				}
			}
		};

		Thread thr = new Thread(task);
		thr.setDaemon(false);
		thr.start();

	}
}
