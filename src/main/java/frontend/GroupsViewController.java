package frontend;

import backend.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GroupsViewController extends BasicController implements Initializable {
    @FXML
    private TableView<Teacher> teachersTable;
    @FXML
    private TableColumn<Teacher, String> firstNameColumn;
    @FXML
    private TableColumn<Teacher, String> lastNameColumn;
    @FXML
    private TableColumn<Teacher, Integer> yearOfBirthColumn;
    @FXML
    private TableColumn<Teacher, Double> salaryColumn;
    @FXML
    private TableColumn<Teacher, TeacherCondition> conditionColumn;
    @FXML
    private ChoiceBox<String> groupNameInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // Configure the table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("lastName"));
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("yearOfBirth"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Double>("salary"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<Teacher, TeacherCondition>("cond"));
        teachersTable.setItems(TeachersInfo.teachers);

        // Configure the choice box
        List<String> groupNamesList = new ArrayList<String>();
        for (String groupName : ContainerInfo.container.groups.keySet())
        {
            groupNamesList.add(groupName);
        }
        ObservableList choices = FXCollections.observableList(groupNamesList);
        groupNameInput.setItems(choices);
    }
}

