package org.mgmt.system.organization;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.mgmt.system.assets.Assets;
import org.mgmt.system.employee.Employee;
import org.mgmt.system.exceptionhandler.EmptyListFoundException;
import org.mgmt.system.exceptionhandler.RecordAlreadyPresentException;
import org.mgmt.system.exceptionhandler.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
	
	@Autowired
	private OrganizationRepository organziationRepository;
	
	public Organization addOrg(Organization org)
	{
		Optional<Organization> orgn = organziationRepository.findById(org.getOrgid());
		if(!orgn.isPresent())
			return organziationRepository.save(org);
		else
			throw new RecordAlreadyPresentException(
						"Organization with given Id: " +org.getOrgid() + " is already present");
		
	}
	
	public Optional<Organization> getOrgDetails(int orgId)
	{
		Optional<Organization> orgn = organziationRepository.findById(orgId);
		if(orgn.isPresent())
		    return organziationRepository.findById(orgId);
		else
			throw new RecordNotFoundException("Organization with given ID "+orgId+ " does not exist");
	}
	
	public List<Organization> getAllOrgDetails()
	{
		List<Organization> orgList = organziationRepository.findAll();
		if(!orgList.isEmpty())
			return organziationRepository.findAll();
		else
			throw new EmptyListFoundException("Record List is empty");
		
	}
	
	public Organization updateOrgDetails(Organization org)
	{
		Optional<Organization> orgn = organziationRepository.findById(org.getOrgid());
		if(orgn.isPresent())
		{
			Set<Employee> employeeList = orgn.get().getEmployees();
			Set<Assets> assetList = orgn.get().getAssets();
			org.setAssets(assetList);
			org.setEmployees(employeeList);
			return organziationRepository.save(org);
		}
		else
			throw new RecordNotFoundException("Organization with given ID "+org.getOrgid()+ " does not exist");
		
		
	}
	
	public void deleteOrg(int orgId)
	{
		try
		{
			organziationRepository.deleteById(orgId);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new RecordNotFoundException("Given entity "+ orgId+" is not found");
		}
		
	}

}
