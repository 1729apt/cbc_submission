package com.example.springbootpractice.service;

import com.example.springbootpractice.dto.DiscountTaxDto;
import com.example.springbootpractice.model.Product;

import com.example.springbootpractice.repository.ProductRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public void addProducts(List<Product> productList) {
        productRepository.saveAll(productList);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public boolean updateProduct(int id, Product products) {
        boolean idFound = false;
        Product oldData = null;
        Optional<Product> optional = productRepository.findById(id);

        if (optional.isPresent()) {
            idFound = true;
            oldData = optional.get();
            if (products.getName() != null) {
                oldData.setName(products.getName());
            }
            if (products.getName() != null) {
                oldData.setName(products.getName());
            }
            if (products.getName() != null) {
                oldData.setName(products.getName());
            }
            if (products.getName() != null) {
                oldData.setName(products.getName());
            }
            if (products.getName() != null) {
                oldData.setName(products.getName());
            }

            productRepository.save(oldData);
        }

        return idFound;
    }

    @Transactional
    public Product applyDiscountOrTax(int productId, DiscountTaxDto discountOrTax) {
        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            double rate = discountOrTax.getRate();
            double price = product.getPrice();
            double modifiedPrice = discountOrTax.isDiscount() ? price - (rate * price / 100.0) : price + (rate * price / 100.0);

            product.setPrice(modifiedPrice);
            productRepository.save(product);

            return product;
        }

        return null;
    }
}
