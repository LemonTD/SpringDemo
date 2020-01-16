package com.springdemo.entities.dao.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
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
	
	@Before
	public void setUp() throws Exception{
		
		 String username = "springdemouser";
		
		 String password = "damilare";
		
		String jdbcDriver = "com.msql.cj.jdbc.Driver";
		
		String dbUrls = "jdbc:mysql://localhost:3306/springDemoDB?useSSL=false&serverTimezone=UTC";
		
		Connection conn = null;
		
		QueryRunner queryRunner = new QueryRunner(dataSource);
		
		DbUtils.loadDriver(jdbcDriver);
		
		conn = DriverManager.getConnection(dbUrls, username, password);
		
		queryRunner.update("drop database springdemodb");
		
		queryRunner.update("create database springdemodb");
		
		queryRunner.update("use springdemodb");
		
		queryRunner.update("create table `employee`(\n" + 
				"	`id` int(11) not null auto_increment,\n" + 
				"	`first_name` varchar(45) default null,\n" + 
				"	`last_name` varchar(45) default null,\n" + 
				"	`email` varchar(45) default null,\n" + 
				"	\n" + 
				"	primary key(`id`)\n" + 
				"	\n" + 
				")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;");
		
		queryRunner.update("ALTER TABLE employee AUTO_INCREMENT = 1");
		
		DbUtils.close(conn);
		
	}
	
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
		
		List<Employee> theEmployees = createEmployeeAndSave();
		
//		//create employee
//		Employee tempEmployee = new Employee("John", "Ken", "Scoolj@gmail.com");
//		Employee tempEmployee1 = new Employee("James", "Blue", "josh@gmail.com");
//		Employee tempEmployee2 = new Employee("Tobi", "Omotosho", "Banaba@gmail.com");
//		
//		//save employees to the database
//		employeeDaoImpl.addEmployee(tempEmployee);
//		employeeDaoImpl.addEmployee(tempEmployee1);
//		employeeDaoImpl.addEmployee(tempEmployee2);
//		
//		List<Employee> theEmployees = employeeDaoImpl.getEmployees();
//		
//
//		
//		assertNotNull(theEmployees.get(0));
//		assertNotNull(theEmployees.get(1));
//		assertNotNull(theEmployees.get(2));
//		
//		int employeeId = theEmployees.get(0).getId();
//		
//		Employee tempEmployee4 = employeeDaoImpl.getEmployeeById(employeeId);
//		
//		displayEmployee(tempEmployee4);
	}
	
	@Test
	public void getEmployeeByIdTest() {
		
		List<Employee> theEmployees = createEmployeeAndSave();
		
		int employeeId = theEmployees.get(0).getId();
		
		Employee tempEmployee4 = employeeDaoImpl.getEmployeeById(employeeId);
		
		String firstName = tempEmployee4.getFirstName();
		String lastName = tempEmployee4.getLastName();
		String email = tempEmployee4.getEmail();
		
		displayEmployee(tempEmployee4);
		
		assertEquals(firstName, tempEmployee4.getFirstName());
		assertEquals(lastName, tempEmployee4.getLastName());
		assertEquals(email, tempEmployee4.getEmail());
		
	}

	private void displayEmployee(Employee tempEmployee4) {
		
		System.out.println(tempEmployee4);
		
	}
	
	@Test
	public void updateEmployee() {
		//get an employee from the database		
		//create employee
		Employee tempEmployee3 = new Employee("John", "Ken", "Scoolj@gmail.com");
		Employee tempEmployee1 = new Employee("James", "Blue", "josh@gmail.com");
		Employee tempEmployee2 = new Employee("Tobi", "Omotosho", "Banaba@gmail.com");
		
		//save employees to the database
		employeeDaoImpl.addEmployee(tempEmployee3);
		employeeDaoImpl.addEmployee(tempEmployee1);
		employeeDaoImpl.addEmployee(tempEmployee2);
		
		List<Employee> theEmployees = employeeDaoImpl.getEmployees();
		

		
		assertNotNull(theEmployees.get(0));
		assertNotNull(theEmployees.get(1));
		assertNotNull(theEmployees.get(2));
//		List<Employee> employeeList = employeeDaoImpl.getEmployees();
		
		Employee tempEmployee = theEmployees.get(0);
		
		//update the employee
		String firstName = tempEmployee.getFirstName();
		String lastName = tempEmployee.getFirstName();
		String email = tempEmployee.getEmail();
		
		System.out.println("Employee firstname ====>>> : " + firstName);
		System.out.println("Employee lastname ====>>> : " + lastName);
		System.out.println("Employee email ====>>> : " + email);
		
		tempEmployee.setLastName("Johnson");
		
		//call daoimpl to update employee
		employeeDaoImpl.updateEmployee(tempEmployee);
	}
	
	private List<Employee> createEmployeeAndSave(){
		
		//create employee
				Employee tempEmployee3 = new Employee("John", "Ken", "Scoolj@gmail.com");
				Employee tempEmployee1 = new Employee("James", "Blue", "josh@gmail.com");
				Employee tempEmployee2 = new Employee("Tobi", "Omotosho", "Banaba@gmail.com");
				
				//save employees to the database
				employeeDaoImpl.addEmployee(tempEmployee3);
				employeeDaoImpl.addEmployee(tempEmployee1);
				employeeDaoImpl.addEmployee(tempEmployee2);
				
				List<Employee> theEmployees = employeeDaoImpl.getEmployees();
				

				
				assertNotNull(theEmployees.get(0));
				assertNotNull(theEmployees.get(1));
				assertNotNull(theEmployees.get(2));

				return theEmployees;				
	}
	
	@Test
	public void deleteEmployeeTest() {
		List<Employee> theEmployees = createEmployeeAndSave();
		
		//get an employee from the list of employees
		Employee tempEmployee = theEmployees.get(0);
		
		//display employee
		displayEmployee(tempEmployee);
		
		assertNotNull(tempEmployee);
		
		employeeDaoImpl.deleteEmployee(tempEmployee.getId());
	}
	

}
