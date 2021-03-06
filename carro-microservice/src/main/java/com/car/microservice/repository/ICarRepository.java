package com.car.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.microservice.entity.Car;

@Repository
public interface ICarRepository extends JpaRepository<Car, Integer>{
	
	List<Car> findByUserId(int userId);
}
