package com.tipyme.solaris_api.products.interfaces;

import java.util.List;

import com.tipyme.solaris_api.products.Product;

public interface ProductService {
    List<Product> findAll();
}
