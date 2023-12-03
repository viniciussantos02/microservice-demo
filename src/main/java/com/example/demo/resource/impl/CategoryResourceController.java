package com.example.demo.resource.impl;

import com.example.demo.event.CreatedResourceEvent;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.resource.CategoryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryResourceController implements CategoryResource {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public ResponseEntity<List<Category>> listAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        Optional<Category> categoryOptional = repository.findById(id);

        return categoryOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category, HttpServletResponse response) {
        Category savedCategory = repository.save(category);

        eventPublisher.publishEvent(new CreatedResourceEvent(this, response, savedCategory.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }
}
