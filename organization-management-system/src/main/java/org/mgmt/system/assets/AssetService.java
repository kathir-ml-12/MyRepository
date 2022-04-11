package org.mgmt.system.assets;

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
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private OrganizationRepository OrgRepository;
	
	public Assets addAsset(int orgId, Assets asset)
	{
		try
		{
			Organization org = OrgRepository.findById(orgId).get();
			asset.setOrganization(org);
			Optional<Assets> asst = assetRepository.findById(asset.getAssetId());
			if(!asst.isPresent())
				return assetRepository.save(asset);
			else
				throw new RecordAlreadyPresentException(
						"Asset with given Id: " + asset.getAssetId() + " is already present");
		}
		catch(NoSuchElementException e)
		{
			throw new RecordNotFoundException(
					"Organization with given Id: " + orgId + " does not exist");
		}
	}
	
	public Optional<Assets> getAssetDetails(int assetId)
	{
		Optional<Assets> asst = assetRepository.findById(assetId);
		if(asst.isPresent())
		   return assetRepository.findById(assetId);
		else
			throw new RecordNotFoundException(
					"Asset with given Id: " + assetId + " does not exist");
	}
	
	public List<Assets> getAllAssetDetails() {
		List<Assets> assetList = assetRepository.findAll();
		if(!assetList.isEmpty())
		    return assetRepository.findAll();
		else
			throw new EmptyListFoundException("Record List is empty");
			
	}
	
	public Assets updateAssetDetails(Assets asset, int orgId)
	{
		Organization org = OrgRepository.findById(orgId).get();
		asset.setOrganization(org);
		return assetRepository.save(asset);
	}
	
	public void deleteAsset(int assetId)
	{
		try
		{
			assetRepository.deleteById(assetId);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new RecordNotFoundException("Given entity "+ assetId+" is not found");
		}
		
	}

	

}
