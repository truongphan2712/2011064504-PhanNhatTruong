package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String showAllCategory(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/list";
    }
    @GetMapping("/add")
    public String addCategoryForm(Model model){
        model.addAttribute("categories", new Category());
        return "category/add";
    }
    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("categories") Category category, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "category/add";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String updateCategory(@PathVariable(value = "id") Long id, Model model){
        Category categoryEdit = categoryService.getCategoryById(id);
        model.addAttribute("categories",categoryEdit);
        return "category/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable(value = "id") Long id, @Valid @ModelAttribute("categories") Category cateUpdate, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "redirect:categories/edit" + id;
        }
        categoryService.saveCategory(cateUpdate);
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(value = "id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}

