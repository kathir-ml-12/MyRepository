package org.mgmt.system.organization;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ColumnTransformer;
import org.mgmt.system.assets.Assets;
import org.mgmt.system.employee.Employee;

@Entity
@Table(name = "organization")
public class Organization 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orgid;
	
	@NotEmpty
    private String orgName;
	
	@NotEmpty
	@ColumnTransformer(read = "UPPER(location)")
    private String location;
	
	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Employee> employees = new HashSet<Employee>();
	
	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Assets> assets = new HashSet<Assets>();
	
    
	public int getOrgid() {
		return orgid;
	}
	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	public Set<Assets> getAssets() {
		return assets;
	}
	public void setAssets(Set<Assets> assets) {
		this.assets = assets;
	}
    
}
