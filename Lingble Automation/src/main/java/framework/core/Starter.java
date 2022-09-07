package framework.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
//@ComponentScan(basePackages = "core")
//@ContextConfiguration()
public class Starter {
public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Starter.class);
    app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
    app.run(args);

}
}