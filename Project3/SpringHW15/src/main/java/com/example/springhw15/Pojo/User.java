package com.example.springhw15.Pojo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "id Should not be empty")
    @Size(min = 3,max = 5,message = "id should not be less than 3 and not more than 5 ")
    private String id;
    @NotEmpty(message = "username Should not be empty")
    @Size(min = 5,max = 8,message = "username should not be less than 5 and not more than 8 ")
    private String username;
    @NotEmpty(message = "password Should not be empty")
    @Size(min = 6,max = 10,message = "password should not be less than 6 and not more than 10 ")
   @Pattern(regexp = "^(([a-zA-Z]+\\d+)|(\\d+[a-zA-Z]+))[a-zA-Z0-9]*$",message = "password must have characters and digits")
    private String password;
    @NotEmpty(message = "email Should not be empty")
    @Email(message = "'not valid email")
    private String email;
    @NotEmpty(message = "role Should not be empty")
    @Pattern(regexp = "^(Admin||Customer)$",message = "role should Admin or Customer only ")
    private String role;
    @NotNull(message = "balance should not be empty")
    @PositiveOrZero(message = "balance should be positve number")
    private int balance;
}
