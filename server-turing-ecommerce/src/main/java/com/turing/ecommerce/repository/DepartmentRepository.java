package com.turing.ecommerce.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.turing.ecommerce.model.Department;



/**
 * Department Repository is for all data access operations for Department.
 * 
 * @author ThankGod Ukachukwu
 */
@RestResource(exported = false)
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query("select department from Department department where name =:name")
	Optional<Department> findByName(@Param("name") String name);

}
