package com.example.demo.resource.impl;

import com.example.demo.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryResourceControllerTest {

    @Autowired
    CategoryResourceController categoryController;

    @MockBean
    CategoryRepository categoryRepository;

    @BeforeAll
    public void beforeTest() {

    }
}