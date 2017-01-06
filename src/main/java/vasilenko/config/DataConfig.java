package vasilenko.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


/**
 * Created by Alkit on 1/4/2017.
 */

@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
public class DataConfig {

    @Autowired
    private Environment env;

    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(env.getProperty("jdbc.Driver"));
        basicDataSource.setUrl(env.getProperty("jdbc.Url"));
        basicDataSource.setUsername(env.getProperty("jdbc.Name"));
        basicDataSource.setPassword(env.getProperty("jdbc.Password"));
        return  basicDataSource;
    }
}
