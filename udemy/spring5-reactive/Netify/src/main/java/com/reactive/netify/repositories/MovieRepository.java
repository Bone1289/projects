package com.reactive.netify.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.reactive.netify.domain.Movie;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String>{
}
