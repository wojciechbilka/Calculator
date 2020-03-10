package calculator.logic;

import java.math.BigDecimal;
import java.util.*;

public class Equation {

    private List<StringBuilder> equation;
    private EquationExecutor executor = new EquationExecutor();

    public Equation() {
        equation = new ArrayList<StringBuilder>();
    }

    public StringBuilder getLastElement() {
        if (equation.size() > 0) {
            return equation.get(equation.size() - 1);
        }
        return null;
    }

    public void appendToElement(String item) {
        getLastElement().append(item);
    }

    public void addNewElement(String element) {
        equation.add(new StringBuilder(element));
    }

    public void appendToBeginningOfElement(String item) {
        getLastElement().insert(0, item);
    }

    public StringBuilder getElementBefore(StringBuilder element) {
        int index = equation.indexOf(element);
        if (index > 0) {
            return equation.get(index - 1);
        }
        return null;
    }

    public StringBuilder getOneBeforeLastElement() {
        int index = equation.indexOf(getLastElement());
        if (index > 0) {
            return equation.get(index - 1);
        }
        return null;
    }

    public void removeLastElement() {
        if (equation.size() > 0) {
            equation.remove(equation.size() - 1);
        }
    }

    public void removeLastCharacter() {
        if (equation.size() > 0) {
            getLastElement().deleteCharAt(getLastElement().length() - 1);
        }
    }

    public void deleteAllElements() {
        equation.clear();
    }

    public String buildString() {
        StringBuilder result = new StringBuilder();
        for (StringBuilder temp : equation) {
            result.append(temp);
        }
        return result.toString();
    }

    public void computeResult() {
        executor.computeEquation();
    }

    public List<String> getNumbersList() {
        executor.updateLists();
        return executor.getNumbersList();
    }

    public int getSize() {
        return equation.size();
    }

    public boolean isEmpty() {
        return equation.isEmpty();
    }


    private class EquationExecutor {
        private List<String> numbersList;
        private List<String> operationsList;

        private void updateLists() {
            Iterator<StringBuilder> iterator = equation.iterator();
            List<String> newNumbersList = new ArrayList<>();
            List<String> newOperationsList = new ArrayList<>();
            String element;

            while (iterator.hasNext()) {
                element = iterator.next().toString();
                if (element.matches(".*[0-9]+.*")) {
                    newNumbersList.add(element);
                } else if (element.length() == 1 && element.matches("[-+/*]")) {
                    newOperationsList.add(element);
                }
            }
            numbersList = newNumbersList;
            operationsList = newOperationsList;
        }

        private void computeEquation() {
            updateLists();
            while (operationsList.size() > 0) {
                executeOperation();
            }
            equation.clear();
            equation.add(new StringBuilder(numbersList.get(0)));

        }

        private void executeOperation() {
            int index = getOperationIndex();
            BigDecimal result;
            if(index != -1) {
                switch (operationsList.get(index)) {
                    case "/":
                        calculate(index, MathematicalOperation.DIVIDE);
                        break;
                    case "*":
                        calculate(index, MathematicalOperation.MULTIPLY);
                        break;
                    case "-":
                        calculate(index, MathematicalOperation.SUBTRACT);
                        break;
                    case "+":
                        calculate(index, MathematicalOperation.ADD);
                        break;
                }
            }
        }

        private int getOperationIndex() {
            int index = 0;
            for(String element : operationsList) {
                if(element.matches("[/*]")) {
                    return index;
                }
                index++;
            }
            index = 0;
            for(String element : operationsList) {
                if(element.matches("[-+]")) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        private void calculate(int indexOfOperation, MathematicalOperation operation) {
            BigDecimal result;
            if(numbersList.size() > (indexOfOperation + 1)) {
                result = operation.calculate(new BigDecimal(numbersList.get(indexOfOperation)), new BigDecimal(numbersList.get(indexOfOperation + 1)));
                operationsList.remove(indexOfOperation);
                numbersList.remove(indexOfOperation);
                numbersList.remove(indexOfOperation);
                numbersList.add(indexOfOperation, result.toString());
            }
        }

        private List<String> getNumbersList() {
            return new ArrayList<>(numbersList);
        }

        private List<String> getOperationsList() {
            return new ArrayList<>(operationsList);
        }
    }
}
