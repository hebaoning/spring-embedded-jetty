package demo.spring.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${db.driverClassName}")
    private String driverClassName;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.initialSize}")
    private Integer initialSize;

    @Value("${db.maxTotal}")
    private Integer maxTotal;

    @Value("${db.maxIdle}")
    private Integer maxIdle;

    @Value("${db.minIdle}")
    private Integer minIdle;

    @Bean
    @Profile("dev")
    public DataSource EmbeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("file:db_schema/test.sql")
                .build();
    }

    @Bean
    @Profile("!dev")
    public DataSource basicDataSource() {
        BasicDataSource db = new BasicDataSource();
        db.setDriverClassName(driverClassName);
        db.setUrl(url);
        db.setUsername(username);
        db.setPassword(password);
        db.setInitialSize(initialSize);
        db.setMaxTotal(maxTotal);
        db.setMaxIdle(maxIdle);
        db.setMinIdle(minIdle);
        return db;
    }

}
