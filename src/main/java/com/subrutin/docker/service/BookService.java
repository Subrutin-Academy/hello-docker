package com.subrutin.docker.service;

import com.subrutin.docker.dto.BookCreateRequestDTO;
import com.subrutin.docker.dto.BookDetailResponseDTO;

public interface BookService {

    public void createBook(BookCreateRequestDTO dto);

    public BookDetailResponseDTO findBook(Long id);
    
}
