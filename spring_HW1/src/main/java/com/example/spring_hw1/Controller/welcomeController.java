package com.example.spring_hw1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class welcomeController {
    @GetMapping("/name")
    public String getName(){
        return "My Name is Maraheb";
    }
@GetMapping("age")
    public String getAge(){
        return "My Age is....";
}

@GetMapping("/check/status")
    public String getStatus(){
        return "Everything Ok";
}

@GetMapping("/health")
    public String getHeaith(){
        return "Server health is up and running";
}

}
