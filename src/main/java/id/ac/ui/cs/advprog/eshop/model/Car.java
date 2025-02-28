package id.ac.ui.cs.advprog.eshop.model;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Car implements Item{
	private String carId;
	private String carName;
	private String carColor;
	private int carQuantity;
	
	public String getId() {
		return carId;
	}
	
	public void setId(String id) {
		carId = id;
	}
	
	public String getName() {
		return carName;
	}
	
	public void setName(String name) {
		carName = name;
	}
	
	public String getColor() {
		return carColor;
	}
	
	public void setColor(String color) {
		carColor = color;
	}
	
	public int getQuantity() {
		return carQuantity;
	}

	public void setQuantity(int quantity) {
		carQuantity= quantity;
	}
}
