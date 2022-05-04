package com.usuario.microservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.microservice.models.Motorcycle;

@FeignClient(name = "moto-microservice", url = "http://localhost:8083")
@RequestMapping("/motos")
public interface IMotoFeignClient {
	
	@PostMapping
	public Motorcycle save(@RequestBody Motorcycle moto);
	
	@GetMapping("/user/{userId}")
	public List<Motorcycle> getMotos(@PathVariable("userId") int userId);
}
