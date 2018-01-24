package demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController {

    @Autowired
    Environment env;
}
