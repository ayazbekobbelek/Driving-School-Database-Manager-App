package com.belek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstructorsDataTest {
    InstructorsData instructorsData;
    @BeforeEach
    public void setUp(){
        instructorsData = new InstructorsData();
        instructorsData.addInstructor("Belek", "Ayazbekov", "+36205732904",
                "1234","1234123");
    }

    @Test
    @DisplayName("New data should be added at the end of the list")
    void addInstructor() {
        instructorsData.addInstructor("Belek", "Ayazbekov", "+36205732904",
                "1234","1234123");
        assertEquals("[1, Belek, Ayazbekov, +36205732904, 1234, 1234123, 01]",
                Arrays.toString(instructorsData.instructors.get(instructorsData.instructors.size()-1)));
        instructorsData.addInstructor("Abdel", "Akmasadykov", "+36205832904",
                "1234","1234123");
        assertEquals("[2, Abdel, Akmasadykov, +36205832904, 1234, 1234123, 01]",
                Arrays.toString(instructorsData.instructors.get(instructorsData.instructors.size()-1)));
    }

    @Test
    void deleteInstructor() {
        // Add some data to the table
        instructorsData.addInstructor("Belek", "Ayazbekov", "+36205732904",
                "1234","1234123");
        instructorsData.addInstructor("Abdel", "Akmasadykov", "+36205832904",
                "1234","1234123");
        //delete the last student
        instructorsData.deleteInstructor("2");

        assertEquals("[1, Belek, Ayazbekov, +36205732904, 1234, 1234123, 01]",
                Arrays.toString(instructorsData.instructors.get(instructorsData.instructors.size()-1)),
                "Now last element should be equal to the element that we added first in this test");
    }

    @Test
    void getAllId() {
        instructorsData.addInstructor("Belek", "Ayazbekov", "+36205732904",
                "1234","1234123");
        instructorsData.addInstructor("Abdel", "Akmasadykov", "+36205832904",
                "1234","1234123");
        List<Object> list = new ArrayList<>() {};
        list.add(0);
        list.add(1);
        list.add(2);
        assertEquals(list, instructorsData.getAllId(),
                "three elements is table -> the result should be [0,1,2]");
        instructorsData.addInstructor("Abdel", "Akmasadykov", "+36205832904",
                "1234","1234123");
        list.add(3);
        assertEquals(list, instructorsData.getAllId(),
                "four elements is table -> the result should be [0,1, 2, 3]");
        instructorsData.deleteInstructor("0");
        list.remove(0);
        assertEquals(list, instructorsData.getAllId(),
                "we deleted the first row of the table, so the result now should be [1, 2, 3]");

    }
}