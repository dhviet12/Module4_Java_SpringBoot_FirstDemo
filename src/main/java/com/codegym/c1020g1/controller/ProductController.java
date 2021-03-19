package com.codegym.c1020g1.controller;

import com.codegym.c1020g1.model.Category;
import com.codegym.c1020g1.model.Product;
import com.codegym.c1020g1.service.category.ICategoryService;
import com.codegym.c1020g1.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @ModelAttribute("listCategory")
    public List<Category> list() {
        return (List<Category>) categoryService.findAll();
    }

    @GetMapping()
    public ModelAndView showAll(@PageableDefault(size = 5) Pageable pageable) {
        return new ModelAndView("index", "list", productService.findAll(pageable));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Product> delete(@PathVariable Long id) {
//        productService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id) {
        productService.delete(id);
        return new ModelAndView("redirect:/products");

    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Product product) {
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute Product product) {
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }

    @PostMapping("/search")
    public ModelAndView searchByCategory(@PageableDefault(size = 5) Pageable pageable, @ModelAttribute Category category) {
        ModelAndView modelAndView = new ModelAndView("search");
        Page<Product> list = productService.findAllCategory(category, pageable);
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView user(){
        return new ModelAndView("user");
    }


}
