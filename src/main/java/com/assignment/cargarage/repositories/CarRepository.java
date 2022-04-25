package com.assignment.cargarage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cargarage.dto.Car;


@Repository
public interface CarRepository extends JpaRepository<Car,Long>
{
}
