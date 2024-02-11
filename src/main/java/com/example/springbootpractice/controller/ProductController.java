package com.example.springbootpractice.controller;

import com.example.springbootpractice.dto.DiscountTaxDto;
import com.example.springbootpractice.dto.ProductRequestDto;
import com.example.springbootpractice.model.Product;
import com.example.springbootpractice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/product") 
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully!");
    }

    @PostMapping("/addProductList")
    public ResponseEntity<String> addProductList(@RequestBody List<Product> productList) {
        productService.addProducts(productList);
        return ResponseEntity.status(HttpStatus.CREATED).body("List of products added successfully!");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with Id: " + id + " deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
        boolean idFound = productService.updateProduct(id, product);
        if (idFound) {
            return ResponseEntity.ok("Product with Id: " + id + " updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with Id: " + id + " not found");
        }
    }
    
    @PostMapping("/{productId}/apply-discount-or-tax")
    public ResponseEntity<Product> applyDiscountOrTax(
            @PathVariable int productId,
            @RequestBody DiscountTaxDto discountOrTax) {
        try {
            Product updatedProduct = productService.applyDiscountOrTax(productId, discountOrTax);
            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
