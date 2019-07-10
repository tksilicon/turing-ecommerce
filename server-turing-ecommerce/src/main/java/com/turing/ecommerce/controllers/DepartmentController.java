package com.turing.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.turing.ecommerce.exceptions.DepartmentNotFoundException;
import com.turing.ecommerce.model.Department;
import com.turing.ecommerce.service.DepartmentService;



/**
 * Department Controller for all Rest APIs related to department.
 * 
 * @author ThankGod Ukachukwu
 *
 */

@RequiredArgsConstructor
@Slf4j

@RestController
public class DepartmentController {

	@Resource(name = "departmentImplService")
	private DepartmentService departmentService;

	/*
	 * API to return all departments
	 */
	@GetMapping(path = "/api/departments")
	public ResponseEntity<List<Department>> getAll() {
		return ResponseEntity.ok(departmentService.getAll());
	}

	/*
	 * API to return a department
	 */
	@GetMapping(path = "/api/departments/{department-id}")
	public ResponseEntity<Optional<Department>> getDepartmentById(
			@PathVariable(name = "department-id", required = true) Integer departmentId) {
		Optional<Department> department = departmentService.findById(departmentId);

		if (!department.isPresent()) {
			//log.error("Department Id " + departmentId + " is not existing");
			//return ResponseEntity.notFound().build();
			
			throw new DepartmentNotFoundException(departmentId);
		}

		return ResponseEntity.ok(department);

	}

}
