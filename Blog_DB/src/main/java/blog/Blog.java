package blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@SpringBootApplication
public class Blog {
    public static void main(String[] args) {
        SpringApplication.run(Blog.class, args);
    }
}
