package com.codegym.c1020g1.repo;

import com.codegym.c1020g1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository  extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
