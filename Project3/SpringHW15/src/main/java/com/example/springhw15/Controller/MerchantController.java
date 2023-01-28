package com.example.springhw15.Controller;

import com.example.springhw15.Pojo.Merchant;
import com.example.springhw15.Pojo.Product;
import com.example.springhw15.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;
    @GetMapping("/get")
    public ResponseEntity getMerchant(){
        ArrayList<Merchant> merchants=merchantService.getMerchant();
        return ResponseEntity.status(200).body(merchants);
    }
    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("Merchant Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id,@Valid@RequestBody Merchant merchant,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdate=merchantService.updateMerchant(id,merchant);
        if(isUpdate){
            return ResponseEntity.status(200).body("Merchant Update");
        }
        return ResponseEntity.status(400).body("Wrong ID");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        Boolean isDelete=merchantService.deleteMerchant(id);
        if(isDelete){
            return ResponseEntity.status(200).body("Merchant Deleted");
        }
        return ResponseEntity.status(400).body("Wrong ID");
    }
}
