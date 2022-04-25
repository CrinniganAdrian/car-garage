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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "parts")
@ApiModel(description = "This is the details about the part model")
public class Part {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "part_name")
    @NotNull
    @ApiModelProperty(notes = "Part name")
    @Size(min=1,max=20,message="Part name to short / long.")
    @NotBlank(message="Part name is mandatory.")
	private String partName;
	
//    @ApiModelProperty(notes = "Part price")
//    @NotNull
//    //@NotBlank(message="Part int is mandatory.")
	private int partPrice;
	
	
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_cars",
    		joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;
	
	public Part(){
		
	}
	
	public Part(Long id, String partName, int partPrice){
		this.id = id;
		this.partName = partName;
		this.partPrice = partPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public int getPartPrice() {
		return partPrice;
	}

	public void setPartPrice(int partPrice) {
		this.partPrice = partPrice;
	}
	
	@Override
	public String toString() {
		return "Part [id=" + id + ", partName=" + partName + ", partPrice=" + partPrice  + "]";
	}
	
}
