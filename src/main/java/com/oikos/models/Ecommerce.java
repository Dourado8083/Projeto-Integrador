package com.oikos.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Ecommerce {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ecommerceId;

	private String ecommerceName;

	private String ecommercePic;
	
	private String ecommerceHeader;

	@OneToOne(mappedBy = "ecommerce")
	@JsonIgnoreProperties({ "businessMessages", "ecommerce" })
	private Business businessOn;

	@OneToMany(mappedBy = "ecommerceOn")
	private List<Product> productList = new ArrayList<Product>();

	public long getEcommerceId() {
		return ecommerceId;
	}

	public void setEcommerceId(long ecommerceId) {
		this.ecommerceId = ecommerceId;
	}

	public Business getBusinessOn() {
		return businessOn;
	}

	public void setBusinessOn(Business businessOn) {
		this.businessOn = businessOn;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public String getEcommercePic() {
		return ecommercePic;
	}

	public void setEcommercePic(String ecommercePic) {
		this.ecommercePic = ecommercePic;
	}

	public String getEcommerceName() {
		return ecommerceName;
	}

	public void setEcommerceName(String ecommerceName) {
		this.ecommerceName = ecommerceName;
	}

	public String getEcommerceHeader() {
		return ecommerceHeader;
	}

	public void setEcommerceHeader(String ecommerceHeader) {
		this.ecommerceHeader = ecommerceHeader;
	}

}
