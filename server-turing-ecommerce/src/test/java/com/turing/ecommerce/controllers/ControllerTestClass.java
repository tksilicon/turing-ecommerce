package com.turing.ecommerce.controllers;

import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.turing.ecommerce.DTO.CategoryDTO;
import com.turing.ecommerce.DTO.CategoryProductDTO;
import com.turing.ecommerce.model.Category;
import com.turing.ecommerce.model.Department;
import com.turing.ecommerce.model.ProductCategory;
import com.turing.ecommerce.model.ProductCategoryPK;
import com.turing.ecommerce.repository.CategoryRepository;
import com.turing.ecommerce.repository.DepartmentRepository;
import com.turing.ecommerce.service.CategoryService;
import com.turing.ecommerce.service.DepartmentService;
import com.turing.ecommerce.service.ProdCatDAOService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * DepartmentControllerTest class will test all APIs in
 * DepartmentController.java.
 *
 * @author ThankGod Ukachukwu
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerTestClass {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProdCatDAOService prodCatDAOService;

	@MockBean
	private CategoryRepository categoryRepository;

	@MockBean
	private DepartmentRepository departmentRepository;

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setup() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}

	/**
	 * Test Get a department Unit testing of controller using Mockito
	 * 
	 * @throws Exception
	 * 
	 * 
	 */

	@Test
	@Transactional
	public void testGetDepartment() throws Exception {

		// given

		Department department = new Department();

		department.setDepartmentId(1);

		department.setName("Africa");

		Optional<Department> optionalDepartment = Optional.of(department);

		given(departmentService.findById(department.getDepartmentId())).willReturn(optionalDepartment);

		// Get Department

		mockMvc.perform(

				MockMvcRequestBuilders.get("/api/departments/{department-id}", 1).accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$.departmentId").value(1));

	}

	/**
	 * 
	 * 
	 * Tests get all departments Unit testing of controller using Mockito
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetDepartments() throws Exception {

		// given

		Department department = new Department();

		department.setDepartmentId(1);

		department.setName("Africa");

		List<Department> departments = Arrays.asList(department);

		given(departmentService.getAll()).willReturn(departments);

		// when + then

		this.mockMvc.perform(get("/api/departments")).andExpect(status().isOk())

				.andExpect(content().json("[{'departmentId': 1,'name': 'Africa'}]"));

	}

	/**
	 * Test Get a department using Spring-boot TestRestTemplate Integration testing
	 * of controller using Spring Boot Test
	 * 
	 * @throws Exception
	 */

	@Test
	@Transactional
	public void canRetrieveByIdWhenExists() {

		Department department = new Department();

		department.setDepartmentId(4);
		department.setName("Nature");

		Optional<Department> optionalDepartment = Optional.of(department);
		// given
		given(departmentRepository.findById(4)).willReturn(optionalDepartment);

		// when
		ResponseEntity<Department> departmentSelect = restTemplate.getForEntity("/api/departments/4", Department.class);

		// then
		assertThat(departmentSelect.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(departmentSelect.getBody().equals(department));
	}

	/**
	 * 
	 * 
	 * Tests get all category Unit testing of category controller using Mockito
	 * 
	 * See
	 * {@linkcom.turing.ecommerce.controllers.CategoryController#getCategoryByProductId(Integer)}.
	 * 
	 * 
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetCategory() throws Exception {

		// given

		Category category = new Category();

		category.setCategoryId(1);
		category.setDepartmentId(1);
		;
		category.setName("Africa");

		List<Category> categories = Arrays.asList(category);

		given(categoryService.getAll()).willReturn(categories);

		// when + then

		this.mockMvc.perform(get("/api/categories")).andExpect(status().isOk())

				.andExpect(content().json("[{'categoryId': 1,'departmentId': 1,'name': 'Africa'}]"));

	}

	/**
	 * 
	 * 
	 * 
	 * Unit testing of category controller using Mockito
	 * 
	 * See
	 * {@linkcom.turing.ecommerce.controllers.CategoryController#getCategoryByDepartmentId(Integer)}.
	 * 
	 * 
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetCategoryByDepartmentId() throws Exception {

		// given

		CategoryDTO category = new CategoryDTO(4, 2, "Animal", "Our ever-growing selection of beautiful");
		CategoryDTO category2 = new CategoryDTO(5, 2, "Flower", "These unique and beautiful flower T-shirts are");

		List<CategoryDTO> categories = Arrays.asList(category, category2);

		given(categoryService.getCategoryByDepartmentId(2)).willReturn(categories);

		// when + then

		this.mockMvc.perform(get("/api/categories/inDepartment/{department_id}", 2)).andExpect(status().isOk())

				.andExpect(content().json(
						"[{'categoryId': 4, 'departmentId': 2, 'name': 'Animal','description': 'Our ever-growing selection of beautiful'}, "
								+ " {'categoryId': 5,'departmentId': 2, 'name': Flower, 'description': 'These unique and beautiful flower T-shirts are'} ]"));

	}

	/**
	 * 
	 * 
	 * 
	 * Unit testing of category controller using Mockito
	 * 
	 * See
	 * {@linkcom.turing.ecommerce.controllers.CategoryController#getCategoryByProductId(Integer)}.
	 * 
	 * 
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetCategoryByProductId() throws Exception {

		// given
		CategoryProductDTO cattt = new CategoryProductDTO();
		cattt.setCategoryId(1);
		cattt.setDepartmentId(1);
		cattt.setName("French");

		ProdCatDAOService mock = org.mockito.Mockito.mock(ProdCatDAOService.class);

		given(mock.findByProductId(18)).willReturn(cattt);

		// when + then
		
		
		mockMvc.perform(

				MockMvcRequestBuilders.get("/api/categories/inProduct/{product_id}", 18).accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("French"))
		        .andDo(MockMvcResultHandlers.print());

		//this.mockMvc.perform(get("/api/categories/inProduct/{product_id}", 18)).andExpect(status().isOk())
				//.andExpect(content().json("[{'categoryId':1, 'departmentId':1, 'name':'French'}]"));

	}

}
