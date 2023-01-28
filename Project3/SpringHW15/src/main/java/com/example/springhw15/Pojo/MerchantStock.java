package com.example.springhw15.Pojo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "id Should not be empty")
    @Size(min = 3,max = 5,message = "id should not be less than 3 and not more than 5 ")
    private String id;
    @NotEmpty(message = "product id  Should not be empty")
    @Size(min = 3,max =5,message = "product id  should not be less than 3 and not more than 5 ")
    private String productid ;
    @NotEmpty(message = "merchantid Should not be empty")
    @Size(min = 3,max = 5,message = " merchant id should not be less than 3 and not more than 5 ")
    private String merchantid;
    @NotNull(message = "stock Should not be empty")
    @Min(value = 10,message = "stock should not be less than 10")
    private int stock;
}
