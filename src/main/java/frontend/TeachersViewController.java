package frontend;

import backend.Teacher;
import backend.TeacherCondition;
import backend.CommonDataStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeachersViewController extends MainViewController implements Initializable{
    private boolean modifying = false;
    private int selectedID = -1;
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
    void addTeacherSubmit(ActionEvent event) throws IOException
    {
        if (modifying)
        {
            CommonDataStorage.teachers.get(selectedID).setFirstName(firstNameInput.getText());
            CommonDataStorage.teachers.get(selectedID).setLastName(lastNameInput.getText());
            CommonDataStorage.teachers.get(selectedID).setCond(getTeacherCondFromString(conditionInput.getValue()));
            CommonDataStorage.teachers.get(selectedID).setYearOfBirth(Integer.parseInt(yearOfBirthInput.getText()));
            CommonDataStorage.teachers.get(selectedID).setSalary(Double.parseDouble(salaryInput.getText()));
            selectedID = -1;
            modifying = false;
        }
        else
        {
            Teacher teacher = new Teacher(firstNameInput.getText(),
                    lastNameInput.getText(),
                    getTeacherCondFromString(conditionInput.getValue()),
                    Integer.parseInt(yearOfBirthInput.getText()),
                    Double.parseDouble(salaryInput.getText()));
            CommonDataStorage.teachers.add(teacher);
        }
        teachersTable.setItems(CommonDataStorage.teachers);
        switchToTeachersView(event);
    }
    @FXML
    void removeTeacher(ActionEvent event)
    {
        selectedID = teachersTable.getSelectionModel().getSelectedIndex();
        teachersTable.getItems().remove(selectedID);
        CommonDataStorage.teachers = teachersTable.getItems();
    }
    @FXML
    void modifyTeachersData(ActionEvent event)
    {
        modifying = true;
        selectedID = teachersTable.getSelectionModel().getSelectedIndex();
        Teacher teacher = CommonDataStorage.teachers.get(selectedID);
        firstNameInput.setText(teacher.getFirstName());
        lastNameInput.setText(teacher.getLastName());
        conditionInput.setValue(getStringFromTeacherCondition(teacher.getCond()));
        yearOfBirthInput.setText(Integer.toString(teacher.getYearOfBirth()));
        salaryInput.setText(Double.toString(teacher.getSalary()));
        CommonDataStorage.teachers = teachersTable.getItems();
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
        teachersTable.setItems(CommonDataStorage.teachers);

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
