package com.demo.graphql.springbootgraphqldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.graphql.springbootgraphqldemo.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
