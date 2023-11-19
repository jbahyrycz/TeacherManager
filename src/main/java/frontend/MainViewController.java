package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void switchToMainView(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("manager_main_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToTeachersView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("manager_teachers_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToGroupsView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("manager_groups_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
