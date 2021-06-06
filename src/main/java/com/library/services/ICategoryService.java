package com.library.services;

import com.library.entities.Category;

import java.util.List;

public interface ICategoryService {
    void add(Category category);
    Category get(Long id);
    List<Category> listAll();
    void save(Category category);
    void delete(Long id);
    List<Category> findByKeyword(String keyword);
}
