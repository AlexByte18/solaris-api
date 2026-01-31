package com.tipyme.solaris_api.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tipyme.solaris_api.products.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

    
}