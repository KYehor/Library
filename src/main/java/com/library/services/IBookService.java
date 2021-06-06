package com.library.services;

import com.library.entities.Book;

import java.util.List;

public interface IBookService {
    Book updateBook(Book book);
    List<Book> listAll();
    void save(Book book);
    void delete(Long id);
    Book get(Long id);
    List<Book> findAllByCategoryId(Long id);
    List<Book> findByKeyword(String keyword);
}
