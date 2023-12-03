package com.example.demo.resource.impl;

import com.example.demo.event.CreatedResourceEvent;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.resource.PersonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("person")
public class PersonResourceController implements PersonResource {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<Person> findPersonById(Long id) {
        Optional<Person> personOptional = repository.findById(id);

        return personOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Person> createPerson(@Valid Person person, HttpServletResponse response) {
        Person savedPerson = repository.save(person);

        eventPublisher.publishEvent(new CreatedResourceEvent(this, response, savedPerson.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @Override
    public void deletePerson(Long id) {
        repository.deleteById(id);
    }
}

