package id.ac.ui.cs.advprog.eshop.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Order {
	String id;
	List<Product> products;
	Long orderTime;
	String author;
	@Setter
	String status;
	
	public Order(String id, List<Product> products, Long orderTime, String author) {
	}
	
	public Order(String id, List<Product> products, Long orderTime, String author, String status) {
	}

	public String getId() {
		return id;
	}

	public List<Product> getProducts() {
		return products;
	}
	

	public String getAuthor() {
		return author;
	}
	
	public Long getOrderTime() {
		return orderTime;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String s) {
		status = s;
	}
}
