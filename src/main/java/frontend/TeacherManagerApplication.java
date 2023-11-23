package frontend;

import backend.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TeacherManagerApplication extends Application{
    @Override
    public void start(Stage stage) throws IOException
    {
        initializeTeachers();
        initializeContainer();
        initializeGroups();

        Parent root = FXMLLoader.load(getClass().getResource("manager_main_view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void initializeTeachers()
    {
        CommonDataStorage.teachers.add(new Teacher("John1", "Smith1", TeacherCondition.present, 2003, 7000));
        CommonDataStorage.teachers.add(new Teacher("John2", "Smith2", TeacherCondition.present, 2002, 6999.5));
        CommonDataStorage.teachers.add(new Teacher("John3", "Smith3", TeacherCondition.sick, 2002, 2000));
        CommonDataStorage.teachers.add(new Teacher("John4", "Smith4", TeacherCondition.delegation, 2002, 5000));
        CommonDataStorage.teachers.add(new Teacher("John5", "Smith5", TeacherCondition.absent, 2002, 3000));
        CommonDataStorage.teachers.add(new Teacher("John6", "Smith6", TeacherCondition.present, 2001, 4000));
        CommonDataStorage.teachers.add(new Teacher("John7", "Smith7", TeacherCondition.present, 2001, 3000));
        CommonDataStorage.teachers.add(new Teacher("John8", "Smith8", TeacherCondition.present, 2003, 6999));
        CommonDataStorage.teachers.add(new Teacher("John9", "Smith9", TeacherCondition.present, 2002, 6000));
        CommonDataStorage.teachers.add(new Teacher("John10", "Smith10", TeacherCondition.present, 2002, 3700));
    }
    public void initializeContainer()
    {
        CommonDataStorage.container.addClass("Mathematicians", 4);
        CommonDataStorage.container.addClass("English teachers", 10);
    }
    public void initializeGroups()
    {
        try
        {
            for(int i = 0; i < 4; i ++)
            {
                CommonDataStorage.container.groups.get("Mathematicians").addTeacher(CommonDataStorage.teachers.get(i));
            }
            for(int i = 4; i < 6; i ++)
            {
                CommonDataStorage.container.groups.get("English teachers").addTeacher(CommonDataStorage.teachers.get(i));
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public static void main(String[] args) {
        launch();
    }
}
