package org.loopingdoge.acme.model;

import java.io.Serializable;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "houseprofile")
@XmlType(name = "houseProfile")
public class HouseProfile implements Serializable {

	@XmlElement(name = "minSquareFootage")
	private int minSquareFootage;
	
	@XmlElement(name = "maxSquareFootage")
	private int maxSquareFootage;
	
	@XmlElement(name = "minPrice")
	private double minPrice;
	
	@XmlElement(name = "maxPrice")
	private double maxPrice;
	
	@XmlElement(name = "addressReference")
	private Address addressReference;
	
	@XmlElement(name = "maxDistance")
	private double maxDistance;
	
	@XmlElement(name = "hasGarden")
	private boolean hasGarden;
	
	
	public HouseProfile(int minSquareFootage, int maxSquareFootage, int minPrice, int maxPrice,
			boolean hasGarden, Address addressReference, double maxKmToAddress) {
		this.minSquareFootage = minSquareFootage;
		this.maxSquareFootage = maxSquareFootage;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.hasGarden = hasGarden;
		this.addressReference = addressReference;
		this.maxDistance = maxKmToAddress;
	}
	
	public HouseProfile() {
		this.minSquareFootage = 0;
		this.maxSquareFootage = 0;
		this.minPrice = 0;
		this.maxPrice = 0;
		this.hasGarden = false;
		this.addressReference = null;
		this.maxDistance = 0;
	}
	
	public int getMinSquareFootage() {
		return minSquareFootage;
	}
	
	public int getMaxSquareFootage() {
		return maxSquareFootage;
	}
	
	public double getMinPrice() {
		return minPrice;
	}
	
	public double getMaxPrice() {
		return maxPrice;
	}
	
	public boolean getHasGarden() {
		return hasGarden;
	}
	
	public Address getAddressReference() {
		return addressReference;
	}
	
	public double getMaxDistance() {
		return maxDistance;
	}
	
	public void setMinSquareFootage(int minSquareFootage) {
		this.minSquareFootage = minSquareFootage;
	}
	
	public void setMaxSquareFootage(int maxSquareFootage) {
		this.maxSquareFootage = maxSquareFootage;
	}
	
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
	}
	
	public void setAddressReference(Address addressReference) {
		this.addressReference = addressReference;
	}
	
	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
	}

}
