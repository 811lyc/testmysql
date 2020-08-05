package com.example.testmysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.testmysql.dao")
public class TestmysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestmysqlApplication.class, args);
	}

}
