package com.projectwebservice.controller;

import com.projectwebservice.model.exam.Category;
import com.projectwebservice.service.CategoryService;
import com.projectwebservice.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //add category
    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        Category category1=this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }
    //get category
    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId")Long categoryId){
        return this.categoryService.getCategory(categoryId);
    }
    //get categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }
    //update category
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return this.categoryService.updateCategory(category);
    }
    //delete category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId")Long categoryId){
        this.categoryService.deleteCategory(categoryId);
    }
}
