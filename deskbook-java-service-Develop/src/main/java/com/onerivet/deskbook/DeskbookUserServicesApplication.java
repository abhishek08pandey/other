package com.onerivet.deskbook;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication 
@SecurityScheme(name = "bearerAuth",  scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info = @Info(title = "Deskbook", version = "1.0", description = "Deskbook Application"))
@EnableScheduling
@EnableTransactionManagement
public class DeskbookUserServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeskbookUserServicesApplication.class, args);
	}
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
