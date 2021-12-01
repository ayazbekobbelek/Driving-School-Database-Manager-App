package com.belek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarDataTest {
    CarData carData;

    @BeforeEach
    public void setUp(){
        carData = new CarData();
        carData.addCar("123-123", "Mazda");
    }

    @Test
    @DisplayName("New data should be added at the end of the list")
    void addCar() {
        carData.addCar("234-234", "Mersedes");
        assertEquals("[1, 234-234, Mersedes, true]",
                Arrays.toString(carData.cars.get(carData.cars.size()-1)));
        carData.addCar("456-456", "Tesla");
        assertEquals("[2, 456-456, Tesla, true]",
                Arrays.toString(carData.cars.get(carData.cars.size()-1)));

    }

    @Test
    @DisplayName("After deletion, data should not appear in the table")
    void deleteCar() {
        carData.addCar("234-234", "Mersedes");
        carData.addCar("456-456", "Tesla");
        carData.deleteCar("2");
        assertEquals("[1, 234-234, Mersedes, true]",
                Arrays.toString(carData.cars.get(carData.cars.size()-1)),
                "Now last element should be equal to the element that we added first in this test");

    }

    @Test
    @DisplayName("Get all the ID's from the table")
    void getAllId() {
        carData.addCar("234-234", "Mersedes");
        carData.addCar("456-456", "Tesla");
        List<Object> list = new ArrayList<>() {};
        list.add(0);
        list.add(1);
        list.add(2);
        assertEquals(list, carData.getAllId(),
                "four elements is table -> the result should be [0,1, 2, 3]");
        carData.deleteCar("0");
        list.remove(0);
        assertEquals(list, carData.getAllId(),
                "we deleted the first row of the table, so the result now should be [1, 2, 3]");
    }
}