package com.ak.shopkart.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.shopkart.dto.CategoryDTO;
import com.ak.shopkart.mapper.CategoryMapper;
import com.ak.shopkart.model.Category;
import com.ak.shopkart.repo.CategoryRepo;
import com.ak.shopkart.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepo categoryRepo;

  @Override
  public Category createCategory(Category category) {
    return categoryRepo.save(category);
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    return CategoryMapper.toDTOList(categoryRepo.findAll());
  }
}
