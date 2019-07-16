package com.spring.spring5welcome.repositories;

import com.spring.spring5welcome.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
