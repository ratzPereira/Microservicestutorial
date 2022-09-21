package com.ratz.productservice.service.impl;

import com.ratz.productservice.dto.ProductRequestDTO;
import com.ratz.productservice.dto.ProductResponseDTO;
import com.ratz.productservice.model.Product;
import com.ratz.productservice.repository.ProductRepository;
import com.ratz.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public void createProduct(ProductRequestDTO productRequestDTO) {

        Product product = Product.builder()
                .name(productRequestDTO.getName())
                .price(productRequestDTO.getPrice())
                .description(productRequestDTO.getDescription())
                .build();

        productRepository.save(product);

        log.info("Product {} saved to database", product.getId());
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapProductToResponse).toList();
    }

    private ProductResponseDTO mapProductToResponse(Product product) {

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
