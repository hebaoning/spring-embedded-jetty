package demo.spring.controller;

import demo.spring.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/user", produces = "application/json")
    public User getUser() {
        User user = new User();
        user.setAge(30);
        user.setName("hbn");
        return user;
    }
}
