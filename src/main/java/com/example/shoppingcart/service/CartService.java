package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    private final ProductRepository productRepository;
    private final double taxRate = 22.0;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addProduct(Product newProduct) {
        productRepository.save(newProduct);
        System.out.println("Product " + newProduct.getName() + " has been added to the cart." + getCartItems());

        return "Product " + newProduct.getName() + " has been added to the cart. Current cart items: " + getCartItems();
    }

    public List<Product> getCartItems() {

        return productRepository.findAll();
    }

    public String removeProductByName(String name) {
        if (productRepository.existsByName(name)) {
            productRepository.deleteByName(name);
        }
        return "Product " + name + " has been removed from the cart.";
    }

    // @Scheduled(fixedRate = 10000)
    public double calculateCartTotal() {
        List<Product> cartItems = getCartItems();
        double total = 0;
        for (Product product : cartItems) {
            total += product.calculateTotalPrice();
        }
        System.out.println(total);
        return total;
    }

    public double calculateTax() {
        return calculateCartTotal() * taxRate / 100;
    }

    public double cartTotalWithTax() {
        return calculateCartTotal() + calculateTax();
    }
}
