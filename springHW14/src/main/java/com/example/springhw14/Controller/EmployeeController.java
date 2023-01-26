package com.example.springhw14.Controller;

import com.example.springhw14.Pojo.ApiResponse;
import com.example.springhw14.Pojo.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    ArrayList<Employee>employees=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    @PostMapping("/add")
    public ResponseEntity addEmployees(@Valid @RequestBody Employee employee,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Added"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index,@Valid@RequestBody Employee employee,Errors errors){
        if(errors.hasErrors()){
            String meassage=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(meassage));

        }
        employees.set(index,employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Updated"));
    }


    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEmoplyee(@PathVariable int index){
        employees.remove(index);
        return new ApiResponse("Employee Deleted");
    }

  @PutMapping("/annualleave/{id}")
    public ResponseEntity leaveEmployee(@PathVariable int id) {
      for (Employee e : employees) {
          if (e.getId().equals(id))
              if (e.getAnnualLeave() == 0) {
                  return ResponseEntity.status(400).body(new ApiResponse("No Annual Leave"));
              } else if (e.isOnLeave()) {
                  return ResponseEntity.status(400).body(new ApiResponse("Bad Request onLeve true"));
              }
              else {
                  e.setOnLeave(true);
                  int newAnnualLeav = e.getAnnualLeave() - 1;
                  e.setAnnualLeave(newAnnualLeav);
                  return ResponseEntity.status(200).body(new ApiResponse("Don!!"));
              }

          }


      return ResponseEntity.status(200).body(new ApiResponse("Not Found!"));
  }


    }

/*boolean equals = e.getId().equals(id);
            if (equals)
                if ( e.getAnnualLeave() > 0){
                    e.setAnnualLeave(e.getAnnualLeave() - 1);
                    e.setOnLeave(true);

                }
            return  "Don!!";


        }
        return "Bad Request";*/






