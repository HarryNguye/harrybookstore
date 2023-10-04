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
import com.example.harrybookstore.domain.AppUserRepository;
import com.example.harrybookstore.domain.Book;
import com.example.harrybookstore.domain.AppUser;



@SpringBootApplication
public class HarrybookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(HarrybookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HarrybookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner studentDemo(BookRepository repository, CategoryRepository grepository,
			AppUserRepository urepository) {
		return (args) -> {
			log.info("save a couple of students");
			grepository.save(new Category("Horror"));
			grepository.save(new Category("Fantasy"));
			grepository.save(new Category("Comedy"));

			repository.save(new Book("Lauri", "Moi", 1, 123, 1, grepository.findByName("Horror").get(0)));
			repository.save(new Book("Harry", "Hei", 1, 123, 1, grepository.findByName("Comedy").get(0)));

			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
