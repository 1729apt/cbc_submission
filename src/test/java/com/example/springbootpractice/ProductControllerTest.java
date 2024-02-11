package com.example.springbootpractice;

import com.example.springbootpractice.controller.ProductController;
import com.example.springbootpractice.dto.DiscountTaxDto;
import com.example.springbootpractice.model.Product;
import com.example.springbootpractice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testAddProduct() throws Exception {
   
        Product product = new Product();
        product.setName("Test Product");

       
        mockMvc.perform(post("/product/addProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isCreated());

        verify(productService, times(1)).createProduct(product);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        
        Product product1 = new Product();
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setName("Product 2");

        List<Product> productList = Arrays.asList(product1, product2);

        when(productService.getProducts()).thenReturn(productList);

       
        mockMvc.perform(get("/product/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[1].name").value("Product 2"));
    }

    @Test
    public void testApplyDiscountOrTax() throws Exception {
       
        int productId = 1;
        DiscountTaxDto discountDto = new DiscountTaxDto();
        discountDto.setRate(10.0);
        discountDto.setDiscount(true);

        Product existingProduct = new Product();
        existingProduct.setPrice(100.0);

        when(productService.applyDiscountOrTax(productId, discountDto)).thenReturn(existingProduct);

        
        mockMvc.perform(post("/product/{productId}/apply-discount-or-tax", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(discountDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price").value(90.0));
    }


}
