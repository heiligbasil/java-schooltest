package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/students")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/student/add", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewStudent(@Valid @RequestBody Student newStudent) throws URISyntaxException
    {
        newStudent = studentService.save(newStudent);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{studid}").buildAndExpand(newStudent.getStudid()).toUri();
        responseHeaders.setLocation(newURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
