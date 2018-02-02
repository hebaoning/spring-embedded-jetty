package demo.spring.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackageClasses = {
        demo.spring.mapper.UserMapper.class
})
@ComponentScan(basePackageClasses = {
        demo.spring.metric.MetricConfig.class,
        demo.spring.service.UserServiceImpl.class
})
@PropertySource("file:${config.dir:src/main/resources}/app-${spring.profiles.active:dev}.properties")
public class RootConfig {

    @Value("${datasource.driverClassName}")
    private String dsDriverClassName;

    @Value("${datasource.url}")
    private String dsUrl;

    @Value("${datasource.username}")
    private String dsUsername;

    @Value("${datasource.password}")
    private String dsPassword;

    @Value("${datasource.initialSize}")
    private Integer dsInitialSize;

    @Value("${datasource.maxTotal}")
    private Integer dsMaxTotal;

    @Value("${datasource.maxIdle}")
    private Integer dsMaxIdle;

    @Value("${datasource.minIdle}")
    private Integer dsMinIdle;

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
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dsDriverClassName);
        dataSource.setUrl(dsUrl);
        dataSource.setUsername(dsUsername);
        dataSource.setPassword(dsPassword);
        dataSource.setInitialSize(dsInitialSize);
        dataSource.setMaxTotal(dsMaxTotal);
        dataSource.setMaxIdle(dsMaxIdle);
        dataSource.setMinIdle(dsMinIdle);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}