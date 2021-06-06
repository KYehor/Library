package com.library.services.impl;

import com.library.entities.Category;
import com.library.repositories.ICategoryRepository;
import com.library.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component("ICategoryServiceImpl")
public class ICategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository iCategoryRepository;

    @Autowired
    public ICategoryServiceImpl(ICategoryRepository iCategoryRepository){
        this.iCategoryRepository = iCategoryRepository;
    }

    @Override
    public void add(Category category) {
        iCategoryRepository.save(category);
    }

    @Override
    public Category get(Long id) {
        return iCategoryRepository.findById(id).get();
    }

    @Override
    public List<Category> listAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        iCategoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findByKeyword(String name) {
        return iCategoryRepository.findByKeyword(name);
    }

    @Override
    public void save(Category category){
        iCategoryRepository.save(category);
    }
}
