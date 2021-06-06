package com.library.controllers;

import com.library.entities.Book;
import com.library.entities.Category;
import com.library.services.IBookService;
import com.library.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private String add_edit_template="add-edit-book";
    private String list_template="list-books";
    private String list_redirect="redirect:/book/list";

    private final IBookService iBookService;
    private final ICategoryService iCategoryService;

    @Autowired
    public BookController(@Qualifier("IBookServiceImpl")IBookService iBookService,@Qualifier("ICategoryServiceImpl")ICategoryService iCategoryService){
        this.iBookService = iBookService;
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        List<Category> categories = iCategoryService.listAll();
        model.addAttribute("categories",categories);

        return add_edit_template;
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model){
        Book book = iBookService.get(id);
        model.addAttribute("book", book);

        List<Category> categories = iCategoryService.listAll();
        model.addAttribute("categories",categories);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book, BindingResult result, Model model){
        model.addAttribute("book", book);
        List<Category> categories = iCategoryService.listAll();
        model.addAttribute("categories",categories);

        if(result.hasErrors()){
            return add_edit_template;
        }

        iBookService.save(book);
        return list_redirect+"?success";
    }



    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        iBookService.delete(id);

        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listBook(Model model,String keyword) {
        List<Category> categories = iCategoryService.listAll();
        model.addAttribute("categories",categories);
        model.addAttribute("book", new Book());
        List<Book> listBooks = iBookService.listAll();
        if(keyword != null){
            model.addAttribute("listBooks", iBookService.findByKeyword(keyword));
        }else{
            model.addAttribute("listBooks", listBooks);
        }

        return list_template;
    }

    @GetMapping("/list/{id}")
    public String getListOfBooksByCategory(@PathVariable("id") Long id,Model model){
        List<Book> listBooks = iBookService.findAllByCategoryId(id);
        model.addAttribute("listBooks",listBooks);

        List<Category> categories = iCategoryService.listAll();
        model.addAttribute("categories",categories);

        return list_template;
    }
}
