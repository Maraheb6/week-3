package com.example.springhw22.Controller;

import com.example.springhw22.pojo.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    ArrayList<Customer> customers=new ArrayList<>();
    @GetMapping("/get")
    public ArrayList getCustomer(){
        return customers;
    }

    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return "Customer Added";
    }

    @PutMapping("/update/{index}")
    public String updateCustomer(@PathVariable int index,@RequestBody Customer customer){
        customers.set(index,customer);
        return "Customer Updated";
    }

    @DeleteMapping("/delete/{index}")
    public String deleteCustomer(@PathVariable int index){
        customers.remove(index);
        return "Customer Deleted";
    }

    @PutMapping("/deposit/{id}/{amount}")
    public String depositMoney(@PathVariable int id,@PathVariable int amount) {
        for (Customer c : customers) {
            if (c.getId()==id)
                c.setBalance(c.getBalance() + amount);
            return "Customer Deposit Don";
        }
                return "Not Found ID";
        }

        @PutMapping("/withdraw/{id}/{amount}")
    public String WithdrawMoney(@PathVariable int id,@PathVariable int amount){
        for(Customer c:customers){
            if(c.getId()==id)
                if(c.getBalance()>=amount){
                    c.setBalance(c.getBalance()-amount);
                     return "Withdrawal Successful";

            }
            else return "Amount Less Than Balance";

        }
            return null;
        }
    }
