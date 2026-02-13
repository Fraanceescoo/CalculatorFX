package artusi.calculatorfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Espressione;
import model.EspressioneException;

public class CalculatorController {
    @FXML
    public Label result;
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
    public void keyPressed(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (btn == btnDeleteAll) { clearOutput(); return; }
        if (btn == btnDelete) { removeLast(); return; }
        if (btn == btnEquals) { equals(); return; }
        if (btn == btnAdd || btn == btnSub || btn == btnMulti || btn == btnDiv || btn == btnPow) {
            if (!isLastOperator() && !result.getText().isEmpty()) {
                addChar(btn.getText());
            }
            return;
        }
        if (btn == btnOpenBracket) {
            openBrackets();
            return;
        }
        if (btn == btnCloseBracket) {
            closeBrackets();
            return;
        }
        addChar(btn.getText());
    }

    @FXML
    public void removeLast() {
        if (!result.getText().isEmpty()) {
            result.setText(result.getText().substring(0, result.getText().length() - 1));
        }
    }

    @FXML
    public void clearOutput() {
        result.setText("");
    }

    @FXML
    public void addChar(String s) {
        result.setText(result.getText() + s);
    }

    public boolean isLastOperator() {
        String text = result.getText();
        if (text.isEmpty()) return false;

        char lastChar = text.charAt(text.length() - 1);
        return lastChar == btnAdd.getText().charAt(0) ||
                lastChar == btnSub.getText().charAt(0) ||
                lastChar == btnMulti.getText().charAt(0) ||
                lastChar == btnDiv.getText().charAt(0) ||
                lastChar == btnPow.getText().charAt(0);
    }

    public void equals() {
        addChar(btnEquals.getText());
        try {
            addChar(Espressione.calculate(result.getText()).toString());
        } catch (EspressioneException errore) {
            result.setText(errore.getMessage());
        }
    }

    public void add() {
        if (!isLastOperator()) addChar(btnAdd.getText());
    }

    public void sub() {
        if (!isLastOperator()) addChar(btnSub.getText());
    }

    public void multi() {
        if (!isLastOperator()) addChar(btnMulti.getText());
    }

    public void div() {
        if (!isLastOperator()) addChar(btnDiv.getText());
    }

    public void pow() {
        if (!isLastOperator()) addChar(btnPow.getText());
    }

    public void openBrackets() {
        addChar(btnOpenBracket.getText());
    }

    public void closeBrackets() {
        if (!isLastOperator() && !result.getText().isEmpty()) {
            addChar(btnCloseBracket.getText());
        }
    }
}
