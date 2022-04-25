package com.assignment.cargarage.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@Data
@Builder
@NoArgsConstructor
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id")
    private Long id;

    @NotNull
    private String carMake;
    
    @NotNull
    private String carType;
    
    
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "car_parts",
//            joinColumns = @JoinColumn(name = "car_id"),
//            inverseJoinColumns = @JoinColumn(name = "part_id"))
//    private List<Part> parts;

    
//    public Car()
//    {
//    }
//
    public Car(Long id, String carMake, String carType)
    {
    	this.id = id;
        this.carMake = carMake;
        this.carType = carType;
    }


	public Long getCarId()
    {
        return id;
    }

    public void setCarId(Long id)
    {
        this.id = id;
    }

	public String getCarMake() {
		return carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

//	public List<Part> getParts()
//    {
//        return parts;
//    }
//
//    public void setParts(List<Part> parts)
//    {
//        this.parts = parts;
//    }  
    
}
