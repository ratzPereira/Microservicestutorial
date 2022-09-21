package com.ratz.productservice.service;

import com.ratz.productservice.dto.ProductRequestDTO;
import com.ratz.productservice.dto.ProductResponseDTO;
import com.ratz.productservice.model.Product;

import java.util.List;

public interface ProductService {

    void createProduct(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> getAllProducts();
}
