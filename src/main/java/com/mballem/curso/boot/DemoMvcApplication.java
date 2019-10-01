package com.mballem.curso.boot;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMvcApplication.class, args);
		System.out.println(TimeZone.getDefault());
	}

}
