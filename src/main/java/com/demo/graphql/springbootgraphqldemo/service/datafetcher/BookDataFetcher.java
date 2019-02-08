package com.demo.graphql.springbootgraphqldemo.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.graphql.springbootgraphqldemo.model.Book;
import com.demo.graphql.springbootgraphqldemo.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book>{
	
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public Book get(DataFetchingEnvironment environment) {
		Long id = environment.getArgument("id");
		return bookRepository.findById(id).orElse(null);
	}

	

}
