package com.example.bookapplication.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.bookapplication.entity.BookEntity;
import com.example.bookapplication.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public BookEntity addBook(BookEntity bookEntity) {
		BookEntity saveBook = bookRepository.save(bookEntity);
		log.info("Book save succesfully with id: {}", saveBook.getId());
		return saveBook;
	}

	public BookEntity getBookById(Integer id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));
	}

}
