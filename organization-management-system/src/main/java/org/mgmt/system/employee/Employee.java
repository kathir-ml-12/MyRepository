package org.mgmt.system.employee;

import javax.persistence.*;

import org.mgmt.system.organization.Organization;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "employee")
public class Employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String emailId;
	private String firstName;
	private String lastName;
	@ManyToOne(cascade = CascadeType.PERSIST)
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
