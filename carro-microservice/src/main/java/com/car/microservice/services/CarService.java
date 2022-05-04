package com.car.microservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.microservice.entity.Car;
import com.car.microservice.repository.ICarRepository;

@Service
public class CarService {

	@Autowired
	private ICarRepository carRepository;
	
	public List<Car> getAll(){
		return carRepository.findAll();
	}
	
	public Car getCarById(int id) {
		return carRepository.findById(id).orElse(null);
	}
	
	public Car save(Car car) {
		Car newCar = carRepository.save(car);
		return newCar;
	}
	
	public List<Car> findByUserId(int userId){
		return carRepository.findByUserId(userId);
	}
}
