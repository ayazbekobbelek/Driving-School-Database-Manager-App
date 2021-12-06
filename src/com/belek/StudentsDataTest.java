package com.belek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class StudentsDataTest {
    StudentsData studentsData;
    @BeforeEach
    public void setUp(){
        studentsData = new StudentsData();
        studentsData.addStudent("Belek", "Ayazbekov", "+36205732904",
                0,0,"Active");
    }

    @Test
    @DisplayName("New data should be added at the end of the list")
    void addStudent() {
        studentsData.addStudent("Belek", "Ayazbekov", "+36205732904",
                0,0,"Active");
        assertEquals("[1, Belek, Ayazbekov, +36205732904, 0, 0, 2021-12-02, Active]",
                Arrays.toString(studentsData.students.get(studentsData.students.size()-1)));
        studentsData.addStudent("Abdel", "Akmasadykov", "+36205832904",
                0,0,"Active");
        assertEquals("[2, Abdel, Akmasadykov, +36205832904, 0, 0, 2021-12-02, Active]",
                Arrays.toString(studentsData.students.get(studentsData.students.size()-1)));
    }

    @Test
    @DisplayName("After deletion, data should not appear in the table")
    void deleteStudent() {
        // Add some data to the table
        studentsData.addStudent("Belek", "Ayazbekov", "+36205732904",
                0,0,"Active");
        studentsData.addStudent("Abdel", "Akmasadykov", "+36205832904",
                0,0,"Active");
        //delete the last student
        studentsData.deleteStudent("2");

        assertEquals("[1, Belek, Ayazbekov, +36205732904, 0, 0, 2021-12-02, Active]",
                Arrays.toString(studentsData.students.get(studentsData.students.size()-1)),
                "Now last element should be equal to the element that we added first in this test");
    }

    @Test
    @DisplayName("Get all the ID's from the table")
    void getAllId() {
        studentsData.addStudent("Belek", "Ayazbekov", "+36205732904",
                0,0,"Active");
        studentsData.addStudent("Abdel", "Akmasadykov", "+36205832904",
                0,0,"Active");


        List<Object> list = new ArrayList<>() {};
        list.add(0);
        list.add(1);
        list.add(2);
        assertEquals(list, studentsData.getAllId(),
                "three elements is table -> the result should be [0,1,2]");
        studentsData.addStudent("Abdel", "Akmasadykov", "+36205832904",
                0,0,"Active");
        list.add(3);
        assertEquals(list, studentsData.getAllId(),
                "four elements is table -> the result should be [0,1, 2, 3]");
        studentsData.deleteStudent("0");
        list.remove(0);
        assertEquals(list, studentsData.getAllId(),
                "we deleted the first row of the table, so the result now should be [1, 2, 3]");


    }
}