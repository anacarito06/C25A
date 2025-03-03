package com.parquesoftti.c26a.service;

import com.parquesoftti.c26a.model.Order;
import com.parquesoftti.c26a.model.Product;
import com.parquesoftti.c26a.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{
    final  ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {

        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public Product update(Long id, Product product) {
        Product productsTmp = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("product not found"));
        productsTmp.setProductName(product.getProductName());
        productsTmp.setProductId(product.getProductId());
        productsTmp.setPrice(product.getPrice());
        return productRepository.save(productsTmp);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void delete(Long id) {
       productRepository.deleteById(id);
    }

    @Override
    public Product findByName(String name) {
        return null;
    }


}
