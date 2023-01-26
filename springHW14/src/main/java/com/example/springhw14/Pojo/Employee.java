package com.example.springhw14.Pojo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Employee {
    @NotEmpty(message = "ID Should not be Empty")
    @Size(min = 2,max=5, message = "ID Size Should 2 or more Character ")
    private String id;
    @NotEmpty(message = "Name Should not be Empty")
    @Size(min = 4,max = 10,message ="Name Should 4 Character or more" )
    private String name;
    @NotNull(message = "Age Should not be Empty")
    //@Digits(message = "age Should be Number")
    @Min(value = 26,message = "Age Should more than 25")
    private int age;
    @NotEmpty(message = "role Should not be Empty")
    @Pattern(regexp = "^(supervisor||coordinator)$",message = "Role Should be supervisor or coordinator ")
    private String role;

  //@Pattern(regexp = "^(false)$",message = "On leave Should be false")

    @AssertFalse(message = "On leave Should be false")
    private boolean onLeave;
    @NotNull(message = "Employement Year Should not be Empty")
   // @Size(min = 2000,max = 2023,message = "Not Valid Year")
    @Min(value = 2000,message = " Employement Year Should 2000 or more ")
    @Max(value = 2023,message = " Employement Year Should 2023 or less")
    private int employmentYear;
    @NotNull(message = " Annual leave Should not be Empty")
    private int annualLeave;



}
