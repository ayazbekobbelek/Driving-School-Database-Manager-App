package com.belek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

public class InstructorDatabase extends JFrame implements Database {
    private InstructorsData data = new InstructorsData();

    @Override
    public void initComponents() {
        this.setLayout(new BorderLayout());
        //-------------------------Table-------------------------
        JTable jTable = new JTable(data);
        jTable.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(jTable);
        this.add(sp, BorderLayout.EAST);
        //--------------------------------------------------

        // --------Menu bar---------------------------
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Go to");
        JMenuItem m1 = new JMenuItem("Student Database");
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentDatabase studentDatabase = new StudentDatabase();
            }
        });
        JMenuItem m2  = new JMenuItem("Car Database");
        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CarDatabase carDatabase = new CarDatabase();
            }
        });
        menu.add(m1); menu.add(m2);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        //--------------------------------------------------------------------


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //-------------------------Labels--------------------------------------------------

        JLabel id = new JLabel("ID: ");
        id.setBounds(30,15,300,30);

        JLabel l1 = new JLabel("Name: ");
        l1.setBounds(30,50, 300,30);

        JLabel l2 = new JLabel("Surname: ");
        l2.setBounds(30,85, 300,30);

        JLabel l3 = new JLabel("Phone Number: ");
        l3.setBounds(30,120, 300,30);

        JLabel l5 = new JLabel("Tax ID: ");
        l5.setBounds(30,155, 300,30);

        JLabel l6 = new JLabel("SSN: ");
        l6.setBounds(30,190, 300,30);

        //-------------------------TextFields--------------------------------------------------

        JTextField idTextField = new JTextField();
        idTextField.setBounds(130, 15, 190,30 );

        JTextField nameField = new JTextField(20);
        nameField.setBounds(130, 50, 180, 30);

        JTextField surnameField = new JTextField(6);
        surnameField.setBounds(130, 85, 180, 30);

        JTextField phoneNumberField = new JTextField(20);
        phoneNumberField.setBounds(130, 120, 180, 30);

        JTextField taxIdField = new JTextField(10);
        taxIdField.setBounds(130, 155, 180, 30);

        JTextField ssn = new JTextField(10);
        ssn.setBounds(130, 190, 180, 30);

        //----------------------------------------------------------------------------------------------------


        //-------------------------Buttons--------------------------------------------------


        JButton addButton = new JButton("New Instructor");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addInstructor(nameField.getText(),
                        surnameField.getText(),
                        phoneNumberField.getText(),
                        taxIdField.getText(),
                        ssn.getText());
                data.fireTableDataChanged();
            }
        });
        addButton.setBounds(30, 225, 200, 30);


        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.deleteInstructor(idTextField.getText());
                data.fireTableDataChanged();
            }
        });
        deleteButton.setBounds(30, 260, 200, 30);

        //----------------------------------------------------------------------------------------------------

        JLabel fill = new JLabel();
        panel.add(id); panel.add(idTextField);
        panel.add(l1); panel.add(nameField);
        panel.add(l2); panel.add(surnameField);
        panel.add(l3); panel.add(phoneNumberField);
        panel.add(l5); panel.add(taxIdField);
        panel.add(l6); panel.add(ssn);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(fill);
        this.add(panel);
        this.setVisible(true);

    }

    public InstructorDatabase(){
        super("Instructor Database");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // read data from the instructor.dat file

        try {
            data = new InstructorsData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("instructors.dat"));
            data.instructors = (List<Object[]>) ois.readObject();
            ois.close();

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        // Save data at shutdown:
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("instructors.dat"));
                    oos.writeObject(data.instructors);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("instructors.dat"));
                    oos.writeObject(data.instructors);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // create window
        setMinimumSize(new Dimension(800, 400));
        initComponents();
        pack();

    }


}

