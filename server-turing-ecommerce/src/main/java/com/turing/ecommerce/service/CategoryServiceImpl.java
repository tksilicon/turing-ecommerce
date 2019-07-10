package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.DTO.CategoryDTO;
import com.turing.ecommerce.model.Category;
import com.turing.ecommerce.model.ProductCategory;
import com.turing.ecommerce.repository.CategoryRepository;

/**
 * CategoryServiceImpl for Category related database access handling.
 * @author ThankGod Ukachukwu
 *
 */
@Service("categoryImplService")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	

	@Override
	public Optional<Category> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Category> findById(Integer departmentId) {
		
		return  categoryRepository.findById(departmentId);
	}

	@Override
	public List<Category> getAll() {
		
		return categoryRepository.findAll();
	}

	@Override
	public List<CategoryDTO> getCategoryByDepartmentId(Integer departmentId) {
		
		return categoryRepository.getCategoryByDepartmentId(departmentId);
	}


	
	

}
