package backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeachersInfo {
    public static ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    public static Teacher findTeacherByName(String name)
    {
        Teacher t = null;
        for (Teacher teacher : teachers)
        {
            String tName = teacher.getFirstName() + " " + teacher.getLastName();
            if (tName.equals(name))
            {
                t = teacher;
            }
        }
        return t;
    }
}
