package com.ak.shopkart.service;

import java.util.List;

import com.ak.shopkart.dto.CategoryDTO;
import com.ak.shopkart.model.Category;

public interface CategoryService {

  Category createCategory(Category category);

  List<CategoryDTO> getAllCategories();

}