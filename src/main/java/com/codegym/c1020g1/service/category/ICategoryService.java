package com.codegym.c1020g1.service.category;

import com.codegym.c1020g1.model.Category;
import com.codegym.c1020g1.service.IService;

public interface ICategoryService extends IService<Category> {
    Category findByName(String name);
}
