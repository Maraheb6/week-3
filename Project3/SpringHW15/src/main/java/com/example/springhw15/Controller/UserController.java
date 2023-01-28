package com.example.springhw15.Controller;

import com.example.springhw15.Pojo.MerchantStock;
import com.example.springhw15.Pojo.Product;
import com.example.springhw15.Pojo.User;
import com.example.springhw15.Service.MerchantStockService;
import com.example.springhw15.Service.ProductService;
import com.example.springhw15.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MerchantStockService merchantStockService;
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
        ArrayList<User> users=userService.getUser();
        return ResponseEntity.status(200).body(users);
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
       userService.addUser(user);
        return ResponseEntity.status(200).body("User Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id,@Valid@RequestBody User user,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdate=userService.updateUser(id,user);
        if(isUpdate){
            return ResponseEntity.status(200).body("User Update");
        }
        return ResponseEntity.status(400).body("Wrong ID");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        Boolean isDelete=userService.deleteUser(id);
        if(isDelete){
            return ResponseEntity.status(200).body("User Deleted");
        }
        return ResponseEntity.status(400).body("Wrong ID");
    }

    @PostMapping("/addproduct/{id}/{idProduct}/{idMerchant}/{stock}")
    public ResponseEntity addProduct(@Valid@PathVariable String id,@Valid@PathVariable String idProduct,@Valid@PathVariable String idMerchant,@Valid@PathVariable int stock, MerchantStock merchantStock,Errors errors){
        if(errors.hasErrors()){
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

            int result=userService.addProduct(id,idProduct,idMerchant,stock,merchantStock);
            if(result==1){
                merchantStock.setId(id);
                merchantStock.setProductid(idProduct);
                merchantStock.setMerchantid(idMerchant);
                merchantStock.setStock(stock);
                merchantStockService.addMerchantStock(merchantStock);
                return ResponseEntity.status(200).body("Product Added");}

            else  if (result==2)
                return ResponseEntity.status(400).body("User not Admin");

            else
            return ResponseEntity.status(400).body("Wrong Id");

    }
    @PutMapping("/buy/{userid}/{productid}/{merchantid}/{number}")
    public ResponseEntity buyProduct(@PathVariable String userid,@PathVariable String productid,@PathVariable String merchantid,@PathVariable int number,User user,MerchantStock merchantStock,Product product){
        int result=userService.buyProduct(userid,productid,merchantid,number,user,merchantStock);
        if(result==6){
            user.setBalance(user.getBalance()-product.getPrice());
            merchantStock.setStock(merchantStock.getStock()-number);
            userService.updateUser(userid,user);
            merchantStockService.updateMerchantStock(merchantid,merchantStock);
            return ResponseEntity.status(200).body("Buy Don!!");}
       else  if (result==5)
            return ResponseEntity.status(400).body("user balance less than product price");
        else if(result==4)
            return ResponseEntity.status(400).body("stock empty");
        else if(result==3)
            return ResponseEntity.status(400).body("wrong merchant id");
        else if(result==2)
            return ResponseEntity.status(400).body("wrong product id");
        else  return ResponseEntity.status(400).body("wrong user id");

    }

}
