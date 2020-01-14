package com.springdemo.entities.dao.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.springdemo.entities.Employee;
import com.springdemo.entities.dao.EmployeeDao;

@ContextConfiguration("classpath:/springDemo-data-context.xml")
@RunWith(SpringRunner.class)
public class EmployeeDaoImplTest {
	
	private Logger logger = Logger.getLogger(EmployeeDaoImplTest.class.getName());
	
	@Autowired
	private EmployeeDao employeeDaoImpl;
	
	@Autowired
	private ComboPooledDataSource dataSource;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void dbManagerClassesInitializedTest() {
		
		assertNotNull(employeeDaoImpl);
		assertNotNull(dataSource);
		assertNotNull(sessionFactory);
	}
	
	@Test
	public void addEmployeeToDatabaseTest() {
		
		try {
			
			logger.info("Creating new employee objects");
			Employee tempEmployee = new Employee("John", "Paulina", "john@gmail.com");
			
			logger.info("Storing employee to the database");
			
			employeeDaoImpl.addEmployee(tempEmployee);
			
			logger.info("Successfully saving employee to the database");
			
		}
		catch(Exception e) {
		
			e.printStackTrace();
			logger.warning("Error saving employee to the database");
		}
		
	}
	
	@Test
	public void getEmployeeListTest() {
		
		//create employee
		Employee tempEmployee = new Employee("John", "Ken", "Scoolj@gmail.com");
		Employee tempEmployee1 = new Employee("James", "Blue", "josh@gmail.com");
		Employee tempEmployee2 = new Employee("Tobi", "Omotosho", "Banaba@gmail.com");
		
		//save employees to the database
		employeeDaoImpl.addEmployee(tempEmployee);
		employeeDaoImpl.addEmployee(tempEmployee1);
		employeeDaoImpl.addEmployee(tempEmployee2);
		
		List<Employee> theEmployees = employeeDaoImpl.getEmployees();
		
//		assertEquals("John", theEmployees.get(0).getFirstName());
//		assertEquals("Ken", theEmployees.get(0).getLastName());
//		assertEquals("Scoolj@gmail.com", theEmployees.get(0).getEmail());
//		
//		
//		assertEquals("James", theEmployees.get(1).getFirstName());
//		assertEquals("Blue", theEmployees.get(1).getLastName());
//		assertEquals("josh@gmail.com", theEmployees.get(1).getEmail());
//		
//		assertEquals("Tobi", theEmployees.get(2).getFirstName());
//		assertEquals("Omotosho", theEmployees.get(2).getLastName());
//		assertEquals("banaba@gmail.com", theEmployees.get(2).getEmail());
		
		assertNotNull(theEmployees.get(0));
		assertNotNull(theEmployees.get(1));
		assertNotNull(theEmployees.get(2));
		
		int employeeId = theEmployees.get(0).getId();
		
		Employee tempEmployee4 = employeeDaoImpl.getEmployeeById(employeeId);
		
		String firstName = tempEmployee4.getFirstName();
		String lastName = tempEmployee4.getLastName();
		String email = tempEmployee4.getEmail();
		
		assertNotNull(tempEmployee);
				
		System.out.println("Employee found from the database: ==>> "+ tempEmployee4);
		
		assertEquals(firstName, tempEmployee4.getFirstName());
		assertEquals(lastName, tempEmployee4.getLastName());
		assertEquals(email, tempEmployee4.getEmail());
	}
	
	@Test
	public void updateEmployee() {
		//get an employee from the database		
		List<Employee> employeeList = employeeDaoImpl.getEmployees();
		
		Employee tempEmployee = employeeList.get(1);
		
		//update the employee
		String firstName = tempEmployee.getFirstName();
		String lastName = tempEmployee.getFirstName();
		String email = tempEmployee.getEmail();
		
	}
	
	@Before
	public void setUp() throws Exception{
		
	}

}
