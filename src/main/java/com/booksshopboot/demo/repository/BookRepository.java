package com.booksshopboot.demo.repository;

import com.booksshopboot.demo.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByTitle(String title);
    List<Book> findAll();
    Book findById(long id);
}
