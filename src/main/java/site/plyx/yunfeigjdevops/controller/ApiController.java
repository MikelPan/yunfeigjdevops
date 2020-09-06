package site.plyx.yunfeigjdevops.controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ApiController {
 
    @GetMapping(value = "/index")
    public String index(){
        return "Welcome to Spring Boot Project ! ";
    }
}
