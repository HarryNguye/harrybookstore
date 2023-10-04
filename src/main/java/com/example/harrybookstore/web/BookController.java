package com.example.harrybookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.harrybookstore.domain.Book;
import com.example.harrybookstore.domain.BookRepository;
import com.example.harrybookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository grepository;

	// BOOK CONTROLLER

	@RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }	

	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());

		return "booklist";
	}

	// FUNCTIONALITY FOR ADDING BOOKS

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", grepository.findAll());

		return "addbook";
	}

	// FUNCTIONALITY FOR SAVING BOOKS

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	// FUNCTIONALITY FOR DELETING BOOKS

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long Id, Model model) {
		repository.deleteById(Id);
		return "redirect:../booklist";
	}

	// FUNCTIONALITY FOR EDITING BOOKS

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long Id, Model model) {

		Optional<Book> optionalBook = repository.findById(Id);

		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			model.addAttribute("book", book);
			model.addAttribute("categories", grepository.findAll());

			return "editbook";
		} else {
			return "redirect:/booklist";
		}
	}

	// FUNCTIONALITY FOR UPDATING EDITED BOOKS

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute Book updatedBook) {

		Book existingBook = repository.findById(updatedBook.getId()).orElse(null);
		if (existingBook != null) {
			// Päivitä kirjan tiedot
			existingBook.setAuthor(updatedBook.getAuthor());
			existingBook.setTitle(updatedBook.getTitle());
			existingBook.setIsbn(updatedBook.getIsbn());
			existingBook.setPublicationYear(updatedBook.getPublicationYear());
			existingBook.setCategory(updatedBook.getCategory());

			repository.save(existingBook);
			return "redirect:/booklist";

		} else {
			return "redirect:/booklist";
		}

	}

	// RESTful service to get all book
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}

	// RESTful service to get book by id
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    	
	}
}