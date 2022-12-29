package com.komandux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/sayHello")
	String sayHello() {
		return "Hello";
	}
}
