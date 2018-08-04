package com.kenzanexample.employeedemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kenzanexample.employeedemo.domain.Employee;
import com.kenzanexample.employeedemo.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	
  private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Optional<Employee> findById(Long id) {
		return this.employeeRepository.findActiveByID(id);
	}
	
	public List<Employee> findAll() {
		return this.employeeRepository.findAllActive();
	}

	public void save(Employee employee) {
		log.debug(employee.toString());
		this.employeeRepository.save(employee);
	}
	
	public void update(Employee employee) {
		Optional <Employee> oldEmployee = this.findById(employee.getID());
		Employee emp=oldEmployee.get();
		if (employee.getFirstName() == null)
			employee.setFirstName(emp.getFirstName());
		
		if (employee.getLastName() == null)
			employee.setLastName(emp.getLastName());
		
		if (employee.getMiddleInitial() == null)
			employee.setMiddleInitial(emp.getMiddleInitial());
		
		if (employee.getDateOfBirth() == null)
			employee.setDateOfBirth(emp.getDateOfBirth());
		
		if (employee.getDateOfEmployment() == null)
			employee.setDateOfEmployment(emp.getDateOfEmployment());
		
		// No status change for update request - this is handled by delete
		employee.setStatus("ACTIVE");
		this.save(employee);
	}
	
	public void delete(Long ID) {
		Optional <Employee> emp = this.findById(ID);
		Employee employee=emp.get();
		//Don't delete record - set to inactive and save
		employee.setStatus("INACTIVE");
		this.save(employee);
	}	
	
}
	
	
	