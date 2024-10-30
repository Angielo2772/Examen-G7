package com.codigo.Tarea_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TareaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TareaApiApplication.class, args);
	}

}
