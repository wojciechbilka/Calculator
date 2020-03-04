package calculator.logic;

public class CalcValidator {

    private StringBuilder result = new StringBuilder();
    private Character lastChar;

    public CalcValidator() {
    }

    public CalcValidator(StringBuilder result) {
        this.result = result;
    }

    public String getResult() {
        return result.toString();
    }

    public void setResult(String result) {
        this.result = new StringBuilder(result);
    }

    public String negate() {
        if (result.charAt(0) == '-') {
            result.deleteCharAt(0);
        } else {
            result.insert(0, '-');
        }
        return result.toString();
    }

    public void addNumber(ButtonIdentifier btnId) {
        //valid str according to result
        if (validNumber(btnId)) {
            result.append(btnId.getCharacter());
        }
        setLastChar();
    }

    private boolean validNumber(ButtonIdentifier btnId) {
        switch (btnId) {
            case ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE:
                String lastNumber = getLastStringAfterRegex(result.toString(), "[+\\-*/]");
                if (lastNumber.length() == 1 && lastNumber.equals("0")){
                    return false;
                } else {
                    return true;
                }
            default:
                return false;
        }
    }

    public void addOperation(ButtonIdentifier btnId) {
        if (validOperation(btnId)) {
            result.append(btnId.getCharacter());
        }
        setLastChar();
    }

    private boolean validOperation(ButtonIdentifier btnId) {
        switch (btnId) {
            case ADD, SUBTRACT, MULTIPLY, DIVIDE:
                if (lastChar == null || "-+*/,".contains(lastChar.toString())) {
                    return false;
                } else {
                    return true;
                }
            default:
                return false;
        }
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
        String lastNumber = getLastStringAfterRegex(result.toString(), "[+\\-*/]");
        return result.length() - lastNumber.length() - 1;
    }
}
