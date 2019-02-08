package com.demo.graphql.springbootgraphqldemo.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/books")
@RestController
public class BookResource {
	@PostMapping
	public void getAllBooks(@RequestBody String query) {
	}

}
