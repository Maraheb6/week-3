package com.example.springhw15.Controller;

import com.example.springhw15.Pojo.MerchantStock;
import com.example.springhw15.Pojo.Product;
import com.example.springhw15.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStock();
        return ResponseEntity.status(200).body(merchantStocks);
    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("Merchant Stock Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id,@Valid@RequestBody MerchantStock merchantStock,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdate=merchantStockService.updateMerchantStock(id,merchantStock);
        if(isUpdate){
            return ResponseEntity.status(200).body("Merchant Stock Update");
        }
        return ResponseEntity.status(400).body("Wrong ID");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable String id){
        Boolean isDelete=merchantStockService.deleteMerchantStock(id);
        if(isDelete){
            return ResponseEntity.status(200).body("Merchant Stock Deleted");
        }
        return ResponseEntity.status(400).body("Wrong ID");
    }

}
