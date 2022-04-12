package org.mgmt.system.employee;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.mgmt.system.organization.Organization;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "employee")
public class Employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Email
	private String emailId;
	
	@NotEmpty(message = "First name must not be empty")
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name="org_id", referencedColumnName = "orgid", columnDefinition = "int")
    private Organization organization;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	


}
