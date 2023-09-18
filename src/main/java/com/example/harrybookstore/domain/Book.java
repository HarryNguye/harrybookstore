package com.example.harrybookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	private long publicationYear;
	private long isbn;
	private long price;

	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;

	public Book() {
	}

	public Book(String title, String author, long publicationYear, long isbn, long price, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(long publicationYear) {
		this.publicationYear = publicationYear;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override

	public String toString() {

		if (this.category != null)
			return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
					+ ", isbn=" + isbn + ", price=" + price + ", category=" + this.getCategory() + "]";

		else
			return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
					+
					", isbn =" + isbn + ", price=" + price + ",]";

	}

}