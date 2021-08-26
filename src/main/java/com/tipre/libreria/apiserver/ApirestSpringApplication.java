package com.tipre.libreria.apiserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApirestSpringApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApirestSpringApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String password = "springboot";
		for(int i=0;i < 3; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println("Password " + i + " encriptado: " + passwordBcrypt);
		}
		
	}

}
