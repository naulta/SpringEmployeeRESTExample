package com.kenzanexample.employeedemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kenzanexample.employeedemo.domain.Employee;
import com.kenzanexample.employeedemo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> findOne(@PathVariable Long id) {
		Optional<Employee> employee = this.employeeService.findById(id);
		Employee emp = employee.get();
		if (!employee.isPresent())
			return new ResponseEntity<Employee>(emp,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> findAll() {
	List<Employee> employees = this.employeeService.findAll();
	return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> create(@RequestBody Employee employee) {
		log.debug("Creating Employee with ID:"+employee.getID());
		employee.setStatus("ACTIVE");
		this.employeeService.save(employee);
		return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee emp) {
		Optional<Employee> employee = this.employeeService.findById(id);
		if (!employee.isPresent())
			return new ResponseEntity<Employee>(emp,HttpStatus.NOT_FOUND);
		else {
			emp.setID(id);
			this.employeeService.update(emp);
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		}
	}


	// Don't delete Employee entity - set to INACTIVE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> delete(@PathVariable Long id) {
		Optional<Employee> employee = this.employeeService.findById(id);
		Employee emp = employee.get();
		if (!employee.isPresent())
			return new ResponseEntity<Employee>(emp,HttpStatus.NOT_FOUND);
		else
			this.employeeService.delete(id);
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
}
