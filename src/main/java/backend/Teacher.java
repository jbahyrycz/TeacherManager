package backend;

public class Teacher implements Comparable <Teacher> {
    String firstName, lastName;
    TeacherCondition cond;
    int yearOfBirth;
    double salary;

    public Teacher(String firstName, String lastName, TeacherCondition cond, int yearOfBirth, double salary)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cond = cond;
        this.yearOfBirth = yearOfBirth;
        this.salary = salary;
    }
    public void printing()
    {
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Condition: " + cond);
        System.out.println("Year of birth: " + yearOfBirth);
        System.out.println("Salary: " + salary);
    }
    @Override
    public int compareTo(Teacher other)
    {
        return String.CASE_INSENSITIVE_ORDER.compare(this.lastName+this.firstName, other.lastName+other.firstName);
    }
    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }
    public double getSalary()
    {
        return this.salary;
    }
    public int getYearOfBirth()
    {
        return this.yearOfBirth;
    }
    public TeacherCondition getCond()
    {
        return this.cond;
    }
    public void setFirstName(String firstName)
    {
       this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public void setSalary(double salary)
    {
        this.salary = salary;
    }
    public void setYearOfBirth(int yearOfBirth)
    {
        this.yearOfBirth = yearOfBirth;
    }
    public void setCond(TeacherCondition cond)
    {
        this.cond = cond;
    }
}

