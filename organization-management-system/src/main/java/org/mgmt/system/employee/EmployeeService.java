package org.mgmt.system.employee;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.mgmt.system.exceptionhandler.EmptyListFoundException;
import org.mgmt.system.exceptionhandler.RecordAlreadyPresentException;
import org.mgmt.system.exceptionhandler.RecordNotFoundException;
import org.mgmt.system.organization.Organization;
import org.mgmt.system.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private OrganizationRepository OrgRepository;
    
    public Employee addEmployee(Employee employee, int orgId)
    {
        try 
        {
            Organization org = OrgRepository.findById(orgId).get();
            employee.setOrganization(org);
            Optional<Employee> emp = employeeRepository.findByemailId(employee.getEmailId());
            if(!emp.isPresent())
                return employeeRepository.save(employee);
            else
                throw new RecordAlreadyPresentException(
                        "Employee with emailId: " + employee.getEmailId() + " is already present");
        }
        catch(NoSuchElementException e)
        {
            throw new RecordNotFoundException("Organization with given ID "+orgId+ " does not exist");
        }
    }
    
    public Optional<Employee> getEmployeeDetailsById(int employeeId)
    {
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        if(emp.isPresent())
            return emp;
        else
           throw new RecordNotFoundException("Employee with given ID "+employeeId+ " does not exist");
    }
    
    public List<Employee> getAllEmployeeDetails()
    {
        List<Employee> emplList = employeeRepository.findAll();
        if(!emplList.isEmpty())
            return employeeRepository.findAll();
        else
            throw new EmptyListFoundException("Record List is empty");
        
    }
    
    public Employee updateEmployee(Employee employee, int orgId)
    {
    	Optional<Employee> emp = employeeRepository.findByemailId(employee.getEmailId());
        if(emp.isPresent())
        {
	    	Organization org = OrgRepository.findById(orgId).get();
	        employee.setOrganization(org);
	        return employeeRepository.save(employee);
        }
        else
        	throw new RecordNotFoundException("Employee with given ID "+employee.getEmailId()+ " does not exist");
    }
    
    public void deleteEmployee(int employeeId)
    {
        try
        {
            employeeRepository.deleteById(employeeId);
        }
        catch(EmptyResultDataAccessException e)
        {
            throw new RecordNotFoundException("Given entity "+ employeeId+" is not found");
        }
        
    }


}
