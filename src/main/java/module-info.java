module artusi.calculatorfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens artusi.calculatorfx to javafx.fxml;
    exports artusi.calculatorfx;
}