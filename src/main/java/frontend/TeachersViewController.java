package frontend;

import backend.Teacher;
import backend.TeacherCondition;
import backend.TeachersInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeachersViewController extends BasicController implements Initializable{
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
    private TextField firstNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField yearOfBirthInput;
    @FXML
    private TextField salaryInput;
    @FXML
    private ChoiceBox<String> conditionInput;
    @FXML
    void addTeacherSubmit(ActionEvent event)
    {
        String condStr = conditionInput.getValue();
        Teacher teacher = new Teacher(firstNameInput.getText(),
                lastNameInput.getText(),
                getTeacherCondFromString(condStr),
                Integer.parseInt(yearOfBirthInput.getText()),
                Double.parseDouble(salaryInput.getText()));
        TeachersInfo.teachers.add(teacher);
        teachersTable.setItems(TeachersInfo.teachers);
    }
    @FXML
    void removeTeacher(ActionEvent event)
    {
        int selectedID = teachersTable.getSelectionModel().getSelectedIndex();
        teachersTable.getItems().remove(selectedID);
        TeachersInfo.teachers = teachersTable.getItems();
    }
    @FXML
    void modifyTeachersData(ActionEvent event)
    {
        int selectedID = teachersTable.getSelectionModel().getSelectedIndex();
        Teacher teacher = TeachersInfo.teachers.get(selectedID);
        firstNameInput.setText(teacher.getFirstName());
        lastNameInput.setText(teacher.getLastName());
        conditionInput.setValue(getStringFromTeacherCondition(teacher.getCond()));
        yearOfBirthInput.setText(Integer.toString(teacher.getYearOfBirth()));
        salaryInput.setText(Double.toString(teacher.getSalary()));
        teachersTable.getItems().remove(selectedID);
        TeachersInfo.teachers = teachersTable.getItems();
    }
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
        List<String> choicesList = new ArrayList<String>();
        choicesList.add(getStringFromTeacherCondition(TeacherCondition.present));
        choicesList.add(getStringFromTeacherCondition(TeacherCondition.delegation));
        choicesList.add(getStringFromTeacherCondition(TeacherCondition.sick));
        choicesList.add(getStringFromTeacherCondition(TeacherCondition.absent));
        ObservableList choices = FXCollections.observableList(choicesList);
        conditionInput.setItems(choices);
    }
    public TeacherCondition getTeacherCondFromString(String condStr)
    {
        TeacherCondition cond = TeacherCondition.present;
        switch(condStr)
        {
            case "present":
                cond = TeacherCondition.present;
                break;
            case "delegation":
                cond = TeacherCondition.delegation;
                break;
            case "sick":
                cond = TeacherCondition.sick;
                break;
            case "absent":
                cond = TeacherCondition.absent;
                break;
        }
        return cond;
    }
    public String getStringFromTeacherCondition(TeacherCondition cond)
    {
        String condStr = "";
        switch(cond)
        {
            case present:
                condStr = "present";
                break;
            case delegation:
                condStr = "delegation";
                break;
            case sick:
                condStr = "sick";
                break;
            case absent:
                condStr = "absent";
                break;
        }
        return condStr;
    }
}
