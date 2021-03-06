package com.moto.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moto.microservice.entity.Motorcycle;


@Repository
public interface IMotoRepository  extends JpaRepository<Motorcycle, Integer>{
	List<Motorcycle> findByUserId(int userId);
}
