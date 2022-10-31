package com.alkemy.ong.controller;


import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryOnlyNameResponse;
import com.alkemy.ong.models.response.CategoryPaginatedResponse;
import com.alkemy.ong.models.response.CategoryResponse;
import com.alkemy.ong.service.CategoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categories")
@Api(tags = "Categories", description = "CRUD methods pertaining to Categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    @ApiOperation(value = "Create a category", notes = "Create and return a category")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<CategoryResponse> save(@Valid @ApiParam(name = "Create a Category", value = "CategoryRequest", required = true) @RequestBody CategoryRequest categoryRequest) throws IOException {
        CategoryResponse categorySavedResponse = categoryService.save(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySavedResponse);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a category by id", notes = "Update and return a category according to the id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Category not found")})
    public ResponseEntity<CategoryResponse> update(@Valid @RequestBody @ApiParam(name = "Update a Category", value = "Category Request") CategoryRequest categoryRequest, @PathVariable @ApiParam(name = "id", type = "Long", value = "Category Id", example = "1", required = true) Long id) throws IOException {
        CategoryResponse categoryUpdatedResponse = categoryService.update(categoryRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryUpdatedResponse);
    }

    @GetMapping
    @ApiOperation(value = "Get all categories", notes = "Returns a list of categories")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Page not found")})
    public ResponseEntity<List<CategoryOnlyNameResponse>> listCategories() {
        List<CategoryOnlyNameResponse> categoriesResponse = categoryService.listCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categoriesResponse);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a category by id", notes = "Return a category as per the id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Category not found")})
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable @ApiParam(name = "id", type = "Long", value = "Category id", example = "1", required = true) Long id) {
        CategoryResponse categoryResponse = categoryService.getCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a category by id", notes = "Delete a category according to the id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Category not found")})
    public ResponseEntity<Void> deleteCategory(@PathVariable @ApiParam(name = "id", type = "Long", value = "Category id", example = "1", required = true) Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<CategoryPaginatedResponse> findAllPaginated(@RequestParam(defaultValue = "1") Integer page){
        CategoryPaginatedResponse response  = categoryService.findAllPaginated(page, 10);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
