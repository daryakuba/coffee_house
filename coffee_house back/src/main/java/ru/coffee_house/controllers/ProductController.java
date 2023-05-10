package ru.coffee_house.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.coffee_house.models.Category;
import ru.coffee_house.models.Product;
import ru.coffee_house.services.ProductService;

import java.util.List;

class ProductsByCategory {
    public String Category;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("/products")
    public void product(@RequestBody Product product){
        service.addProduct(product);
    }

    @GetMapping("/products")
    public @ResponseBody List<Product> getAll(){
        return service.getProducts();
    }

    @GetMapping("/products/{id}")
    public @ResponseBody List<Product> getProducts(@PathVariable Long id){
        return service.getProductsByCategory(id);
    }

    @GetMapping(value = "/product/{productId}/category")
    public @ResponseBody Category getProductCategory(@PathVariable("productId") Long productId){
        return service.getCategoryByProduct(productId);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable long id){
        service.deleteProduct(id);
    }
}