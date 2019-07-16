package com.spring.spring5welcome.repositories;

import com.spring.spring5welcome.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
