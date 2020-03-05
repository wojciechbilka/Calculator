package calculator.logic;

public enum ButtonIdentifier {

    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    COMMA(","),
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    BACKSPACE("\b"),
    CLEAR_ALL("c"),
    CLEAR("c"),
    NEGATE("\u00B1"),
    EQUALS("=");


    String str;

    ButtonIdentifier(String str) {
        this.str = str;
    }

    public String getString() {
        return str;
    }

}
