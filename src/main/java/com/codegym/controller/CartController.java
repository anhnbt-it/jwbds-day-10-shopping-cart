package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Hashtable;
import java.util.Map;

@Controller
@SessionAttributes("shoppingCart")
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @ModelAttribute("shoppingCart")
    public Hashtable<Long, Product> shoppingCart() {
        return new Hashtable<>();
    }

    @GetMapping
    public ModelAndView showCartLists(@ModelAttribute("shoppingCart") Hashtable<Long, Product> shoppingCart) {
        ModelAndView modelAndView = new ModelAndView("cart");
        double amount = 0;
        if (shoppingCart.size() > 0) {
            for (Map.Entry cart : shoppingCart.entrySet()) {
                amount += ((Product) cart.getValue()).getPrice() * ((Product) cart.getValue()).getQty();
            }
        }
        modelAndView.addObject("cart", shoppingCart);
        modelAndView.addObject("amount", amount);
        return modelAndView;
    }

    @GetMapping("add-product/{id}")
    public String addProduct(@PathVariable("id") Long id, @ModelAttribute("shoppingCart") Hashtable<Long, Product> shoppingCart, RedirectAttributes redirectAttributes) {
        Product product = productService.findOne(id);
        if (product != null) {
            if (shoppingCart.containsKey(id)) {
                // Neu ton tai san pham trong gio hang thi cap nhat lai so luong
                Product productInCart = shoppingCart.get(id);
                product.setQty(productInCart.getQty() + 1);
                redirectAttributes.addFlashAttribute("message", "Updated quantity success.");
            } else {
                // Them san pham vao gio hang
                product.setQty(1);
                redirectAttributes.addFlashAttribute("message", "Add to cart successfully.");
            }
            shoppingCart.put(id, product);
        } else {
            redirectAttributes.addFlashAttribute("message", "Product not found.");
        }
        return "redirect:/cart";
    }

    @GetMapping("update-product/{id}/{qty}")
    public String updateProduct(@PathVariable("id") Long id, @PathVariable("qty") Integer qty,
                                @ModelAttribute("shoppingCart") Hashtable<Long, Product> shoppingCart,
                                RedirectAttributes redirectAttributes) {
        Product product = productService.findOne(id);
        if (product != null && shoppingCart.containsKey(id)) {
            if (product.getQty() < qty || qty < 1) {
                redirectAttributes.addFlashAttribute("message", "Quantity exceeds quantity available in stock.");
            } else {
                // Neu ton tai san pham trong gio hang thi cap nhat lai so luong
                product.setQty(qty);
                redirectAttributes.addFlashAttribute("message", "Updated quantity success.");
                shoppingCart.put(id, product);
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Product not found.");
        }
        return "redirect:/cart";
    }

    @GetMapping("remove-product/{id}")
    public String removeProduct(@PathVariable("id") Long id,
                                @ModelAttribute("shoppingCart") Hashtable<Long, Product> shoppingCart,
                                RedirectAttributes redirectAttributes) {
        Product product = productService.findOne(id);
        if (product != null && shoppingCart.containsKey(id)) {
                // Neu ton tai san pham trong gio hang thi xoa
            shoppingCart.remove(id);
            redirectAttributes.addFlashAttribute("message", "Remove product success.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Product not found.");
        }
        return "redirect:/cart";
    }
}
