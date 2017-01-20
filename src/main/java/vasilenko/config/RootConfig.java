package vasilenko.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages ={ "vasilenko" })
@Import({DataConfig.class,WebConfig.class})
public class RootConfig {
}
