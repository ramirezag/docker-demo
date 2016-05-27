package dockerdemo.model;

/**
 * @author Allan G. Ramirez (aramirez@lingotek.com)
 */
public class StudentGradeDto
{
    private String name;
    private int grade;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getGrade()
    {
        return grade;
    }

    public void setGrade(int grade)
    {
        this.grade = grade;
    }
}
