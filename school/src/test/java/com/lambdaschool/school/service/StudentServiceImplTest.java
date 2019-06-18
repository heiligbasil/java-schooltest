package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class StudentServiceImplTest
{
    @Autowired
    private StudentService studentService;

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void save()
    {
        Student makeStudent = new Student("Franklin");

        Student addStudent = studentService.save(makeStudent);

        assertNotNull(addStudent);
        Student foundStudent = studentService.findStudentById(addStudent.getStudid());
        assertEquals(addStudent.getStudname(), foundStudent.getStudname());
    }
}