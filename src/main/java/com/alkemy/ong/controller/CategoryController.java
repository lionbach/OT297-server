package com.alkemy.ong.controller;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryOnlyNameResponse;
import com.alkemy.ong.models.response.CategoryResponse;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest categoryRequest) throws IOException {
        CategoryResponse categorySavedResponse = categoryService.save(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySavedResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable Long id) throws IOException {
        CategoryResponse categoryUpdatedResponse = categoryService.update(categoryRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryUpdatedResponse);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryOnlyNameResponse>> listCategories() {
        List<CategoryOnlyNameResponse> categoriesResponse = categoryService.listCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categoriesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.getCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
