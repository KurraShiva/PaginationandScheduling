package com.productInventory.dto;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.opencsv.bean.CsvBindByName;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prodId;

    @CsvBindByName(column = "prodName")
    private String prodName;

    @CsvBindByName(column = "prodPrice")
    private double prodPrice;

    @CsvBindByName(column = "prodQuantity")
    private int prodQuantity;

    @CsvBindByName(column = "prodCategory")
    private String prodCategory;
    
   public  Product(){
   
    }
    
    public Product(int prodId, String prodName, double prodPrice, int prodQuantity, String prodCategory) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodQuantity = prodQuantity;
		this.prodCategory = prodCategory;
	}
    
    
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getProdQuantity() {
		return prodQuantity;
	}
	
	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}
	public String getProdCategory() {
		return prodCategory;
	}
	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}   
}