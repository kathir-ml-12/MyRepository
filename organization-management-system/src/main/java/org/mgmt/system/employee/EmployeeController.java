package org.mgmt.system.employee;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService userservice;
	
	
	@PostMapping(value = "/org/{orgId}/addEmployee", produces = "application/xml")
	public ResponseEntity<Employee> addEmployee(@PathVariable int orgId, @RequestBody Employee employee)
	{
		Employee empl = userservice.addEmployee(employee, orgId);
		return new ResponseEntity<Employee>(empl, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getEmployeeById/{employeeId}", produces = "application/xml" )
	public  ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable int employeeId)
	{
		Employee employee = userservice.getEmployeeDetailsById(employeeId).get();
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllEmployee", produces = "application/xml" )
	public ResponseEntity<List<Employee>> getAllEmployeeDetails()
	{
		List<Employee> listOfAllEmps = userservice.getAllEmployeeDetails();
		return new ResponseEntity<List<Employee>>(listOfAllEmps, HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/org/{orgId}/updateEmployee", produces = "application/xml")
	public ResponseEntity<Employee> updateEmployeeDetails(@PathVariable int orgId, @RequestBody Employee employee)
	{
		Employee empl = userservice.updateEmployee(employee, orgId);
		return new ResponseEntity<Employee>(empl, HttpStatus.CREATED);
		
	}
		
	@DeleteMapping(value = "/deleteEmployee/{employeeId}", produces = "application/xml")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int employeeId)
	{
		userservice.deleteEmployee(employeeId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
