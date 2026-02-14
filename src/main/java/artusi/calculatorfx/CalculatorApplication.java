package artusi.calculatorfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculatorApplication.class.getResource("calculator-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 670);
        CalculatorController controller = fxmlLoader.getController();
        stage.setTitle("Calcolatrice");
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case SHIFT /*ENTER*/ -> controller.equals();
                case ESCAPE -> controller.clearOutput();
                case BACK_SPACE -> controller.removeLast();
                case OPEN_BRACKET -> controller.addChar("(");
                case CLOSE_BRACKET -> controller.addChar(")");
                case PLUS, ADD -> controller.add();
                case MINUS, SUBTRACT -> controller.sub();
                case MULTIPLY -> controller.multi();
                case DIVIDE -> controller.div();
                case DIGIT0, NUMPAD0,
                     DIGIT1, NUMPAD1,
                     DIGIT2, NUMPAD2,
                     DIGIT3, NUMPAD3,
                     DIGIT4, NUMPAD4,
                     DIGIT5, NUMPAD5,
                     DIGIT6, NUMPAD6,
                     DIGIT7, NUMPAD7,
                     DIGIT8, NUMPAD8,
                     DIGIT9, NUMPAD9 -> controller.addChar(keyEvent.getText());
            };
        });
    }
    public static void main(String[] args) {
        launch();
    }
}