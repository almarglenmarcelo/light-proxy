package slf.almardemo.lightproxy.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring-boot")
public class GreetingController {

    @GetMapping
    public String greeting(){
        return "Hello World of Spring Boot!";
    }

}
