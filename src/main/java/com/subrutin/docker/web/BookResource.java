package com.subrutin.docker.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.docker.dto.BookCreateRequestDTO;
import com.subrutin.docker.dto.BookDetailResponseDTO;
import com.subrutin.docker.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
public class BookResource {
    
    private BookService bookService; 

    @PostMapping("/v1/book")
    public ResponseEntity<Void> createNewBook(@RequestBody BookCreateRequestDTO dto){
        bookService.createBook(dto);
        return ResponseEntity.created(URI.create("/v1/book")).build();
    }

    @GetMapping("/v1/book/{bookId}")
    public ResponseEntity<BookDetailResponseDTO> findBookDetail(@PathVariable Long bookId){

        return ResponseEntity.ok().body(bookService.findBook(bookId));
    }
}
