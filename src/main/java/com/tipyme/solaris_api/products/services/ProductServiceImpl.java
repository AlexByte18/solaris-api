package com.tipyme.solaris_api.products.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tipyme.solaris_api.products.Product;
import com.tipyme.solaris_api.products.dto.ProductResponseDto;
import com.tipyme.solaris_api.products.mapper.ProductMapper;
import com.tipyme.solaris_api.products.repository.ProductRepository;
import com.tipyme.solaris_api.shared.exceptions.ResourceNotFoundException;
import com.tipyme.solaris_api.users.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductResponseDto> findAll(String name, Pageable pageable) {
        Page<Product> productsPage;

        if (name != null && !name.trim().isEmpty()) {
            productsPage = productRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            productsPage = productRepository.findAll(pageable);
        }

        List<ProductResponseDto> productResponseDtos = productsPage.getContent().stream()
            .map(productMapper::toResponseDto)
            .toList();

        return new PageImpl<>(productResponseDtos, pageable, productsPage.getTotalElements());
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Product with id " + id + " not found")
        );
    }
    
}
