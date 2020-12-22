package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping
    public ModelAndView showProducts(Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @RequestMapping("product/{id}")
    public ModelAndView getProduct(@PathVariable("id") Long id) {
        Product product = productService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("product", product);
        return modelAndView;
    }
}
