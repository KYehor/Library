package com.library.services.impl;

import com.library.entities.Book;
import com.library.repositories.IBookRepository;
import com.library.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component("IBookServiceImpl")
public class IBookServiceImpl implements IBookService {

    private final IBookRepository iBookRepository;

    @Autowired
    public IBookServiceImpl(IBookRepository iBookRepository){
        this.iBookRepository = iBookRepository;

    }
    @Override
    public Book updateBook(Book book) {
        return iBookRepository.save(book);
    }

    public void save(Book book){
        iBookRepository.save(book);
    }

    public Book get(Long id) {
        return iBookRepository.findById(id).get();
    }

    public void delete(Long id) {
        iBookRepository.deleteById(id);
    }

    @Override
    public List<Book> listAll() {
        return (List<Book>) iBookRepository.findAll();
    }

    @Override
    public List<Book> findAllByCategoryId(Long id){
        return iBookRepository.findAllByCategoryId(id);
    }

    @Override
    public List<Book> findByKeyword(String keyword) {
        return iBookRepository.findByKeyword(keyword);
    }
}
