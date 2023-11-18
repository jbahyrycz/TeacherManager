module com.example.teachersgroups {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.teachersgroups to javafx.fxml;
    exports com.example.teachersgroups;
}