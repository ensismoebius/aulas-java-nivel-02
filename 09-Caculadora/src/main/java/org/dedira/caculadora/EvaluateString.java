package org.dedira.caculadora;

import java.util.Stack;

/**
 * Evaluate math expressions
 *
 * @author ensis
 *
 */
public class EvaluateString {

    /**
     * Translate the variable name to his value
     */
    private static IVariableTranslator variableTranslator;

    /**
     * Evaluate given expression and returns the result
     *
     * @param expression
     * @return
     */
    public static String evaluate(String expression) {

        // Ignores empty expressions
        if (expression.trim().isEmpty()) {
            return "";
        }

        // An array of tokens
        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<String> values = new Stack<String>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        // Cicles over all tokens in expression
        for (int i = 0; i < tokens.length; i++) {
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ') {
                continue;
            }

            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9' || Character.isAlphabetic(tokens[i])) {

                // There may be more than one digits in number
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length && (isPartOfANumber(tokens[i]) || isPartOfAVariable(tokens[i]))) {
                    sbuf.append(tokens[i++]);
                }

                // After number has been built push it into the numbers stack
                values.push(sbuf.toString());

                // We must subtract 1 at the index because the next
                // interaction will add 1, doing this we can keep track
                // of the tokens
                i--;

                // After build the number goto to next char/iteration
                continue;
            }

            // Current token is an opening brace, push it to 'ops'
            if (tokens[i] == '(') {
                ops.push(tokens[i]);
                continue;
            }

            // Closing brace encountered, solve entire brace
            if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
                continue;
            }

            // Current token is an operator.
            if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '=') {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
                }

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty()) {
            values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
        }

        // Top of 'values' contains result, return it
        return values.pop();
    }

    private static boolean isPartOfAVariable(char c) {

        return Character.isAlphabetic(c);
    }

    /**
     * Verify if a char may be a part of a number
     *
     * @param c
     * @return true if is a part of a number, false if its not
     */
    private static boolean isPartOfANumber(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        if (c == '.') {
            return true;
        }
        return false;
    }

    /**
     * Returns true if 'op2' has higher or same precedence as 'op1', otherwise
     * returns false.
     *
     * @param op1
     * @param op2
     * @return
     */
    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '=') {
            return false;
        }

        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * A utility method to apply an operator 'op' on operands 'a' and 'b'.
     * Return the result
     *
     * @param operation
     * @param secondNumber
     * @param firstNumber
     * @return
     */
    private static String applyOperation(char operation, String valueTwo, String valueOne) {

        boolean isValueOneNumeric = isNumber(valueOne);
        boolean isValueTwoNumeric = isNumber(valueTwo);

        double firstNumber = 0;
        double secondNumber = 0;

        if (operation != '=') {
            if (variableTranslator.isVariable(valueOne) || isValueOneNumeric) {
                firstNumber = isValueOneNumeric ? Double.parseDouble(valueOne)
                        : Double.parseDouble(variableTranslator.getVariableValue(valueOne));
            }
        }

        if (variableTranslator.isVariable(valueTwo) || isValueTwoNumeric) {

            secondNumber = isValueTwoNumeric ? Double.parseDouble(valueTwo)
                    : Double.parseDouble(variableTranslator.getVariableValue(valueTwo));
        }

        if (operation == '=') {
            valueTwo = variableTranslator.getVariableValue(valueTwo);
        }

        double result = 0;

        switch (operation) {
            case '+':
                result = firstNumber + secondNumber;
                break;
            case '-':
                result = firstNumber - secondNumber;
                break;
            case '*':
                result = firstNumber * secondNumber;
                break;
            case '=':
                variableTranslator.setVariable(valueOne, valueTwo);
                result = 0;
                break;
            case '/':
                if (secondNumber == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                result = firstNumber / secondNumber;
        }
        return String.valueOf(result);
    }

    public static void setVariableTranslator(IVariableTranslator t) {
        variableTranslator = t;
    }
}
