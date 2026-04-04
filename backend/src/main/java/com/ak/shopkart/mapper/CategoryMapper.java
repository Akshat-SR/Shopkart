package com.ak.shopkart.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ak.shopkart.dto.CategoryDTO;
import com.ak.shopkart.model.Category;

public class CategoryMapper {

  public static CategoryDTO toDTO(Category category) {

    CategoryDTO dto = new CategoryDTO();
    dto.setId(category.getId());
    dto.setName(category.getName());

    return dto;
  }

  public static List<CategoryDTO> toDTOList(List<Category> categories) {

    List<CategoryDTO> list = new ArrayList<>();

    for (Category category : categories) {
      list.add(toDTO(category));
    }

    return list;
  }
}