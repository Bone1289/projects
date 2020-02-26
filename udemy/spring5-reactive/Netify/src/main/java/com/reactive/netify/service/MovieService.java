package com.reactive.netify.service;

import com.reactive.netify.domain.Movie;
import com.reactive.netify.domain.MovieEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

	Flux<MovieEvent> events(String movieId);

	Mono<Movie> getMovieById(String id);

	Flux<Movie> getAllMovies();
}
