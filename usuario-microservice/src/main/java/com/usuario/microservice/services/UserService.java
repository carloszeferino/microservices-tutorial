package com.usuario.microservice.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.microservice.entity.User;
import com.usuario.microservice.feignclients.ICarFeignClient;
import com.usuario.microservice.feignclients.IMotoFeignClient;
import com.usuario.microservice.models.Car;
import com.usuario.microservice.models.Motorcycle;
import com.usuario.microservice.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private ICarFeignClient carFeignClient;
	
	@Autowired
	private IMotoFeignClient motoFeignClient;
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User save(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}
	
	//REST TEMPLATE
	public List<Car> getCars(int userId){
		List<Car> cars = restTemplate.getForObject("http://localhost:8082/cars/user/"+ userId, List.class);
		return cars;
	}
	
	public List<Motorcycle> getMotos(int userId){
		List<Motorcycle> motos = restTemplate.getForObject("http://localhost:8083/motos/user/"+ userId, List.class);
		return motos;
	}
	
	//FEIGN CLIENT
	public Car saveCar(int userId, Car car) {
		car.setUserId(userId);
		Car newCar = carFeignClient.save(car);
		return newCar;
	}
	
	public Motorcycle saveMoto(int userId, Motorcycle moto) {
		moto.setUserId(userId);
		Motorcycle newMoto = motoFeignClient.save(moto);
		return newMoto;
	}
	
	public Map<String, Object> getUserAndVehicles(int userId){
		Map<String,Object> resultado = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);
		if(user==null) {
			resultado.put("Message", "User does not exist.");
		}else {
			resultado.put("User", user);
			List<Car> cars = carFeignClient.getCars(userId);
			if(cars.isEmpty()) {
				resultado.put("Cars", "User does not have cars.");
			}else {
				resultado.put("Cars", cars);
			}
			List<Motorcycle> motos = motoFeignClient.getMotos(userId);
			if(motos.isEmpty()) {
				resultado.put("Motos", "User does not have motos.");
			}else {
				resultado.put("Motos", motos);
			}
			
		}
		return resultado;
	}
}
