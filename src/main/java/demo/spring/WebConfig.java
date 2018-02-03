package demo.spring;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import demo.spring.controller.KafkaInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableMetrics
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {
        demo.spring.controller.BaseController.class
})
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new KafkaInterceptor());
    }

}
