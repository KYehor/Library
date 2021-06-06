package com.library.controllers;

import com.library.entities.Category;
import com.library.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private String add_edit_template="add-edit-category";
    private String list_template="list-categories";
    private String list_redirect="redirect:/category/list";
    private final ICategoryService iCategoryService;

    @Autowired
    public CategoryController (@Qualifier("ICategoryServiceImpl")ICategoryService iCategoryService){
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("/add")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return add_edit_template;
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model){
        Category category = iCategoryService.get(id);
        model.addAttribute("category", category);

        List<Category> categories = iCategoryService.listAll();
        model.addAttribute("categories",categories);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category, BindingResult result, Model model){
        model.addAttribute("category", category);

        if(result.hasErrors()){
            return add_edit_template;
        }
            iCategoryService.save(category);

        return list_redirect+"?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        iCategoryService.delete(id);

        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listCategory(Model model,String keyword) {
        List<Category> categories = iCategoryService.listAll();
        model.addAttribute("category", new Category());
        if(keyword != null){
            model.addAttribute("categories", iCategoryService.findByKeyword(keyword));
        }else{
            model.addAttribute("categories", categories);
        }

        return list_template;
    }
}
