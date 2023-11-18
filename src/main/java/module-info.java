module frontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens frontend to javafx.fxml;
    exports frontend;

    opens backend to javafx.fxml;
    exports backend;
}