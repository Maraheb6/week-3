package com.example.springhw15.Controller;

import com.example.springhw15.Pojo.Category;
import com.example.springhw15.Pojo.Product;
import com.example.springhw15.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory(){
        ArrayList<Category> categories=categoryService.getCategory();
        return ResponseEntity.status(200).body(categories);
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id,@Valid@RequestBody Category category,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdate=categoryService.updateCategory(id,category);
        if(isUpdate){
            return ResponseEntity.status(200).body("Category Update");
        }
        return ResponseEntity.status(400).body("Wrong ID");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        Boolean isDelete=categoryService.deleteCategory(id);
        if(isDelete){
            return ResponseEntity.status(200).body("Category Deleted");
        }
        return ResponseEntity.status(400).body("Wrong ID");
    }
}
