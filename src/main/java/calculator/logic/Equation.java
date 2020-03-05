package calculator.logic;

import java.util.ArrayList;
import java.util.List;

public class Equation {

    private List<StringBuilder> equation;

    public Equation() {
        initializeEquation();
    }

    private void initializeEquation() {
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

    public String buildString() {
        StringBuilder result = new StringBuilder();
        for (StringBuilder temp : equation) {
            result.append(temp);
        }
        return result.toString();
    }

    public int getSize() {
       return equation.size();
    }

    public boolean isEmpty() {
       return equation.isEmpty();
    }
}
