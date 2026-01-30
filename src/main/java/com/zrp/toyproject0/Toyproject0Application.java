package com.zrp.toyproject0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Toyproject0Application {

	public static void main(String[] args) {
		SpringApplication.run(Toyproject0Application.class, args);
	}

}

class Test {

	private static long SERIAL_ID = 1L;

	private long id;
	private String name;
	private String address;

	public Test(String name, String address) {
		this.id = SERIAL_ID++;
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return id + " : " + name + " : " + address;
	}

}
