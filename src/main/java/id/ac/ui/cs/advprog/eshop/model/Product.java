package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product implements Item{
	private String productId;
	private String productName;
	private int productQuantity;
	
	public String getId() {
		return productId;
	}
	
	public void setId(String id) {
		this.productId = id;
	}
	
	public String getName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public int getQuantity() {
        return productQuantity;
    }

    public void setQuantity(int quantity) {
        this.productQuantity = quantity;
    }
}
