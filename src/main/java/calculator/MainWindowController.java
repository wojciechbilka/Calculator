package calculator;

import calculator.logic.ButtonIdentifier;
import calculator.logic.CalcValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainWindowController {

    private final CalcValidator validator = new CalcValidator();

    @FXML
    private Label display;

    @FXML
    private void numberPressed(ActionEvent event) {
        ButtonIdentifier buttonIdentifier = ButtonIdentifier.getButtonIdentifier(getButtonId(event));
        validator.addNumber(buttonIdentifier);
        updateDisplay();
    }
    @FXML
    private void operationPressed(ActionEvent event) {
        ButtonIdentifier buttonIdentifier = ButtonIdentifier.getButtonIdentifier(getButtonId(event));
        validator.addOperation(buttonIdentifier);
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
    private void clearEntry() {
        validator.clearEntry();
        updateDisplay();
    }

    @FXML
    private void clearAll() {
        validator.clearAll();
        updateDisplay();
    }

    @FXML
    private void getResult() {
        validator.calculateResult();
        updateDisplay();
    }

    private String getButtonId(ActionEvent event){
        Button sourceButton = (Button) event.getSource();
        return sourceButton.getId();
    }

    private void updateDisplay() {
        display.setText(validator.getResult());
    }
}
