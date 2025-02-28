package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;

@Repository
public class CarServiceImpl extends GenericService<Car>{
	private final CarRepository CarRepository;
	
	public CarServiceImpl(CarRepository CarRepository) {
        this.CarRepository = CarRepository;
        this.repository = CarRepository;
    }
}
