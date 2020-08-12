package com.booksshopboot.demo.controller;

import com.booksshopboot.demo.model.Author;
import com.booksshopboot.demo.model.Book;
import com.booksshopboot.demo.model.Genre;
import com.booksshopboot.demo.repository.BookRepository;
import com.booksshopboot.demo.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;


@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Book book) {
        bookRepository.save(book);
    }


//    @Cacheable(value = "book")
    @RequestMapping(method = RequestMethod.GET)
    public List<Book> fiadAll() {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Book findById(@PathVariable("id") long id) {
        return bookRepository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable("id") long id) {
        Book currentBook = bookRepository.findOne(id);
        if (currentBook == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Book with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        bookRepository.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book ' " + currentBook.getTitle() + "' deleted");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Book book) {
        Book currentBook = bookRepository.findOne(id);
        if (currentBook == null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. Book with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentBook.setTitle(book.getTitle());
        currentBook.setIsbn(book.getIsbn());
        currentBook.setGenre(book.getGenre());
        bookRepository.save(currentBook);
        return new ResponseEntity(currentBook, HttpStatus.OK);
    }

}
