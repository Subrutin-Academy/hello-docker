package com.subrutin.docker.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.docker.dto.GreetingDTO;

@RestController
public class HelloResource {
	
	@GetMapping("/v1/hello")
	public ResponseEntity<GreetingDTO> greeting(){
		return ResponseEntity.ok().body(new GreetingDTO("Hello World"));
	}

}
