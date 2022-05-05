package com.usuario.microservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.usuario.microservice.models.Car;


@FeignClient(name = "car-microservice")
@RequestMapping("/cars")
public interface ICarFeignClient {

	@PostMapping
	public Car save(@RequestBody Car car);
	
	@GetMapping("/user/{userId}")
	public List<Car> getCars(@PathVariable("userId") int userId);
	
}
