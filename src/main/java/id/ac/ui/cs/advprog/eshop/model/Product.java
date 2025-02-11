package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
	private static int lastId = -1;
	private String productId;
	private String productName;
	private int productQuantity;
	
	public Product() {
		productId = ""+ ++lastId;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String id) {
		this.productId = id;
	}
	
	public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int quantity) {
        this.productQuantity = quantity;
    }
	
}
