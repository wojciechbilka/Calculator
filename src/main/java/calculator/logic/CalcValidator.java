package calculator.logic;

public class CalcValidator {

    private final Equation currentEquation;

    public CalcValidator() {
        this.currentEquation = new Equation();
    }

    public CalcValidator(StringBuilder result, Equation currentEquation) {
        this.currentEquation = currentEquation;
    }

    public String getResult() {
        return currentEquation.buildString();
    }

    public void negate() {
        if (currentEquation.isEmpty()) {
            currentEquation.addNewElement("-");
        } else {
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
                } else {
                    currentEquation.addNewElement("-");
                }
            } else {
                currentEquation.addNewElement("-");
            }
        }
        System.out.println("negate() invoked: " + "last element is: " + currentEquation.getLastElement() + "  list size: " + currentEquation.getSize());
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
        System.out.println("addNumber() invoked: " + "last element is: " + currentEquation.getLastElement() + "  list size: " + currentEquation.getSize());
    }

    private CalcValidatorAction validNumber(ButtonIdentifier btnId) {
        CalcValidatorAction action;
        if (btnId.isNumberButton()) {
            if (currentEquation.isEmpty()) {
                action = CalcValidatorAction.ADD_NEW_ELEMENT;
            } else {
                String lastElement = currentEquation.getLastElement().toString();
                if (isNumber(lastElement)) {
                    if ((lastElement.charAt(0) == '0' || (lastElement.charAt(0) == '-' && lastElement.charAt(1) == '0')) && !lastElement.contains(".")) {
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
        } else {
            action = CalcValidatorAction.NO_ACTION;
            System.out.println("validNumber() invoked: " + action + "ATTENTION! default switch case used!");
            return action;
        }
        System.out.println("validNumber() invoked: " + action);
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
        System.out.println("addOperation() invoked: " + "last element is: " + currentEquation.getLastElement() + "  list size: " + currentEquation.getSize());
    }

    private CalcValidatorAction validOperation(ButtonIdentifier btnId) {
        CalcValidatorAction action;
        if (btnId.isOperationButton()) {
            if (isOperation(currentEquation.getLastElement()) || endsWithComma(currentEquation.getLastElement())) {
                action = CalcValidatorAction.NO_ACTION;
            } else {
                action = CalcValidatorAction.ADD_NEW_ELEMENT;
            }
        } else {
            action = CalcValidatorAction.NO_ACTION;
            System.out.println("validOperation() invoked: " + action + "ATTENTION! default switch case used!");
            return action;
        }
        System.out.println("validOperation() invoked: " + action);
        return action;
    }

    public void clearAll() {
        currentEquation.deleteAllElements();
        System.out.println("clearAll() invoked: " + "last element is: " + currentEquation.getLastElement() + "  list size: " + currentEquation.getSize());
    }

    public void clearEntry() {
        if (isNumber(currentEquation.getLastElement())) {
            currentEquation.removeLastElement();
        }
        System.out.println("clearEntry() invoked: " + "last element is: " + currentEquation.getLastElement() + "  list size: " + currentEquation.getSize());
    }

    public void addComma() {
        if (validComma().equals(CalcValidatorAction.APPEND_TO_ELEMENT)) {
            currentEquation.appendToElement(".");
        }
        System.out.println("addComma() invoked: " + "last element is: " + currentEquation.getLastElement() + "  list size: " + currentEquation.getSize());
    }

    private CalcValidatorAction validComma() {
        CalcValidatorAction action;
        if (isNumber(currentEquation.getLastElement()) && !currentEquation.getLastElement().toString().contains(".")) {
            action = CalcValidatorAction.APPEND_TO_ELEMENT;
        } else {
            action = CalcValidatorAction.NO_ACTION;
        }
        System.out.println("validComma() invoked: " + action);
        return action;
    }

    public void deleteLastCharacter() {
        switch (validDeletion()) {
            case REMOVE_FROM_ELEMENT:
                currentEquation.removeLastCharacter();
                break;
            case REMOVE_ENTIRE_ELEMENT:
                currentEquation.removeLastElement();
                break;
            case NO_ACTION:
                break;
        }
    }

    private CalcValidatorAction validDeletion() {
        CalcValidatorAction action;
        if (currentEquation.getSize() > 0) {
            if (currentEquation.getLastElement().length() > 1) {
                action = CalcValidatorAction.REMOVE_FROM_ELEMENT;
            } else {
                action = CalcValidatorAction.REMOVE_ENTIRE_ELEMENT;
            }
        } else {
            action = CalcValidatorAction.NO_ACTION;
        }
        System.out.println("validDeletion() invoked: " + action);
        return action;
    }

    public void calculateResult() {
        if (validCalculateResult().equals(CalcValidatorAction.CALC_RESULT)) {
            currentEquation.computeResult();
        }
        System.out.println("calculateResult invoked: " + "last element is: " + currentEquation.getLastElement() + "  list size: " + currentEquation.getSize());
    }

    private CalcValidatorAction validCalculateResult() {
        CalcValidatorAction action;
        System.out.println(currentEquation.buildString());
        if (currentEquation.getNumbersList().size() > 1 && !currentEquation.buildString().matches("\\S*[\\/0][^.,]*")) {
            action = CalcValidatorAction.CALC_RESULT;
        } else {
            action = CalcValidatorAction.NO_ACTION;
        }
        System.out.println("validCalculateResult() invoked: " + action);
        return action;
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

    private boolean endsWithComma(StringBuilder element) {
        if (element != null) {
            String stringElement = element.toString();
            if (stringElement.charAt(stringElement.length() - 1) == '.') {
                return true;
            }
        }
        return false;
    }
}
