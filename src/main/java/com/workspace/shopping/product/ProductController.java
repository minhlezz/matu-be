package com.workspace.shopping.product;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductDTO saveProduct(@Valid @RequestBody ProductDTO requestedProduct) {
        return productService.save(requestedProduct);
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.findProducts();
    }
}
