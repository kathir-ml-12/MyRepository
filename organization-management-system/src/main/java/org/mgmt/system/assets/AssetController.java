package org.mgmt.system.assets;

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
@RequestMapping("/asset")
public class AssetController {
	
	@Autowired
	private AssetService assetService;
	
	@PostMapping(value = "/org/{orgId}/addAsset", produces = "application/xml")
	public ResponseEntity<Assets> addAsset(@PathVariable int orgId, @RequestBody Assets asset)
	{
		Assets assets = assetService.addAsset(orgId, asset);
		return new ResponseEntity<Assets>(assets, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAsset/{assetId}", produces = "application/xml" )
	public ResponseEntity<Assets> getAssetDetails(@PathVariable int assetId)
	{
		Assets assets = assetService.getAssetDetails(assetId).get();
		return new ResponseEntity<Assets>(assets, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllAssets", produces = "application/xml" )
	public ResponseEntity<List<Assets>> getAllAssetDetails()
	{
		List<Assets> assetList = assetService.getAllAssetDetails();
		return new ResponseEntity<List<Assets>>(assetList, HttpStatus.OK);
	}
	
	@PutMapping(value = "/org/{orgId}/updateAsset", produces = "application/xml")
	public ResponseEntity<Assets> updateAssetIdDetails(@PathVariable int orgId, @RequestBody Assets asset)
	{
		Assets assets = assetService.updateAssetDetails(asset, orgId);
		return new ResponseEntity<Assets>(assets, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/deleteAsset/{assetId}", produces = "application/xml")
	public ResponseEntity<Void> deleteAsset(@PathVariable int assetId)
	{
		assetService.deleteAsset(assetId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
