package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Student;

public interface StudentService
{
    Student findStudentById(Long studentid);

    Student save(Student student);
}
