package org.mgmt.system.assets;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.mgmt.system.organization.Organization;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "assets")
public class Assets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int assetId;
	
	@NotEmpty
	private String assetType;
	
	@NotEmpty
	private String assetName;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonIgnore
    @JoinColumn(name="org_id", referencedColumnName = "orgid", columnDefinition = "int")
    private Organization organization;
	
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
	
	

}
