package com.example.springhw15.Controller;

import com.example.springhw15.Pojo.Product;
import com.example.springhw15.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity getProduct(){
        ArrayList<Product>products=productService.getProduct();
        return ResponseEntity.status(200).body(products);
    }
@PostMapping("/add")
    public ResponseEntity addProduct(@Valid@RequestBody Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
     productService.addProduct(product);
        return ResponseEntity.status(200).body("Product Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id,@Valid@RequestBody Product product,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdate=productService.updateProduct(id,product);
        if(isUpdate){
            return ResponseEntity.status(200).body("Product Update");
        }
        return ResponseEntity.status(400).body("Wrong ID");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        Boolean isDelete=productService.deleteProduct(id);
        if(isDelete){
            return ResponseEntity.status(200).body("Product Deleted");
        }
        return ResponseEntity.status(400).body("Wrong ID");
    }
}
