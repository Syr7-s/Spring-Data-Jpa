package org.kodluyoruz.controller;

import org.kodluyoruz.entities.Category;
import org.kodluyoruz.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categoryHome", method = RequestMethod.GET)
    public String getCategoryHome() {
        categoryService.categoryOperations();
        return "Category Operation is running";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String addCategory() {
        categoryService.categoryAddToTheDatabase();
        return "New category was added";
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getCategory(@PathVariable("name") String name) {
        Category category = categoryService.getCategory(name);
        if (category != null) {
            System.out.println(category);
            return category.getName() + " named category was received";
        }throw new ResponseStatusException(HttpStatus.NOT_FOUND, name+" named category is not found");
       // return name+" named category is not found";

    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String getAllCategory() {
        List<Category> categoryList = categoryService.getAllCategory();
        if (!categoryList.isEmpty()) {
            System.out.println("All categeries are getting.");
            Arrays.stream(categoryList.toArray()).forEach(System.out::println);
            return "All categories have been brought.";
        }throw new ResponseStatusException(HttpStatus.NOT_FOUND,"CategoryList is empty");//return "CategoryList is empty";
    }
}
