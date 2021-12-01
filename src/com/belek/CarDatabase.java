package com.belek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

public class CarDatabase extends JFrame implements Database {
    private CarData data = new CarData();


    @Override
    public void initComponents() {
        this.setLayout(new BorderLayout());
        JTable jTable = new JTable(data);
        jTable.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(jTable);
        this.add(sp, BorderLayout.EAST);

        // --------Menu bar---------------------------
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Go to");
        JMenuItem m1 = new JMenuItem("Instructor Database");
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InstructorDatabase instructorDatabase = new InstructorDatabase();
            }
        });
        JMenuItem m2  = new JMenuItem("Student Database");
        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentDatabase studentDatabase = new StudentDatabase();
            }
        });
        menu.add(m1); menu.add(m2);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        //--------------------------------------------------------------------



        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel id = new JLabel("ID: ");
        id.setBounds(30,15,300,30);

        JLabel l1 = new JLabel("License Plate: ");
        l1.setBounds(30,50, 300,40);

        JLabel l2 = new JLabel("Brand: ");
        l2.setBounds(30,85, 300,40);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(130, 15, 190,30 );

        JTextField licenseField = new JTextField(20);
        licenseField.setBounds(130, 50, 180, 30);

        JTextField brandField = new JTextField(6);
        brandField.setBounds(130, 85, 180, 30);


        JButton addButton = new JButton("New Car");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addCar(licenseField.getText(),
                        brandField.getText());
                data.fireTableDataChanged();
            }
        });
        addButton.setBounds(30, 120, 200, 30);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.deleteCar(idTextField.getText());
                data.fireTableDataChanged();
            }
        });
        deleteButton.setBounds(30, 155, 200, 30);

        JLabel fill = new JLabel();
        panel.add(id); panel.add(idTextField);
        panel.add(l1); panel.add(licenseField);
        panel.add(l2); panel.add(brandField);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(fill);
        this.add(panel);
        this.setVisible(true);

    }

    public CarDatabase(){
        super("Car Database");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        try {
            data = new CarData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cars.dat"));
            data.cars = (List<Object[]>) ois.readObject();
            ois.close();

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        // Save data at shutdown:
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cars.dat"));
                    oos.writeObject(data.cars);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cars.dat"));
                    oos.writeObject(data.cars);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });




        setMinimumSize(new Dimension(800, 400));
        initComponents();
        pack();

    }


}

