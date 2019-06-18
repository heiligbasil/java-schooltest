package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
public class CourseControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

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
    public void listAllCourses() throws Exception
    {
        String apiUrl = "/courses/courses";

        Mockito.when(courseService.findAll()).thenReturn(courseList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void getCountStudentsInCourses()
    {
    }

    @Test
    public void addNewCourse() throws Exception
    {
        String apiUrl = "/courses/course/add";

        Course makeCourse = new Course("History", new Instructor("Don Quixote"));
        makeCourse.setCourseid(33);
        ObjectMapper mapper = new ObjectMapper();
        String constructedJson = mapper.writeValueAsString(makeCourse);

        Mockito.when(courseService.save(any(Course.class))).thenReturn(makeCourse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(apiUrl).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(constructedJson);

        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteCourseById()
    {
    }
}