package com.codegym.c1020g1.service.product;

import com.codegym.c1020g1.model.Category;
import com.codegym.c1020g1.model.Product;
import com.codegym.c1020g1.repo.IProductRepository;
import com.codegym.c1020g1.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByOrderById(pageable);
    }

    @Override
    public Page<Product> findAllCategory(Category name, Pageable pageable) {
        return productRepository.findAllByCategory(name,pageable);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


}
