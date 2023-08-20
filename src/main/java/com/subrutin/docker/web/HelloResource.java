package com.subrutin.docker.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.docker.dto.GreetingDTO;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloResource {
	
	@GetMapping("/v1/hello")
	public ResponseEntity<GreetingDTO> greeting() throws UnknownHostException{
		log.info("request served");
		InetAddress ip=InetAddress.getLocalHost();
		return ResponseEntity.ok().body(new GreetingDTO("Hello World. This is from "+ip.toString()));
	}

}
