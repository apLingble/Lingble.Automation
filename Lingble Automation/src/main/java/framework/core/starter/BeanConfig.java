package framework.core.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public String getMessage(){
        return ("Start Spring!!!!");
    }
}
