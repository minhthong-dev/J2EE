package com.example.kiemtra_j2EE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
// @SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) // dung
// de test postman ma khong can dang nhap
public class KiemtraJ2EeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KiemtraJ2EeApplication.class, args);
	}

}
