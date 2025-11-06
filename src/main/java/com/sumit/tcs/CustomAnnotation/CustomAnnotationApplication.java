package com.sumit.tcs.CustomAnnotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomAnnotationApplication {

	static {
		System.out.println("This is printed from STATIC Block in CustomAnnotationApplication");
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomAnnotationApplication.class, args);
		System.out.println("Ready in main function :: " + CustomAnnotationApplication.class.getName());
	}

}
