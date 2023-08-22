package com.subrutin.docker.service.impl;

import org.springframework.stereotype.Service;

import com.subrutin.docker.domain.Book;
import com.subrutin.docker.dto.BookCreateRequestDTO;
import com.subrutin.docker.dto.BookDetailResponseDTO;
import com.subrutin.docker.exception.ResourceNotFoundException;
import com.subrutin.docker.repository.BookRepository;
import com.subrutin.docker.service.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Override
    public void createBook(BookCreateRequestDTO dto) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setDescription(dto.description());
        bookRepository.save(book);
    }

    @Override
    public BookDetailResponseDTO findBook(Long id){
        Book book =  bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("book.notfound"));
        BookDetailResponseDTO dto = new BookDetailResponseDTO(book.getId(), book.getTitle(), book.getDescription());
        return dto;
    }
    
}
