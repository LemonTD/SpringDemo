package com.springdemo.controller.employee;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springdemo.employee.service.EmployeeService;
import com.springdemo.entities.Employee;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-mvc-crud-demo-servlet.xml")
@RunWith(SpringRunner.class)
public class EmployeeControllerTest {
	
	
	private MockMvc mockMvc;
	
	@Mock
	private EmployeeService employeeServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(EmployeeController.class).build();
	}

	@Test
	public void addEmployee() throws Exception{
		
		Employee theEmployee = new Employee("Michael", "husborn", "lemon@gmail.com");
		
		doNothing().when(employeeServiceImpl).addEmployee(isA(Employee.class));
	}
	
	

}
