package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "studentService")
public class StudentServiceImpl implements StudentService
{
    @Autowired
    private StudentRepository studrepos;

    @Override
    public Student save(Student student)
    {
        Student newStudent = new Student();

        newStudent.setStudname(student.getStudname());

        ArrayList<Course> newCourses = new ArrayList<>();
        for (Course course : student.getCourses())
        {
            newCourses.add(new Course(course.getCoursename()));
        }
        newStudent.setCourses(newCourses);

        return studrepos.save(newStudent);
    }
}
