package org.dedira.caculadora;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculatorWindow extends Stage {

    private TextArea txtExpression;

    private final ListView<String> lstVariables;

    private final VariableConteiner variableContainer;

    public CalculatorWindow() {
        this.setTitle("Calculadora");

        this.variableContainer = new VariableConteiner();

        this.txtExpression = new TextArea();
        this.txtExpression.prefWidthProperty().bind(this.widthProperty().divide(4).multiply(3));

        this.lstVariables = new ListView<>(this.variableContainer.getVariableItens());
        this.lstVariables.prefWidthProperty().bind(this.widthProperty().divide(4).multiply(1));

        EvaluateString.setVariableTranslator(this.variableContainer);

        this.txtExpression.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                String finalText = txtExpression.getText();
                String[] lines = finalText.split("\n");
                txtExpression.appendText("\n" + String.valueOf(EvaluateString.evaluate(lines[lines.length - 1])));
            }
        });

        GridPane gp = new GridPane();
        gp.add(txtExpression, 0, 0);
        gp.add(lstVariables, 1, 0);

        Scene scene = new Scene(gp);
        scene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());

        setScene(scene);
        show();
    }
}
