package com.usuario.microservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.microservice.entity.User;
import com.usuario.microservice.models.Car;
import com.usuario.microservice.models.Motorcycle;
import com.usuario.microservice.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.getAll();
		if(users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(users);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> obtainUser(@PathVariable("id") int id){
		User user = userService.getUserById(id);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(user);
		}
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		User userNew = userService.save(user);
		return ResponseEntity.ok(userNew);
	}
	
	@GetMapping("/cars/{userId}")
	public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable("userId") int userId){
		User user = userService.getUserById(userId);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}else {
			List<Car> cars = userService.getCars(userId);
			return ResponseEntity.ok(cars);
		}
	}
	
	@GetMapping("/motos/{userId}")
	public ResponseEntity<List<Motorcycle>> getMotosByUserId(@PathVariable("userId") int userId){
		User user = userService.getUserById(userId);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}else {
			List<Motorcycle> motos = userService.getMotos(userId);
			return ResponseEntity.ok(motos);
		}
	}
	
	@PostMapping("/cars/{userId}")
	public ResponseEntity<Car> saveCarToUser(@PathVariable("userId") int userId, @RequestBody Car car){
		Car newCar = userService.saveCar(userId, car);
		return ResponseEntity.ok(newCar);
	}
	
	@PostMapping("/motos/{userId}")
	public ResponseEntity<Motorcycle> saveMotoToUser(@PathVariable("userId") int userId, @RequestBody Motorcycle moto){
		Motorcycle newMoto = userService.saveMoto(userId, moto);
		return ResponseEntity.ok(newMoto);
	}
	
	@GetMapping("/all/{userId}")
	public ResponseEntity<Map<String,Object>> listAllVehicles(@PathVariable("userId") int userId){
		Map<String,Object> resultado = userService.getUserAndVehicles(userId);
		return ResponseEntity.ok(resultado);
	}
	
}
