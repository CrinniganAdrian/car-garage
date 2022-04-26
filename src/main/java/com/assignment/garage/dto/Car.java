package com.assignment.garage.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "cars")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    @NonNull
    @Column(name = "car_make")
    private String carMake;
    
    @NonNull
    @Column(name = "car_type")
    private String carType;

//	public Long getCarId()
//    {
//        return carId;
//    }
//
//    public void setCarId(Long id)
//    {
//        this.carId = id;
//    }
//
//	public String getCarMake() {
//		return carMake;
//	}
//
//	public void setCarMake(String carMake) {
//		this.carMake = carMake;
//	}
//
//	public String getCarType() {
//		return carType;
//	}
//
//	public void setCarType(String carType) {
//		this.carType = carType;
//	}
    
}
