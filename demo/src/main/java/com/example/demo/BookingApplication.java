package com.example.demo;

import com.example.demo.model.Clients;
import com.example.demo.service.BookingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
	}
}
