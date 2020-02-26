package com.reactive.netify.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document
@Data
public class Movie {

	private String id;

	@NonNull
	private String title;

	public Movie(String title) {
		this.title = title;
	}
}
