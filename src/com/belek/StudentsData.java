package com.belek;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;
import java.time.LocalDate;

/*
 * Stores student data
 */

public class StudentsData extends AbstractTableModel {
    public List<Object[]> students = new ArrayList<Object[]>();
    private InstructorsData instructorsData;
    private CarData carData;


    private int studentID = 0;

    String[] columnNames = {"ID","Name", "Surname", "Phone Number", "Car", "Instructor", "Start date", "Status"};

    public StudentsData() {
        super();
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return students.get(rowIndex)[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) return false;
        return columnIndex != 6;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        students.get(rowIndex)[columnIndex] = aValue;
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public void addStudent(String name, String surname, String phoneNumber, int carID, int instructorID, String status) {
            Object[] temp;
            // read data from the cars.dat and instructors.dat in order to get their id numbers
        try {
            carData = new CarData();
            instructorsData = new InstructorsData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cars.dat"));
            carData.cars = (List<Object[]>) ois.readObject();
            ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream("instructors.dat"));
            instructorsData.instructors = (List<Object[]>) ois2.readObject();
            ois.close();
            ois2.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        // if the table is empty first id nubmer will be 0
        if (students.size() == 0) {
            temp = new Object[]{studentID,name, surname, phoneNumber , carID, instructorID, LocalDate.now(), status};

        }
        // if not last raw id +1
        else {
            int tempint = (int) students.get(students.size() - 1)[0];
            temp = new Object[]{tempint+1, name, surname, phoneNumber , carID, instructorID, LocalDate.now(), status};
        }
        // if in car's and insturctor's database exists such as carID and instructorID
        // add the data to the table
        if (carData.getAllId().contains(carID) && instructorsData.getAllId().contains(instructorID)) {
            students.add(temp);
            studentID += 1;
        }
        // if not show the error message
        else if (!carData.getAllId().contains(carID)) {
            JOptionPane.showMessageDialog(null,"No such car");
        }
        else if (!instructorsData.getAllId().contains(instructorID)) {
            JOptionPane.showMessageDialog(null,"No such instructor");
        }
        else JOptionPane.showMessageDialog(null,"Error!");

    }

    //delete raw

    public void deleteStudent (String id) {
        int intID = Integer.parseInt(id);
        for(int i = 0; i < students.size(); i++) {
            int temp_id = (int) students.get(i)[0];
            if(temp_id == intID) {
                students.remove(i);
            }
        }
    }

    // Get all id number of students

    public List<Object> getAllId() {
        List<Object> temp = new ArrayList<>();
        for (Object[] car : students) {
            temp.add(car[0]);
        }
        return temp;
    }


}

