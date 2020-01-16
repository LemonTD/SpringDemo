package com.springdemo.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springdemo.employee.service.EmployeeService;
import com.springdemo.entities.Employee;

@RestController
public class EmployeeController {

	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	
	
	@PostMapping("/addEmployee")
	public Employee addEmployee(@RequestBody Employee tempEmployee ) {
		
		employeeServiceImpl.addEmployee(tempEmployee);
		
		return tempEmployee;
		
	}
	
	@GetMapping("/getEmployees")
	public List<Employee> getEmployees(){
		
		
		return employeeServiceImpl.getEmployees();
	}
	
	@GetMapping("/getEmployee/{employeeId}")
	public Employee getEmployeeById(@PathVariable int employeeId) {
		
		return employeeServiceImpl.getEmployeeById(employeeId);
	} 
	
	@PutMapping("/getEmployee/{employeeId}")
	public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee) {
		
		employee.setId(employeeId);
		
		employeeServiceImpl.updateEmployee(employee);
		
		return employee;
	} 
	
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public void deleteEmployee(@PathVariable int employeeId) {
		
		employeeServiceImpl.deleteEmployee(employeeId);
	} 
	
}
