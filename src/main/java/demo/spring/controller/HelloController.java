package demo.spring.controller;

import com.codahale.metrics.annotation.Timed;
import demo.spring.model.User;
import demo.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController extends BaseController {

    @Autowired
    private UserService userService;

    @Timed
    @GetMapping(value = "/user", produces = "application/json")
    public User getUser() {
        User user = userService.getUser(1);
        user.setAge(30);
        user.setName(env.getProperty("app.name"));
        return user;
    }
}
