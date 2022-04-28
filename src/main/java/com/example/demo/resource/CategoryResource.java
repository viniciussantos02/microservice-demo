package com.example.demo.resource;

import com.example.demo.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

public interface CategoryResource {
    @GetMapping
    public ResponseEntity<List<Category>> listAll();

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id);

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response);
}
