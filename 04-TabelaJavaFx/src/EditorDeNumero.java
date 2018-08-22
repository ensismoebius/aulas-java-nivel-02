import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

public class EditorDeNumero implements Callback<TableColumn<Produto, Integer>, TableCell<Produto, Integer>> {

	@Override
	public TableCell<Produto, Integer> call(TableColumn<Produto, Integer> param) {
		return new CelulaDeInteiro();
	}

	class CelulaDeInteiro extends TableCell<Produto, Integer> {

		private TextField editor;

		@Override
		public void startEdit() {
			super.startEdit();
			this.criarCampoDeTexto();

			setGraphic(this.editor);
			this.editor.setText(getItem().toString());
			this.editor.selectAll();
			setText(null);
		}
		
		@Override
		protected void updateItem(Integer item, boolean empty) {
			super.updateItem(item, empty);
			if(empty) {
				this.setText(null);
				this.setGraphic(null);
			}else {
				
				if(this.isEditing()) {
					this.setText(null);
					this.setGraphic(this.editor);
				}else {
					this.setText(getItem().toString());
					this.setGraphic(null);
				}
			}
		}
		
		@Override
		public void cancelEdit() {
			super.cancelEdit();
			this.setText(getItem().toString());
			this.setGraphic(null);
		}

		private void criarCampoDeTexto() {
			this.editor = new TextField();
			this.editor.setOnKeyReleased(evento -> {

				if (evento.getCode() == KeyCode.ENTER) {
					commitEdit(Integer.parseInt(this.editor.getText()));
				}
			});
		}

	}

}
