package backend;
import java.util.*;

public class ClassContainer
{
    public Map<String, ClassTeacher> groups = new HashMap<>();

    public ClassTeacher addClass(String groupName, int maxNumOfTeachers)
    {
        ClassTeacher newGroup = new ClassTeacher(groupName, maxNumOfTeachers);
        newGroup.summary();
        groups.put(groupName, newGroup);
        return newGroup;
    }

    public void removeClass(String groupName)
    {
        groups.remove(groupName);
    }

    public List<ClassTeacher> findEmpty()
    {
        List<ClassTeacher> list = new ArrayList<>();
        for (String key : groups.keySet())
        {
            if (groups.get(key).teachers.isEmpty())
            {
                list.add(groups.get(key));
            }
        }
        return list;
    }

    public void summary()
    {
        for (String groupName : groups.keySet())
        {
            double capacity = (double)groups.get(groupName).maxNumOfTeachers;
            double filling = (double)groups.get(groupName).teachers.size();
            double result = 0.0;
            if (capacity == 0.0)
            {
                result = 100;
            }
            else if (filling == 0.0)
            {
                result = 0.0;
            }
            else
            {
                result = (filling/capacity)*100.0;
            }
            System.out.println(groupName + " is filled in " + result + "%");
        }
    }

}
