package frontend;

import backend.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class GroupsViewController extends MainViewController implements Initializable {
    private ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    private List<String> groupNamesList = new ArrayList<String>();
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
    @FXML
    private ChoiceBox<String> addTeacherInput;
    @FXML
    private TextField addGroupNameInput;
    @FXML
    private TextField numOfTeachersInput;
    @FXML
    private TextField searchField;
    @FXML
    private Label errorLabel;
    @FXML
    void searchTeacher(ActionEvent event)
    {
        CommonDataStorage.activeGroup.searchPartial(searchField.getText());
        teachers = FXCollections.observableArrayList();
        teachers.addAll(CommonDataStorage.activeGroup.searchPartial(searchField.getText()));
        teachersTable.setItems(teachers);
    }
    @FXML
    void removeTeacher(ActionEvent event)
    {
        int selectedID = teachersTable.getSelectionModel().getSelectedIndex();
        teachersTable.getItems().remove(selectedID);
        CommonDataStorage.activeGroup.setTeachers(teachersTable.getItems());
    }
    @FXML
    void removeGroup(ActionEvent event) throws IOException
    {
        ClassTeacher groupToRemove = CommonDataStorage.activeGroup;
        int selectedID = groupNameInput.getSelectionModel().getSelectedIndex();
        if (selectedID == 0){
            groupNameInput.setValue(groupNameInput.getItems().get(1));
        }
        else
        {
            groupNameInput.setValue(groupNameInput.getItems().get(0));
        }
        CommonDataStorage.container.removeClass(groupToRemove.getGroupName());
        switchToGroupsView(event);
    }
    @FXML
    void addGroup(ActionEvent event) throws IOException
    {
        String groupName = addGroupNameInput.getText();
        int maxNumOfTeachers = Integer.parseInt(numOfTeachersInput.getText());
        CommonDataStorage.container.addClass(groupName, maxNumOfTeachers);
        switchToGroupsView(event);
    }
    @FXML
    void addTeacher(ActionEvent event) throws IOException
    {
        try
        {
            CommonDataStorage.activeGroup.addTeacher(CommonDataStorage.findTeacherByName(addTeacherInput.getValue()));
            switchToGroupsView(event);
        }
        catch(Exception e)
        {
            errorLabel.setText(e.getMessage());
        }
    }
    public void initializeTeachersChoiceBox()
    {
        List<String> names = new ArrayList<String>();
        for (Teacher teacher : CommonDataStorage.teachers)
        {
            names.add(teacher.getFirstName()+" "+teacher.getLastName());
        }
        ObservableList choices = FXCollections.observableList(names);
        addTeacherInput.setItems(choices);
    }
    public void initializeGroupNames()
    {
        groupNameInput.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2)
            {
                CommonDataStorage.activeGroup = CommonDataStorage.container.groups.get(groupNameInput.getItems().get((Integer) number2));
                teachers = FXCollections.observableList(CommonDataStorage.activeGroup.getTeachers());
                teachersTable.setItems(teachers);
            }
        });
        groupNamesList = new ArrayList<String>();
        for (String groupName : CommonDataStorage.container.groups.keySet())
        {
            groupNamesList.add(groupName);
        }
        ObservableList choices = FXCollections.observableList(groupNamesList);
        groupNameInput.setItems(choices);
        groupNameInput.setValue(groupNameInput.getItems().get(0));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // Configure choice boxes
        initializeGroupNames();
        initializeTeachersChoiceBox();

        // Configure the table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("lastName"));
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("yearOfBirth"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Double>("salary"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<Teacher, TeacherCondition>("cond"));
        teachers = FXCollections.observableList(CommonDataStorage.activeGroup.getTeachers());
        teachersTable.setItems(teachers);
    }
}

