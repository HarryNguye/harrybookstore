package com.example.harrybookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.harrybookstore.domain.CategoryRepository;
import com.example.harrybookstore.domain.Category;
import com.example.harrybookstore.domain.BookRepository;
import com.example.harrybookstore.domain.Book;



@SpringBootApplication
public class HarrybookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(HarrybookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(HarrybookstoreApplication.class, args);
	}
		@Bean
		public CommandLineRunner studentDemo(CategoryRepository repository, CategoryRepository grepository) {
			return (args) -> {
				log.info("save a couple of students");
				grepository.save(new Category("Horror"));
				grepository.save(new Category("Fantasy"));
				grepository.save(new Category("Comedy"));
				
				};

			}
		}

