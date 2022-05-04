package com.example.demo.resource;

import com.example.demo.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

public interface CategoryResource {

    @GetMapping
    ResponseEntity<List<Category>> listAll();

    @GetMapping("/{id}")
    ResponseEntity<Category> findCategoryById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Category> createCategory(@Valid @RequestBody Category category, HttpServletResponse response);
}
