package com.moto.microservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moto.microservice.entity.Motorcycle;
import com.moto.microservice.repository.IMotoRepository;

@Service
public class MotoService {

	@Autowired
	private IMotoRepository motoRepository;
	
	public List<Motorcycle> getAll(){
		return motoRepository.findAll();
	}
	
	public Motorcycle getMotoById(int id) {
		return motoRepository.findById(id).orElse(null);
	}
	
	public Motorcycle save(Motorcycle moto) {
		Motorcycle newMoto = motoRepository.save(moto);
		return newMoto;
	}
	
	public List<Motorcycle> findByUserId(int userId){
		return motoRepository.findByUserId(userId);
	}
}
