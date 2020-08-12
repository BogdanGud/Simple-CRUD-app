package com.booksshopboot.demo.repository;

import com.booksshopboot.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByLastName(String lastName);

    List<Author> findAll();

    Author findById(long id);
}
