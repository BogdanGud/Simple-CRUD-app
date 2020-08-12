package com.booksshopboot.demo.controller;

import com.booksshopboot.demo.model.Author;
import com.booksshopboot.demo.repository.AuthorRepository;
import com.booksshopboot.demo.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;



    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Author author) {
        authorRepository.save(author);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Author> fiadAll() {
        return authorRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Author findById(@PathVariable("id") long id) {
        return authorRepository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable("id") long id) {
        Author currentAuthor = authorRepository.findOne(id);
        if (currentAuthor == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Book with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        authorRepository.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book ' " + currentAuthor.getLastName() + "' deleted");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Author author) {
        Author currentAuthor = authorRepository.findOne(id);
        if (currentAuthor == null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. Book with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentAuthor.setBirthday(author.getBirthday());
        currentAuthor.setFirstName(author.getFirstName());
        currentAuthor.setLastName(author.getLastName());
        authorRepository.save(currentAuthor);
        return new ResponseEntity(currentAuthor, HttpStatus.OK);
    }


}
