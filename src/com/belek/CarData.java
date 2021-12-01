
package com.belek;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/*
 * Stores cars data
 */

public class CarData extends AbstractTableModel {


    public List<Object[]> cars = new ArrayList<Object[]>();

    private int carID = 0;
    String[] columnNames = {"ID","License Plate", "Brand", "Usable"};


    public CarData() {
        super();
    }

    @Override
    public int getRowCount() {
        return cars.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return cars.get(rowIndex)[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        cars.get(rowIndex)[columnIndex] = aValue;
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 3) return Boolean.class;
        return String.class;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public void addCar(String licensePlate, String brand) {
        Object[] temp;
        if (cars.size() == 0) {
             temp = new Object[]{carID, licensePlate, brand, true};
        }
        else {
            int tempint = (int) cars.get(cars.size() - 1)[0];
            temp = new Object[]{tempint + 1, licensePlate, brand, true};

        }
        cars.add(temp);
        carID += 1;
    }

    public void deleteCar (String id) {
        int intID = Integer.parseInt(id);
        for(int i = 0; i < cars.size(); i++) {
            int temp_id = (int) cars.get(i)[0];
            if(temp_id == intID) {
                cars.remove(i);
            }
        }
    }

    public List<Object> getAllId() {
        List<Object> temp = new ArrayList<>();
       for (Object[] car : cars) {
           temp.add(car[0]);
       }
        return temp;
    }                                                                 
}
