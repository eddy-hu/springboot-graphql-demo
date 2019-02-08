package com.demo.graphql.springbootgraphqldemo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.graphql.springbootgraphqldemo.service.GraphQLService;

import graphql.ExecutionResult;

@RequestMapping("/rest/books")
@RestController
public class BookResource {
	
	@Autowired
	GraphQLService graphSQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
		
		ExecutionResult execute = graphSQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(execute,HttpStatus.OK);
	}

}
