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
	public CommandLineRunner studentDemo(BookRepository repository, CategoryRepository grepository) {
		return (args) -> {
			log.info("save a couple of students");
			grepository.save(new Category("Horror"));
			grepository.save(new Category("Fantasy"));
			grepository.save(new Category("Comedy"));

			repository.save(new Book("dsa", "dsa", 1,123, 1, grepository.findByName("Horror").get(0)));
			repository.save(new Book("dsa", "dsa", 1,123, 1, grepository.findByName("Comedy").get(0)));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
		}

	};
}
}
