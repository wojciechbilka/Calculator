package calculator.logic;

import java.util.Arrays;
import java.util.List;

public enum ButtonIdentifier {

    ZERO("zero", "0"),
    ONE("one", "1"),
    TWO("two", "2"),
    THREE("three", "3"),
    FOUR("four", "4"),
    FIVE("five", "5"),
    SIX("six", "6"),
    SEVEN("seven", "7"),
    EIGHT("eight", "8"),
    NINE("nine", "9"),
    COMMA("comma", "."),
    ADD("add", "+"),
    SUBTRACT("subtract", "-"),
    MULTIPLY("multiply", "*"),
    DIVIDE("divide", "/"),
    BACKSPACE("backspace", "\b"),
    CLEAR_ALL("clearAll", "c"),
    CLEAR_ENTRY("clearEntry", "c"),
    NEGATE("negate", "\u00B1"),
    EQUALS("equals", "=");

    String str;
    String buttonKeyword;
    static List<String> numberKeywordList = Arrays.asList(ZERO.buttonKeyword, ONE.buttonKeyword, TWO.buttonKeyword, THREE.buttonKeyword, FOUR.buttonKeyword,
            FIVE.buttonKeyword, SIX.buttonKeyword, SEVEN.buttonKeyword, EIGHT.buttonKeyword, NINE.buttonKeyword);
    static List<String> operationKeywordList = Arrays.asList(ADD.buttonKeyword, SUBTRACT.buttonKeyword, MULTIPLY.buttonKeyword, DIVIDE.buttonKeyword);

    ButtonIdentifier(String buttonKeyword, String str) {
        this.buttonKeyword = buttonKeyword;
        this.str = str;
    }

    public String getString() {
        return str;
    }

    public static ButtonIdentifier getButtonIdentifier(String id) {
        for (ButtonIdentifier b : ButtonIdentifier.values()) {
            if (b.buttonKeyword.equals(id)) {
                return b;
            }
        }
        return null;
    }

    public boolean isNumberButton() {
        return (this != null && numberKeywordList.contains(this.buttonKeyword));
    }

    public boolean isOperationButton() {
        return (this != null && operationKeywordList.contains(this.buttonKeyword));
    }
}
