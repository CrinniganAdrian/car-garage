package controllerTests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.assignment.cargarage.controllers.CarGarageController;
import com.assignment.cargarage.dto.Car;
import com.assignment.cargarage.exceptions.CarBadResponseException;
import com.assignment.cargarage.exceptions.CarNotFoundException;
import com.assignment.cargarage.repositories.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

class CarGarageControllerTest {

	//private Car car;
	
	@Autowired
    MockMvc mockMvc;
	
    @Autowired
    ObjectMapper mapper;
    
    @MockBean
    CarRepository carRepository;
    
    
    private List<Car> carList;
    
//    @BeforeEach
//    public void setUp() {
//    	System.out.println("TestSetup");
//    	this.carList.add(new Car(1L, "Ford","Fuel"));                               
//    	this.carList.add(new Car(2L, "Toyota","Hybrid"));                               
//    	this.carList.add(new Car(3L, "Honda","Electric"));
//    }
    
    Car carOne = new Car(1l,"Ford","Fuel");
    Car carTwo = new Car(2l,"Toyota","Hybrid");
    Car carThree = new Car(3l,"Honda","Electric");
    
    
      
    
    @Test
    public void getCars_success() throws Exception {
        List<Car> cars = new ArrayList<>(Arrays.asList(carOne, carTwo, carThree));
        
        Mockito.when(carRepository.findAll()).thenReturn(carList);
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("/car")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].carMake", is("Honda")));
    }
    
    
    @Test
    public void getCarById_success() throws Exception {
        Mockito.when(carRepository.findById(carOne.getCarId())).thenReturn(java.util.Optional.of(carOne));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/car/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.carMake", is("Ford")));
    }
    
    
    @Test
    public void createCar_success() throws Exception {
        Car car = Car.builder()
                .carMake("VWGolf")
                .carType("Fuel")
                .build();

        Mockito.when(carRepository.save(car)).thenReturn(car);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(car));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.carMake", is("VWGolf")));
        }
    
    
    @Test
    public void updateCar_nullId() throws Exception {
    	Car car = Car.builder()
                .carMake("VWGolf")
                .carType("Fuel")
                .build();

        Mockito.when(carRepository.save(car)).thenReturn(car);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(car));


        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                    assertTrue(result.getResolvedException() instanceof CarBadResponseException))
        .andExpect(result ->
            assertEquals("Car or ID must not be null!", result.getResolvedException().getMessage()));
        }

    @Test
    public void updateCar_notFound() throws Exception {
    	Car car = Car.builder()
                .carMake("VWGolf")
                .carType("Fuel")
                .build();

        Mockito.when(carRepository.save(car)).thenReturn(car);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(car));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                    assertTrue(result.getResolvedException() instanceof CarNotFoundException))
        .andExpect(result ->
            assertEquals("Car with ID 5 does not exist.", result.getResolvedException().getMessage()));
    }
    
    @Test
    public void deleteCarById_success() throws Exception {
        Mockito.when(carRepository.findById(carOne.getCarId())).thenReturn(Optional.of(carTwo));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/car/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCarById_notFound() throws Exception {
        Mockito.when(carRepository.findById(5l)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/car/2")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof CarNotFoundException))
        .andExpect(result ->
                assertEquals("Car with ID 5 does not exist.", result.getResolvedException().getMessage()));
    }
}



