package tn.esprit.pidev;

import java.awt.image.BufferedImage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;



@SpringBootApplication
public class ConsommiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsommiSpringApplication.class, args);
	}
	
	
	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
	    return new BufferedImageHttpMessageConverter();
	}

}
