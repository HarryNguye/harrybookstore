package com.example.harrybookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import com.example.harrybookstore.domain.Category;
import com.example.harrybookstore.domain.CategoryRepository;
import com.example.harrybookstore.domain.Book;
import com.example.harrybookstore.domain.BookRepository;


//@DataJpaTest

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HarrybookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository grepository;
    
    @Test
    public void findByLastnameShouldReturnBook() {
        List<Book> books = repository.findByTitle("Lauri");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Lauri");
    }
    
    @Test
    public void createNewStudent() {
    	Category category = new Category("Comedy");
    	grepository.save(category);
    	Book book = new Book("Lauri", "Nguyen", 123, 123,123, category);
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
    @Test
    public void deleteNewStudent() {
		List<Book> books = repository.findByTitle("Title");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByTitle("Title");
		assertThat(newBooks).hasSize(0);
     }

}