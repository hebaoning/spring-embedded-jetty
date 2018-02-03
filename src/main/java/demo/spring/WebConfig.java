package demo.spring;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableMetrics
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {
        demo.spring.controller.BaseController.class
})
public class WebConfig {

}
