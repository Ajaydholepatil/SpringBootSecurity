package com.example.bookapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bookapplication.entity.BookEntity;
import com.example.bookapplication.service.BookService;

@Controller
@RequestMapping("/book/v1")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/addBook")
	public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity bookEntity) {
		BookEntity saveBook = bookService.addBook(bookEntity);
		return ResponseEntity.ok(saveBook);

	}

}
