package calc;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VariableConteiner implements IVariableTranslator {
	private ObservableList<String> variableItens = FXCollections.observableArrayList();
	private HashMap<String, Integer> variablesIndex = new HashMap<String, Integer>();
	private ObservableList<String> userVariables = FXCollections.observableArrayList();

	public ObservableList<String> getVariableItens() {
		return userVariables;
	}

	public void setVariable(String name, String value) {

		if (isVariable(name)) {
			variableItens.set(variablesIndex.get(String.valueOf(name)), value);
			userVariables.set(variablesIndex.get(String.valueOf(name)), name + "=" + value);
			return;
		}

		variableItens.add(value);
		userVariables.add(name + "=" + value);
		variablesIndex.put(String.valueOf(name), variableItens.size() - 1);
	}

	@Override
	public boolean isVariable(String variableName) {
		if (variablesIndex.get(variableName) == null)
			return false;
		return variableItens.get(variablesIndex.get(variableName)) != null;
	}

	@Override
	public String getVariableValue(String variableName) {

		try {
			return variableItens.get(variablesIndex.get(variableName));
		} catch (Exception e) {
			return variableName;
		}
	}

}
