package com.example.bookapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.bookapplication.entity.BookEntity;
import com.example.bookapplication.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/book/v1")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/addBook")
	public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity bookEntity) {
		BookEntity saveBook = bookService.addBook(bookEntity);
		// log.info("Book save succesfully with id: {}",saveBook.getId());
		return ResponseEntity.ok(saveBook);

	}

	@GetMapping("/getBook/{id}")
	public ResponseEntity<BookEntity> getBookById(@PathVariable Integer id) {
		log.info("Fetching book with id: {}", id);
		BookEntity book = bookService.getBookById(id);
		return ResponseEntity.ok(book);
	}
}
