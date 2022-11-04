package com.task.rohan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.rohan.dao.EmployeeRepository;
import com.task.rohan.exception.CustomException;
import com.task.rohan.exception.blankValueException;
import com.task.rohan.model.Employee;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository repository;
	
	
	@GetMapping("/")
	public String hello(){
		return "This is task 3";
	}
	
	
//Creating the employee:
	
//	@PostMapping("/createEmployee")
//	public String saveEmployee(@RequestBody Employee employee) {
//		repository.save(employee);
//		return "Employee is being saving";
//	}
	
	@PostMapping("/createEmployee")
	public ResponseEntity<Employee> save(@RequestBody Employee employee){

		if (employee.getName().isEmpty()) {
			throw new blankValueException("Add employee's name");
		}
		else if (String.valueOf(employee.getAge()) == null) {
			throw new blankValueException("Add employee's age");
		}
		else if (employee.getDept().isEmpty()) {
			throw new blankValueException("Add employee's department");
		}
		else if (employee.getLocation().isEmpty()) {
			throw new blankValueException("Add employee's location");
		}
		else if (String.valueOf(employee.getExperience())==null) {
			throw new blankValueException("Add employee's experience");
		}
		else if (String.valueOf(employee.getSalary())==null) {
			throw new blankValueException("Add employee's salary");
		}
		else {
			return new ResponseEntity<>(repository.save(employee),HttpStatus.CREATED);	
		}
	}
	
	

//Fetching all the records of employee:
	
	@GetMapping("/getEmployees")
	public List<Employee> getAll() {
		List<Employee> employees = repository.findAll();
		if (employees.isEmpty()) {
			throw new blankValueException("Please add some records of employee, Just do the createEmployee rest");
		}
		else {
			return employees;
		}
	}
	
//	@GetMapping("/getEmployees")
//	public ResponseEntity<List<Employee>> getAllEmployees(){
//		List<Employee> list = repository.findAll();
//		if (list.isEmpty()) {
//			throw new blankValueException("Please add some records of employee, Just do the createEmployee rest");
//		}
//		return new ResponseEntity<List<Employee>>(list, HttpStatus.FOUND);	
//	}
	
	
	
//Location Section:
	
	@GetMapping("/getEmployee/{location}")
	public List<Employee> getEmployeesByLocation(@PathVariable String location) {
		if (location.contains(null)) {
			throw new blankValueException(
					"Please add some records of employee");
		}
		else {
			return repository.findByLocation(location);}
	}
	
	@GetMapping("/groupByLocation")
	public List<Employee> findLocations(){
		List<Employee> employees = repository.findAll();
		List<Employee> empLocations = employees
										.stream()
										.filter(e -> e.getLocation().startsWith("Chennai")
												   ||e.getLocation().startsWith("Hydrabad")
												   ||e.getLocation().startsWith("Banglore")
												   ||e.getLocation().startsWith("Kolkata"))
										.collect(Collectors.toList());
		return empLocations;
	}
	
	
	
// Salary Section:
	
//	@GetMapping("/salary/groupBySalary")
//	public ResponseEntity<List<Employee>> getLaptopsByGreaterThan (@RequestParam double salary) {
//		return new ResponseEntity<List<Employee>>(repository.findBySalaryGreaterThan(salary), HttpStatus.OK);
//	}
	
	@GetMapping("/groupBySalary")
	public List<Employee> getGreaterThanSalary(@RequestParam double salary){
		return repository.findBySalaryGreaterThan(salary);
	}
	
	@GetMapping("/findBySalary")
	public List<Employee> findSalary(/*@RequestParam double salary*/){
		List<Employee> employees = repository.findAll();
		List<Employee> greaterSalary = employees
									  .stream()
									  .filter(e -> e.getSalary() > 10000 /*salary*/)
									  .collect(Collectors.toList());
		return greaterSalary;
	}
	
	
	
//Experience Section:
	
	@GetMapping("/findByExperience")
	public List<Employee> findByExperience(){
		List<Employee> employees = repository.findAll();
		List<Employee> findByExperience = employees
											.stream()
											.filter(e -> e.getSalary() > 10000 && e.getExperience() > 10)
											.collect(Collectors.toList());
		return findByExperience;
	}
	
	
	@GetMapping("modifyDepartment")
	public List<Employee> modifyDepartment(){
		List<Employee> employees = repository.findAll();
		List<Employee> modifyDepartment = new ArrayList<>();
		for(Employee employee : employees) {
			if (employee.getSalary() > 10000 && employee.getExperience() > 10) {
				employee.setDept("sr");
				modifyDepartment.add(employee);
			}
		}
		return modifyDepartment;
 	}
}
