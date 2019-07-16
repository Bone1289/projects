package com.spring.spring5welcome.repositories;

import com.spring.spring5welcome.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
