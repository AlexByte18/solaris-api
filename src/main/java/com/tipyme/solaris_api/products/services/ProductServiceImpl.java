package com.tipyme.solaris_api.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tipyme.solaris_api.products.Product;
import com.tipyme.solaris_api.products.interfaces.ProductService;
import com.tipyme.solaris_api.products.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
}
