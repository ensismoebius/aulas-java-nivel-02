package org.dedira.caculadora;

public interface IVariableTranslator {

    /**
     * Returns if a name is a variable or not
     *
     * @param variableName
     * @return
     */
    public boolean isVariable(String variableName);

    /**
     * Returns the variable numeric value if any othewise return the variable
     * itself
     *
     * @param variableName
     * @return
     */
    public String getVariableValue(String variableName);

    /**
     * Sets the variable numeric value
     *
     * @param name
     * @param value
     */
    public void setVariable(String name, String value);
}
