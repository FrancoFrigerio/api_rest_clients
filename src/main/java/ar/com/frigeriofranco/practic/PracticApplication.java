package ar.com.frigeriofranco.practic;

import ar.com.frigeriofranco.practic.util.UtilJWT;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PracticApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}



}
