package com.example.bookapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookapplication.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity,Integer>{

}
