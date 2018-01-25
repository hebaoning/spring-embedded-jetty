package demo.spring.controller;

import com.codahale.metrics.annotation.Timed;
import demo.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController extends BaseController {

    @Timed
    @GetMapping(value = "/user", produces = "application/json")
    public User getUser() {
        User user = new User();
        user.setAge(30);
        user.setName(env.getProperty("app.name"));
        return user;
    }
}
