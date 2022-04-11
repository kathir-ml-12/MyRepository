package org.mgmt.system.organization;

import java.util.List;

import javax.validation.Valid;

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
@RequestMapping("/organization")
public class OrganizationController 
{
	
	@Autowired
	private OrganizationService organzationService;
	
	
	@PostMapping(value = "/addOrgDetails",  produces = "application/xml")
	public ResponseEntity<Organization> addOrg(@Valid @RequestBody Organization org)
	{
		Organization organizaion = organzationService.addOrg(org);
		return new ResponseEntity<Organization>(organizaion, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAllOrgDetails",  produces = "application/xml")
	public ResponseEntity<List<Organization>> getAllOrganiationDetails()
	{
		List<Organization> orgList = organzationService.getAllOrgDetails();
		return new ResponseEntity<List<Organization>>(orgList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getOrgDetails/{orgId}",  produces = "application/xml")
	public ResponseEntity<Organization> getOrganiationDetails(@PathVariable int orgId)
	{
		Organization organization = organzationService.getOrgDetails(orgId).get();
		return new ResponseEntity<Organization>(organization, HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateOrgDetails",  produces = "application/xml")
	public ResponseEntity<Organization> updateOrganization(@Valid @RequestBody Organization org)
	{
		Organization organization = organzationService.updateOrgDetails(org);
		return new ResponseEntity<Organization>(organization, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/deleteOrgDetails/{orgId}")
	public ResponseEntity<Void>  deleteOrg(@PathVariable int orgId)
	{
		organzationService.deleteOrg(orgId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
