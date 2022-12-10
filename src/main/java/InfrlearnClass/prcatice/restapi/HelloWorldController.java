package InfrlearnClass.prcatice.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // GET
    // hellow-wordl(end point)
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "hello-World";
    }

}
