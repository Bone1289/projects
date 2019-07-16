package com.spring.spring5welcome.bootstrap;

import com.spring.spring5welcome.model.Author;
import com.spring.spring5welcome.model.Book;
import com.spring.spring5welcome.model.Publisher;
import com.spring.spring5welcome.repositories.AuthorRepository;
import com.spring.spring5welcome.repositories.BookRepository;
import com.spring.spring5welcome.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }


    private void initData() {
        Publisher publisher = new Publisher("Egalitati 5", "Dusks");
        publisherRepository.save(publisher);

        {
            Author bogdan = new Author("Bogdan", "Rata");
            Book ddd = new Book("Domain Driven Design", "12345", publisher);
            bogdan.getBooks().add(ddd);
            authorRepository.save(bogdan);
            bookRepository.save(ddd);
        }

        {
            Author miha = new Author("Mihaela", "Badan");
            Book fantasy = new Book("One hundred years of solitude", "123121", publisher);
            miha.getBooks().add(fantasy);
            authorRepository.save(miha);
            bookRepository.save(fantasy);
        }
    }
}
