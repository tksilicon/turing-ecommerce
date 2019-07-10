package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.model.Department;
import com.turing.ecommerce.repository.DepartmentRepository;


/**
 * DepartmentServiceImpl for department related handling.
 * @author ThankGod Ukachukwu
 *
 */
@Service("departmentImplService")
public class DepartmentServiceImpl implements DepartmentService {
	
	
  @Autowired
  private DepartmentRepository departmentRepository;

  

	@Override
	public Optional<Department> findByName(String name) {
		
		return departmentRepository.findByName(name);
	}

	@Override
	public Optional<Department> findById(Integer departmentId) {
		
		return departmentRepository.findById(departmentId);
	}

	@Override
	public List<Department> getAll() {
		
		return departmentRepository.findAll();
	}

}
