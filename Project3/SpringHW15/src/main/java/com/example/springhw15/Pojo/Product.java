package com.example.springhw15.Pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "id Should not be empty")
    @Size(min = 3,max = 5,message = "id should not be less than 3 and not more than 5 ")
    private String id;
    @NotEmpty(message = "name Should not be empty")
    @Size(min = 3,max =8,message = "name should not be less than 3 and not more than 8 ")
    private String name;
   @NotNull(message = "price Should not be empty")
   @PositiveOrZero(message = "price should be zero or positive number only")
    private int price;
    @NotEmpty(message = "category id Should not be empty")
    @Size(min = 3,max = 5,message = "category id should not be less than 3 and not more than 5 ")
   private String categoryID;
}
