package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Hashtable;

@Controller
@SessionAttributes("shoppingCart")
public class CartController {

    @Autowired
    private ProductService productService;

    @ModelAttribute("shoppingCart")
    public Hashtable<Long, Product> shoppingCart() {
        return new Hashtable<>();
    }

    @GetMapping("cart")
    public ModelAndView showCartLists(@ModelAttribute("shoppingCart") Hashtable<Long, Product> shoppingCart) {
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("cart", shoppingCart);
        return modelAndView;
    }

    @GetMapping("add-to-cart/{id}")
    public String addToCart(@PathVariable("id") Long id, @ModelAttribute("shoppingCart") Hashtable<Long, Product> shoppingCart, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("cart");
        Product product = productService.findOne(id);
        if (product != null) {
            if (shoppingCart.containsKey(id)) {
                System.out.println("Updated quantity success");
                // Neu ton tai san pham trong gio hang thi cap nhat lai so luong
                Product productInCart = shoppingCart.get(id);
                product.setQty(productInCart.getQty() + 1);
                redirectAttributes.addFlashAttribute("message",   "Updated quantity success.");
            } else {
                System.out.println("Add to cart successfully");
                // Them san pham vao gio hang
                product.setQty(1);
                redirectAttributes.addFlashAttribute("message",   "Add to cart successfully.");
            }
            shoppingCart.put(id, product);
        } else {
            redirectAttributes.addFlashAttribute("message", "Product not found.");
        }
        return "redirect:/cart";
    }
    // remove cart
    // update cart
}
