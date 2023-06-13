package com.workspace.shopping.product;

import com.workspace.shopping.product.ProductDTO;
import com.workspace.shopping.product.Product;
import com.workspace.shopping.product.ProductMapper;
import com.workspace.shopping.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductDTO save(ProductDTO productDTO) {
        Product product = productRepository.save(productMapper.toEntity(productDTO));
        return productMapper.toDto(product);
    }

    public List<ProductDTO> findProducts() {
        List<Product> products = productRepository.findAll();


        List<ProductDTO> responseProducts = products.stream()
                .map(product -> productMapper.toDto(product))
                .collect(Collectors.toList());
        return responseProducts;

    }
}
