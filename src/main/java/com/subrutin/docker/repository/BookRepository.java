package com.subrutin.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.docker.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}