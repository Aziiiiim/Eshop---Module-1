package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.GenericService;

@Controller
@RequestMapping("/car")
class CarController extends GenericController<Car>{
	@Autowired
    private CarServiceImpl service;

    protected Car getEntity() {
        return new Car();
        
    }protected GenericService<Car> getService() {
        return service;
    }

	@Override
	protected String getSingularEntityName() {
		return "Car";
	}

	@Override
	protected String getPluralEntityName() {
		return "Cars";
	}
	
	

}
