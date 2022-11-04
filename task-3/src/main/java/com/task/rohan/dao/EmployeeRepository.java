package com.task.rohan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.rohan.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByLocation(String location);

	List<Employee> findBySalaryGreaterThan(double salary);
	
}
