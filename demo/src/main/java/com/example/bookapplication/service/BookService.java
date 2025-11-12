package com.example.bookapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookapplication.entity.BookEntity;
import com.example.bookapplication.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public BookEntity addBook(BookEntity bookEntity) {
		return bookRepository.save(bookEntity);
	}

}
