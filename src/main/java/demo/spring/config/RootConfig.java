package demo.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:${config.dir:src/main/resources}/app_${spring.profiles.active:dev}.properties")
public class RootConfig {
}