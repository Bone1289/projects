package com.reactive.netify.service;

import java.time.Duration;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.reactive.netify.domain.Movie;
import com.reactive.netify.domain.MovieEvent;
import com.reactive.netify.repositories.MovieRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;

	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Flux<MovieEvent> events(String movieId) {
		return Flux.<MovieEvent>generate(movieEventSyncSink -> {
			MovieEvent movie = new MovieEvent(movieId, new Date());
			movieEventSyncSink.next(movie);
			System.out.println(movie);
		}).delayElements(Duration.ofSeconds(1));
	}

	@Override
	public Mono<Movie> getMovieById(String id) {
		return this.movieRepository.findById(id);
	}

	@Override
	public Flux<Movie> getAllMovies() {
		return this.movieRepository.findAll();
	}

}
