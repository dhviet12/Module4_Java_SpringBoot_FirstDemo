package com.codegym.c1020g1.service.product;

import com.codegym.c1020g1.model.Category;
import com.codegym.c1020g1.model.Product;
import com.codegym.c1020g1.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IProductService extends IService<Product> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllCategory(Category name, Pageable pageable);
}
