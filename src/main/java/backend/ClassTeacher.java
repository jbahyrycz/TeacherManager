package backend;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassTeacher {
    String groupName;
    int maxNumOfTeachers;
    List<Teacher> teachers = new ArrayList<>();

    ClassTeacher(String groupName, int maxNumOfTeachers)
    {
        this.groupName = groupName;
        this.maxNumOfTeachers = maxNumOfTeachers;
    }

    public void printTeachers(List<Teacher> teachers)
    {
        for (int i = 0; i < teachers.size(); i++)
        {
            System.out.println(teachers.get(i).firstName + " " + teachers.get(i).lastName + ": " + teachers.get(i).salary);
        }
    }

    public void printTeachers()
    {
        for (int i = 0; i < teachers.size(); i++)
        {
            System.out.println(teachers.get(i).firstName + " " + teachers.get(i).lastName + ": " + teachers.get(i).salary);
        }
    }

    public void addTeacher(Teacher t)
    {
        if (teachers.size() < maxNumOfTeachers)
        {
            for (int i = 0; i < teachers.size(); i++)
            {
                if (t.compareTo(teachers.get(i)) == 0)
                {
                    System.out.println("Teacher named " + t.firstName + " " + t.lastName + " is already assigned to the group: " + groupName);
                    return;
                }
            }
            teachers.add(t);
            System.out.println("Teacher named " + t.firstName + " " + t.lastName + " successfully added to the group: " + groupName);
        }
        else
        {
            System.out.println("The group: " + groupName + " is already full, cannot add a new teacher");
        }
    }

    public void addSalary(Teacher t, double s)
    {
        t.salary+=s;
    }

    public void removeTeacher(Teacher t)
    {
        teachers.remove(t);
        System.out.println("Teacher named " + t.firstName + " " + t.lastName + " removed the group: " + groupName);
    }

    public void changeCondition(Teacher t, TeacherCondition cond)
    {
        t.cond = cond;
    }

    public Teacher search(String firstName, String lastName)
    {
        Teacher searchedTeacher = new Teacher(firstName, lastName, TeacherCondition.absent, 0, 0);
        for (int i = 0; i < teachers.size(); i++)
        {
            if (searchedTeacher.compareTo(teachers.get(i)) == 0)
            {
                System.out.println("Teacher " + firstName + " " + lastName + " found");
                return teachers.get(i);
            }
        }
        System.out.println("Teacher " + firstName + " " + lastName + " not found");
        return null;
    }

    public List<Teacher> searchPartial(String frag)
    {
        List<Teacher> list = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++)
        {
            if (teachers.get(i).firstName.contains(frag) || teachers.get(i).lastName.contains(frag))
            {
                list.add(teachers.get(i));
            }
        }
        return list;
    }

    public int countByCondition(TeacherCondition cond)
    {
        int cnt = 0;
        for (int i = 0; i < teachers.size(); i++)
        {
            if (teachers.get(i).cond == cond)
            {
                cnt++;
            }
        }
        return cnt;
    }

    public void summary()
    {
        for (int i = 0; i < teachers.size(); i++)
        {
            teachers.get(i).printing();
        }
    }

    public List<Teacher> sortByName()
    {
        teachers.sort(null);
        return teachers;
    }

    public List<Teacher> sortBySalary()
    {
        teachers.sort(new SalaryComparatorDescending());
        return teachers;
    }

    public Teacher max()
    {
        return Collections.max(teachers, new SalaryComparatorAscending());
    }

    public List<Teacher> getTeachers()
    {
        return this.teachers;
    }

}

class SalaryComparatorDescending implements java.util.Comparator<Teacher>
{
    @Override
    public int compare(Teacher a, Teacher b)
    {
        return Double.compare(b.salary, a.salary);
    }
}

class SalaryComparatorAscending implements java.util.Comparator<Teacher>
{
    @Override
    public int compare(Teacher a, Teacher b)
    {
        return Double.compare(a.salary, b.salary);
    }
}
