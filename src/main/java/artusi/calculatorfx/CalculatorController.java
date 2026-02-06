package artusi.calculatorfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Espressione;
import model.EspressioneException;
import model.Frazione;

import java.util.Arrays;
import java.util.Objects;

public class CalculatorController {
    @FXML
    private Label result;
    @FXML
    private Button btnDeleteAll;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnPow;
    @FXML
    private Button btnDiv;
    @FXML
    private Button btnSub;
    @FXML
    private Button btnMulti;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEquals;
    @FXML
    private Button btnOpenBracket;
    @FXML
    private Button btnCloseBracket;

    @FXML
    public void removeLast() {
        if (!result.getText().isEmpty()) {
            result.setText(result.getText().substring(0, result.getText().length() - 1));
        }
    }

    @FXML
    public void addChar(String s) {
        if (result.getText().isEmpty()) result.setText("");
        result.setText(result.getText() + s);
    }

    public boolean isPresent() {
        String text = result.getText();
        char lastChar = 0;
        if (!text.isEmpty()) lastChar = text.charAt(text.length() - 1);
        return lastChar == btnAdd.getText().charAt(0) || lastChar == btnDiv.getText().charAt(0)
                || lastChar == btnSub.getText().charAt(0) || lastChar == btnMulti.getText().charAt(0)
                || lastChar == btnPow.getText().charAt(0) || lastChar == btnOpenBracket.getText().charAt(0)
                || lastChar == btnCloseBracket.getText().charAt(0);
    }
    @FXML
    public void equals() {
        addChar(btnEquals.getText());
        try {
            addChar(Espressione.calculate(result.getText()).toString());
        } catch (EspressioneException error) {
            result.setText("ERRORE");
        }
    }
    @FXML
    public void keyPressed(ActionEvent event) {
        if (event.getSource() == btnDeleteAll) {
            clearOutput();
            return;
        }
        if (event.getSource() == btnDelete) {
            removeLast();
            return;
        }
        if (event.getSource() == btnAdd) {
            add();
            return;
        }
        if (event.getSource() == btnSub) {
            sub();
            return;
        }
        if (event.getSource() == btnMulti) {
            multi();
            return;
        }
        if (event.getSource() == btnDiv) {
            div();
            return;
        }
        if (event.getSource() == btnPow) {
            pow();
            return;
        }
        if (event.getSource() == btnEquals) {
            equals();
            return;
        }
        if (event.getSource() == btnOpenBracket) {
            openBrackets();
            return;
        }
        if (event.getSource() == btnCloseBracket) {
            closeBrackets();
            return;
        }
        addChar(((Button) event.getSource()).getText());
    }

    @FXML
    public void clearOutput() {
        result.setText("");
    }

    public void add() {
        if (!isPresent()) result.setText(result.getText() + btnAdd.getText());
    }

    public void sub() {
        if (!isPresent()) result.setText(result.getText() + btnSub.getText());
    }

    public void multi() {
        if (!isPresent()) result.setText(result.getText() + btnMulti.getText());
    }

    public void div() {
        if (!isPresent()) result.setText(result.getText() + btnDiv.getText());
    }

    public void pow() {
        if (!isPresent()) result.setText(result.getText() + btnPow.getText());
    }

    public void openBrackets() {
        if (!isPresent()) result.setText(result.getText() + btnOpenBracket.getText());
    }

    public void closeBrackets() {
        if (!isPresent()) result.setText(result.getText() + btnCloseBracket.getText());
    }
}
