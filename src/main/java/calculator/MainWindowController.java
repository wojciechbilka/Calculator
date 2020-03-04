package calculator;

import calculator.logic.ButtonIdentifier;
import calculator.logic.CalcValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindowController {

    private final CalcValidator validator = new CalcValidator();
    private Map<String, ButtonIdentifier> buttonMap = initializeButtonsMap();

    @FXML
    private Label display;
    @FXML
    private Button change_text;
    public Button backspace;

    @FXML
    private void numberPressed(ActionEvent event) {
        validator.addNumber(getButtonIdentifier(event));
        updateDisplay();
    }
    @FXML
    private void operationPressed(ActionEvent event) {
        validator.addOperation(getButtonIdentifier(event));
        updateDisplay();
    }

    @FXML
    private void commaPressed(ActionEvent event) {
        validator.addComma();
        updateDisplay();
    }

    @FXML
    private void negate() {
        validator.negate();
        updateDisplay();
    }

    @FXML
    private void delete() {
        validator.deleteLastCharacter();
        updateDisplay();
    }

    @FXML
    private void clear() {
        validator.clearEquation();
        updateDisplay();
    }

    @FXML
    private void clearAll() {
        validator.clearAll();
        updateDisplay();
    }

    private ButtonIdentifier getButtonIdentifier(ActionEvent event){
        Button sourceButton = (Button) event.getSource();
        return buttonMap.get(sourceButton.getId());
    }

    private void updateDisplay() {
        display.setText(validator.getResult());
    }

    private Map<String, ButtonIdentifier> initializeButtonsMap() {
        Map<String, ButtonIdentifier> map = new HashMap<>();
        map.put("zero", ButtonIdentifier.ZERO);
        map.put("one", ButtonIdentifier.ONE);
        map.put("two", ButtonIdentifier.TWO);
        map.put("three", ButtonIdentifier.THREE);
        map.put("four", ButtonIdentifier.FOUR);
        map.put("five", ButtonIdentifier.FIVE);
        map.put("six", ButtonIdentifier.SIX);
        map.put("seven", ButtonIdentifier.SEVEN);
        map.put("eight", ButtonIdentifier.EIGHT);
        map.put("nine", ButtonIdentifier.NINE);
        map.put("comma", ButtonIdentifier.COMMA);
        map.put("add", ButtonIdentifier.ADD);
        map.put("subtract", ButtonIdentifier.SUBTRACT);
        map.put("multiply", ButtonIdentifier.MULTIPLY);
        map.put("divide", ButtonIdentifier.DIVIDE);
        map.put("backspace", ButtonIdentifier.BACKSPACE);
        map.put("clearAll", ButtonIdentifier.CLEAR_ALL);
        map.put("clear", ButtonIdentifier.CLEAR);
        map.put("negate", ButtonIdentifier.NEGATE);
        map.put("equals", ButtonIdentifier.EQUALS);
        return map;
    }
}
