package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestBody Product newProduct) {
        return cartService.addProduct(newProduct);
    }

    @GetMapping("/get-cart-items")
    public List<Product> getCartItems() {
        return cartService.getCartItems();
    }

    @DeleteMapping("/remove-product-by-name/{name}")
    public String removeProductByName(@PathVariable ("name") String name) {
        return cartService.removeProductByName(name);
    }

    @GetMapping("/calculate-cart-total")
    public double calculateCartTotal() {
        return cartService.calculateCartTotal();
    }

    @GetMapping("/calculate-tax")
    public double calculateTax() {
        return cartService.calculateTax();
    }

    @GetMapping("/cart-total-with-tax")
    public double cartTotalWithTax() {
        return cartService.cartTotalWithTax();
    }

}