package com.car.microservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.microservice.entity.Car;
import com.car.microservice.services.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
	
	@Autowired
	private CarService carService;

	@GetMapping
	public ResponseEntity<List<Car>> getCars(){
		List<Car> cars = carService.getAll();
		if(cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(cars);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Car> obtainCar(@PathVariable("id") int id){
		Car car = carService.getCarById(id);
		if(car == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(car);
		}
	}
	
	@PostMapping
	public ResponseEntity<Car> saveCar(@RequestBody Car car){
		Car newCar = carService.save(car);
		return ResponseEntity.ok(newCar);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable ("userId") int userId){
		List<Car> cars = carService.findByUserId(userId);
		if(cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(cars);
		}
	}
}
