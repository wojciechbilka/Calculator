package calculator.logic;

import java.util.ArrayList;

public class CalcValidator {

    private StringBuilder result = new StringBuilder();
    private Character lastChar;
    private Equation currentEquation;


    public CalcValidator() {
        this.currentEquation = new Equation();
    }

    public CalcValidator(StringBuilder result, Equation currentEquation) {
        this.result = result;
        this.currentEquation = currentEquation;
    }

    public String getResult() {
        return currentEquation.buildString();
    }

    public void negate() {
        if (!currentEquation.isEmpty()) {
            String lastElement = currentEquation.getLastElement().toString();
            if (isNumber(lastElement)) {
                if (lastElement.contains("-")) {
                    currentEquation.getLastElement().deleteCharAt(0);
                } else {
                    currentEquation.appendToBeginningOfElement("-");
                }
            } else if (isOperation(lastElement)) {
                if (lastElement.contains("-")) {
                    if (isNumber(currentEquation.getOneBeforeLastElement())) {
                        currentEquation.addNewElement("-");
                    } else {
                        currentEquation.removeLastElement();
                    }
                }
            } else {
                currentEquation.addNewElement("-");
            }
        } else {
            currentEquation.addNewElement("-");
        }
        System.out.println("negate() invoked: " + currentEquation.getLastElement() + "   " + currentEquation.getSize());
    }

    public void addNumber(ButtonIdentifier btnId) {
        switch (validNumber(btnId)) {
            case NO_ACTION:
                break;
            case APPEND_TO_ELEMENT:
                currentEquation.appendToElement(btnId.getString());
                break;
            case ADD_NEW_ELEMENT:
                currentEquation.addNewElement(btnId.getString());
                break;
        }
    }

    private CalcValidatorAction validNumber(ButtonIdentifier btnId) {
        CalcValidatorAction action;
        switch (btnId) {
            case ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE:
                if (currentEquation.isEmpty()) {
                    action = CalcValidatorAction.ADD_NEW_ELEMENT;
                } else {
                    String lastElement = currentEquation.getLastElement().toString();
                    if (isNumber(lastElement)) {
                        if (lastElement.charAt(0) == '0' && !lastElement.contains(",")) {
                            action = CalcValidatorAction.NO_ACTION;
                        } else {
                            action = CalcValidatorAction.APPEND_TO_ELEMENT;
                        }
                    } else if (isOperation(lastElement) && lastElement.contains("-")) {
                        if (isNumber(currentEquation.getOneBeforeLastElement())) {
                            action = CalcValidatorAction.ADD_NEW_ELEMENT;
                        } else {
                            action = CalcValidatorAction.APPEND_TO_ELEMENT;
                        }
                    } else {
                        action = CalcValidatorAction.ADD_NEW_ELEMENT;
                    }
                }
                break;
            default:
                action = CalcValidatorAction.NO_ACTION;
                System.out.println("validNumber() invoked : " + action + "ATTENTION! default switch case used!");
                return action;
        }
        System.out.println("validNumber() invoked : " + action);
        return action;
    }

    public void addOperation(ButtonIdentifier btnId) {
        switch (validOperation(btnId)) {
            case NO_ACTION:
                break;
            case APPEND_TO_ELEMENT:
                currentEquation.appendToElement(btnId.getString());
                break;
            case ADD_NEW_ELEMENT:
                currentEquation.addNewElement(btnId.getString());
                break;
        }
    }

    private CalcValidatorAction validOperation(ButtonIdentifier btnId) {
        CalcValidatorAction action;
        switch (btnId) {
            case ADD, SUBTRACT, MULTIPLY, DIVIDE:
                if (isOperation(currentEquation.getLastElement())) {
                    action = CalcValidatorAction.NO_ACTION;
                } else {
                    action = CalcValidatorAction.ADD_NEW_ELEMENT;
                }
                break;
            default:
                action = CalcValidatorAction.NO_ACTION;
                System.out.println("validOperation() invoked : " + action + "ATTENTION! default switch case used!");
                return action;
        }
        System.out.println("validOperaion() invoked : " + action);
        return action;
    }

    public void clearAll() {
        result.delete(0, result.length());
    }

    public void clearEquation() {
        if (validClearEquation()) {
            result.delete(getLastNumberBeginningIndex() + 1, result.length());
        }
    }

    private boolean validClearEquation() {
        if (lastChar != null && result.length() > 0 && !"+-/*".contains(lastChar.toString())) {
            String lastNumber = getLastStringAfterRegex(result.toString(), "[+\\-*/]");
            if (lastNumber.matches("[0-9]+.*")) {
                return true;
            }
        }
        return false;
    }

    public void addComma() {
        if (validComma()) {
            result.append(',');
        }
        setLastChar();
    }

    private boolean validComma() {
        if (lastChar != null && result.length() > 0) {
            String lastNumber = getLastStringAfterRegex(result.toString(), "[+\\-*/]");
            if (lastNumber.matches("[0-9]+")) {
                return !lastNumber.contains(",");
            }
        }
        return false;
    }

    public void deleteLastCharacter() {
        if (validDeletion()) {
            result.deleteCharAt(result.length() - 1);
            setLastChar();
        }
    }

    private boolean validDeletion() {
        if (result.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setLastChar() {
        if (result.length() > 0) {
            lastChar = result.charAt(result.length() - 1);
        } else {
            lastChar = null;
        }
    }

    private String getLastStringAfterRegex(String inputString, String regex) {
        String[] array = inputString.split(regex);
        return array[array.length - 1];
    }

    private int getLastNumberBeginningIndex() {
        String lastNumber = getLastStringAfterRegex(result.toString(), "[-+*/]");
        return result.length() - lastNumber.length() - 1;
    }

    private boolean isNumber(String element) {
        if (element != null && element.matches(".*[0-9]+.*")) {
            return true;
        }
        return false;
    }

    private boolean isNumber(StringBuilder element) {
        if (element != null) {
            if (element.toString().matches(".*[0-9]+.*")) {
                return true;
            }
        }
        return false;
    }

    private boolean isOperation(String element) {
        if (element != null && element.length() == 1 && element.matches("[-+/*]")) {
            return true;
        }
        return false;
    }

    private boolean isOperation(StringBuilder element) {
        if (element != null) {
            if (element.toString().length() == 1 && element.toString().matches("[-+/*]")) {
                return true;
            }
        }
        return false;
    }
}
