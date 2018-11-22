package com.chrisbrown.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.chrisbrown.spring5webapp.model.Author;
import com.chrisbrown.spring5webapp.model.Book;
import com.chrisbrown.spring5webapp.model.Publisher;
import com.chrisbrown.spring5webapp.repositories.AuthorRepository;
import com.chrisbrown.spring5webapp.repositories.BookRepository;
import com.chrisbrown.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	
	
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}

	private void initData() {
		//eric
		Author eric = new Author("Eric", "Evans");
		Publisher hc = new Publisher("Harper Collins", "50 Road Street");
		publisherRepository.save(hc);
		Book ddd = new Book("Domain Driven Design", "1234", hc);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		
		//Rod
		Author rod = new Author("Rod", "Johnson");
		Publisher worx = new Publisher("Worx", "33 done road");
		publisherRepository.save(worx);
		Book noEJB = new Book("J2EE Develpment without EJB", "23444", worx);
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);
		
		authorRepository.save(rod);
		bookRepository.save(noEJB);
		
	}
}
