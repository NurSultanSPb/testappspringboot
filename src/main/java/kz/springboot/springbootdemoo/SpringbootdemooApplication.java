package kz.springboot.springbootdemoo;

import kz.springboot.springbootdemoo.controllers.OfficersController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootdemooApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemooApplication.class, args);
	}

}
