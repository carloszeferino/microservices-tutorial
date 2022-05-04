package com.moto.microservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moto.microservice.entity.Motorcycle;
import com.moto.microservice.services.MotoService;

@RestController
@RequestMapping("/motos")
public class MotoController {
	
	@Autowired
	private MotoService motoService;

	@GetMapping
	public ResponseEntity<List<Motorcycle>> getMotorcycles(){
		List<Motorcycle> motos = motoService.getAll();
		if(motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(motos);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Motorcycle> obtainMoto(@PathVariable("id") int id){
		Motorcycle moto = motoService.getMotoById(id);
		if(moto == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(moto);
		}
	}
	
	@PostMapping
	public ResponseEntity<Motorcycle> saveMoto(@RequestBody Motorcycle moto){
		Motorcycle newMoto = motoService.save(moto);
		return ResponseEntity.ok(newMoto);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Motorcycle>> getMotosByUserId(@PathVariable ("userId") int userId){
		List<Motorcycle> motos = motoService.findByUserId(userId);
		if(motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(motos);
		}
	}
}
