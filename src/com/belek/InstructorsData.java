
package com.belek;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/*
 * Stores instructors data
 */

public class InstructorsData extends AbstractTableModel {
    public List<Object[]> instructors = new ArrayList<Object[]>();
    private StudentsData studentsData;

    private int instructorID = 0;

    String[] columnNames = {"ID","Name", "Surname", "Phone Number", "Tax ID", "SSN", "Students"};

    public InstructorsData() {
        super();
    }

    @Override
    public int getRowCount() {
        return instructors.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return instructors.get(rowIndex)[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        instructors.get(rowIndex)[columnIndex] = aValue;
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

    public void addInstructor(String name, String surname, String phoneNumber, String taxID, String ssn) {
        Object[] temp;
        // read from the Students file, in order to get their IDs
        try {
            studentsData = new StudentsData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            studentsData.students = (List<Object[]>) ois.readObject();
            ois.close();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        // Saving IDs into the stringBuilder
        StringBuilder sb = new StringBuilder();
        for (Object s: studentsData.getAllId()) {
            sb.append(s);
        }
        // If the table is empty, first id will 0
        if (instructors.size() == 0) {
            temp = new Object[]{instructorID ,name, surname, phoneNumber , taxID, ssn, sb.toString()};
        }
        // If not first id will be last raw ID +1
        else {
            int tempint = (int) instructors.get(instructors.size() - 1)[0];
            temp = new Object[]{tempint+1 ,name, surname, phoneNumber , taxID, ssn,sb.toString() };

        }
        instructors.add(temp);
        instructorID += 1;
    }
    //delete the raw in the table
    //search by id number
    public void deleteInstructor (String id) {
        int intID = Integer.parseInt(id);
        for(int i = 0; i < instructors.size(); i++) {
            int temp_id = (int) instructors.get(i)[0];
            if(temp_id == intID) {
                instructors.remove(i);
            }
        }
    }
    // getting all ID numbers from the instructor table
    public List<Object> getAllId() {
        List<Object> temp = new ArrayList<>();
        for (Object[] car : instructors) {
            temp.add(car[0]);
        }
        return temp;
    }
}