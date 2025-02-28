package id.ac.ui.cs.advprog.eshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;

@Controller
@RequestMapping("/car")
class CarController{
	@Autowired
	private CarServiceImpl carservice;
	
	@GetMapping("/createCar")
	public String createCarPage(Model model){
		Car car = new Car();
		model.addAttribute("car",car);
		return "CreateCar";
	}
	
	@PostMapping("/createCar")
	public String createCarPost(@ModelAttribute Car car, Model model){
		carservice.create(car);
		return "redirect:/car/listCar";
	}
	
	@GetMapping("/listCar")
	public String carListPage(Model model){
		List<Car> allCars = carservice.findAll();
		model.addAttribute("cars", allCars != null ? allCars : new ArrayList<>());
		return "CarList";
	}
	
    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable("carId") String carId, Model model) {
        Car car = carservice.findById(carId);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carservice.update(car.getCarId(), car);
        return "redirect:/car/listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carservice.deleteById(carId);
        return "redirect:/car/listCar";
    }
}
