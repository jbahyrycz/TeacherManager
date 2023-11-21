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
        CommonDataStorage.teachers.add(new Teacher("Julia", "Bahyrycz", TeacherCondition.present, 2003, 7000));
        CommonDataStorage.teachers.add(new Teacher("Łukasz", "Gajewski", TeacherCondition.present, 2002, 6999.5));
        CommonDataStorage.teachers.add(new Teacher("Patryk", "Bindas", TeacherCondition.sick, 2002, 2000));
        CommonDataStorage.teachers.add(new Teacher("Jakub", "Baruś", TeacherCondition.delegation, 2002, 5000));
        CommonDataStorage.teachers.add(new Teacher("Michał", "Bartoszek", TeacherCondition.absent, 2002, 3000));
        CommonDataStorage.teachers.add(new Teacher("Jan", "Baran", TeacherCondition.present, 2001, 4000));
        CommonDataStorage.teachers.add(new Teacher("Alan", "Abdi", TeacherCondition.present, 2001, 3000));
        CommonDataStorage.teachers.add(new Teacher("Szymon", "Bołd", TeacherCondition.present, 2003, 6999));
        CommonDataStorage.teachers.add(new Teacher("Karol", "Dziadkowiec", TeacherCondition.present, 2002, 6000));
        CommonDataStorage.teachers.add(new Teacher("Tomasz", "Bulanda", TeacherCondition.present, 2002, 3700));
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
