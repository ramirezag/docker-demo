package dockerdemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dockerdemo.model.Student;
import dockerdemo.model.StudentGradeDto;

/**
 * @author Allan G. Ramirez (ramirezag@gmail.com)
 */
@Controller
@RequestMapping("/student")
public class StudentController
{
    private ConcurrentHashMap<String, Student> studentMap = new ConcurrentHashMap<>();

    @RequestMapping(value = "/grade/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> status() throws Exception
    {
        return new ArrayList<>(studentMap.values());
    }

    @RequestMapping(value = "/grade/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addGrade(@RequestBody StudentGradeDto dto) throws Exception
    {
        Student student = studentMap.get(dto.getName());
        if (student == null)
        {
            student = new Student(dto.getName());
            studentMap.put(dto.getName(), student);
        }
        student.addGrade(dto.getGrade());
    }
}
