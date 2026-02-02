package com.tipyme.solaris_api.products.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tipyme.solaris_api.products.Product;
import com.tipyme.solaris_api.products.dto.ProductResponseDto;

public interface ProductService {
    // List<Product> findAll();

    Page <ProductResponseDto> findAll(String name, Pageable pageable);
}
