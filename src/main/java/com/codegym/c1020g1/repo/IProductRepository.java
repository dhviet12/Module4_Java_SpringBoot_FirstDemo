package com.codegym.c1020g1.repo;

import com.codegym.c1020g1.model.Category;
import com.codegym.c1020g1.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderById(Pageable pageable);
    Page<Product> findAllByCategory(Category category, Pageable pageable);
}
