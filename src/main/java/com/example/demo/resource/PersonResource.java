package com.example.demo.resource;

import com.example.demo.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("person")
public interface PersonResource {

    @GetMapping
    ResponseEntity<List<Person>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<Person> findPersonById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Person> createPerson(@Valid @RequestBody Person person, HttpServletResponse response);
}
