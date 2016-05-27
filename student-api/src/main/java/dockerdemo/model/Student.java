package dockerdemo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * @author Allan G. Ramirez (aramirez@lingotek.com)
 */
public class Student
{
    private String name;
    private List<Integer> grades = new ArrayList<>();

    public Student(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public List<Integer> getGrades()
    {
        return grades;
    }

    public double getAverage()
    {
        OptionalDouble opt = grades.stream().mapToDouble(d -> d).average();
        return opt.isPresent() ? opt.getAsDouble() : 0.0;
    }

    public void addGrade(int grade)
    {
        grades.add(grade);
    }
}
