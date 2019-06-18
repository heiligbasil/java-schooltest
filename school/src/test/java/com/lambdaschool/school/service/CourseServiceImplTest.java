package com.lambdaschool.school.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.repository.InstructorRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class CourseServiceImplTest
{
    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorRepository instructorRepository;

    private ArrayList<Course> courseList;

    @Before
    public void setUp() throws Exception
    {
        courseList = new ArrayList<>();
        courseList.add(new Course("Science", new Instructor("John")));
        courseList.add(new Course("Social Studies", new Instructor("Chance")));
        courseList.add(new Course("Mathematics", new Instructor("Patrick")));
        courseList.add(new Course("English", new Instructor("Josh")));
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findAll()
    {
    }

    @Test
    public void findCourseById()
    {
        assertEquals("Mobile Android", courseService.findCourseById(6L).getCoursename());
    }

    @Test
    public void getCountStudentsInCourse()
    {
    }

    @Test
    public void deleteFound()
    {
        courseService.delete(1);
        assertEquals(5, courseService.findAll().size());
    }

    @Test (expected = EntityNotFoundException.class)
    public void deleteNotFound()
    {
        courseService.delete(100);
        assertEquals(6, courseService.findAll().size());
    }

    @Test
    public void save()
    {
        //Optional<Instructor> instructor = instructorRepository.findById(1L)/*.findInstructorsByInstructnameEquals("John")*/;
        Course makeCourse = new Course("History", instructorRepository.findById(1L).get());
        makeCourse.getStudents().add(new Student("Suzy"));

        Course addCourse = courseService.save(makeCourse);

        assertNotNull(addCourse);
        Course foundRestaurant = courseService.findCourseById(addCourse.getCourseid());
        assertEquals(addCourse.getCoursename(), foundRestaurant.getCoursename());
    }
}