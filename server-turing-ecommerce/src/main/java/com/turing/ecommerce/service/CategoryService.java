package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.turing.ecommerce.DTO.CategoryDTO;
import com.turing.ecommerce.model.Category;
import com.turing.ecommerce.model.Department;
import com.turing.ecommerce.model.ProductCategory;


public interface CategoryService {

	Optional<Category> findByName(String name);

	Optional<Category> findById(Integer departmentId);

	public List<Category> getAll();

	public List<CategoryDTO> getCategoryByDepartmentId(Integer departmentId);

	

}
