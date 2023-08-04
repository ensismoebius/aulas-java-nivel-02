package org.dedira.caculadora;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VariableConteiner implements IVariableTranslator {

    private final ObservableList<String> variableItens;
    private final ObservableList<String> userVariables;
    private final HashMap<String, Integer> variablesIndex;

    public VariableConteiner() {
        this.variablesIndex = new HashMap<>();
        this.userVariables = FXCollections.observableArrayList();
        this.variableItens = FXCollections.observableArrayList();
    }

    public ObservableList<String> getVariableItens() {
        return userVariables;
    }

    /**
     * Sets the variable numeric value
     *
     * @param name
     * @param value
     */
    @Override
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
        if (variablesIndex.get(variableName) == null) {
            return false;
        }
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
