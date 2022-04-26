package com.assignment.garage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.garage.dto.Car;

@Repository
public interface CarRepo extends JpaRepository<Car,Long>{

}
