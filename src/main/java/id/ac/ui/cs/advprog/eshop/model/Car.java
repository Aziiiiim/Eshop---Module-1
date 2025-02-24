package id.ac.ui.cs.advprog.eshop.model;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Car {
	private String carId;
	private String carName;
	private String carColor;
	private int carQuantity;
	
	public String getCarId() {
		return carId;
	}
	
	public void setCarId(String id) {
		carId = id;
	}
	
	public String getCarName() {
		return carName;
	}
	
	public void setCarName(String name) {
		carName = name;
	}
	
	public String getCarColor() {
		return carColor;
	}
	
	public void setCarColor(String color) {
		carColor = color;
	}
	
	public int getCarQuantity() {
		return carQuantity;
	}

	public void setCarQuantity(int quantity) {
		carQuantity= quantity;
	}
}
