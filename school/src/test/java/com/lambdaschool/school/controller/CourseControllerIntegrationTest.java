package com.lambdaschool.school.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.repository.InstructorRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private InstructorRepository instructorRepository;

    @Before
    public void setUp() throws Exception
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void listAllCourses() //GET /courses/courses
    {
        given().when().get("/courses/courses").then().time(lessThan(5000L));
    }

    @Test
    public void getCountStudentsInCourses()
    {
    }

    @Test
    public void addNewCourse() throws JsonProcessingException // POST /courses/course/add
    {
        Course makeCourse = new Course("Chemistry", instructorRepository.findById(2L).get());
        makeCourse.setCourseid(57);

        ObjectMapper mapper = new ObjectMapper();
        String constructedJson = mapper.writeValueAsString(makeCourse);

        given().contentType("application/json").body(constructedJson).when().post("/courses/course/add").then().statusCode(201);
    }

    @Test
    public void deleteCourseById()
    {
    }
}