package com.assignment.cargarage.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.cargarage.dto.Car;
import com.assignment.cargarage.dto.Part;
import com.assignment.cargarage.exceptions.CarBadResponseException;
import com.assignment.cargarage.repositories.CarRepository;
import com.assignment.cargarage.repositories.PartRepository;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/car")
public class CarGarageController {
	
	
// Part Crud methods / endpoints
    
    @Autowired
    PartRepository partRepository;

    
    @GetMapping("/parts")
    public List<Part> getParts() {
        return partRepository.findAll();
    }

    
    @GetMapping("/parts/{id}")
    public ResponseEntity<Part> getPartById(@PathVariable(value = "id") Long partId) {
        Optional<Part> part = partRepository.findById(partId);
        if(part.isPresent())
            return ResponseEntity.ok().body(part.get());
        else
            throw new CarBadResponseException("Part not found : " + partId);
    }

   
    @PostMapping("/parts")
    public Part createPart(@RequestBody Part part) {
        return partRepository.save(part);
    }
    
    
    
    @RequestMapping(value="/parts/{id}", method = RequestMethod.PUT)
	Part updatePart(@PathVariable("id") Long id, @RequestBody Part part) {
		
		Optional<Part> optionalPart = partRepository.findById(id);
		Part existingPart = optionalPart.get();
		existingPart.setPartName(part.getPartName());
		existingPart.setPartPrice(part.getPartPrice());
		Part savedPart = partRepository.save(existingPart);
		
		
		return savedPart;
	}
    

    
    @RequestMapping(value="/parts/{id}", method = RequestMethod.DELETE)
	Part deletePartById(@PathVariable("id") Long id){
		
		Optional<Part> optionalPart = partRepository.findById(id);
		Part existingPart=optionalPart.get();
		
		partRepository.delete(existingPart);
		return existingPart;
	}
    
    
    
    
    
// Car Crud methods / endpoints
    
    @Autowired
    CarRepository carRepository;

    
    @GetMapping
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @GetMapping(value = "{carId}")
    public Car getCarById(@PathVariable(value="carId") Long carId) {
        return carRepository.findById(carId).get();
    }
    
    @PostMapping
    public Car createCar(@RequestBody @Valid Car car) {
        return carRepository.save(car);
    }
    
    @PutMapping
    public Car updateCar(@RequestBody Car car) throws NotFoundException {
        if (car == null || car.getCarId() == null) {
            throw new CarBadResponseException("Car or ID must not be null!");
        }
        Optional<Car> optionalCar = carRepository.findById(car.getCarId());
        if (!optionalCar.isPresent()) {
            throw new CarBadResponseException("Car with ID " + car.getCarId() + " does not exist.");
        }
        Car existingCar = optionalCar.get();

        existingCar.setCarMake(car.getCarMake());
        existingCar.setCarType(car.getCarType());
    	
        return carRepository.save(existingCar);
    }
    
    
    @DeleteMapping(value = "{carId}")
    public void deleteCarById(@PathVariable(value = "carId") Long carId) throws NotFoundException {
        if (carRepository.findById(carId).isPresent()) {
            throw new NotFoundException("Car with ID " + carId + " does not exist.");
        }
        carRepository.deleteById(carId);
    }
}
