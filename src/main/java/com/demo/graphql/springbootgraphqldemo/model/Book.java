package com.demo.graphql.springbootgraphqldemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Book {
	
	@Id
	private Long id;
	private String title;
	private String publisher;
	private String[] authors;
	private String publishedDate;


}
