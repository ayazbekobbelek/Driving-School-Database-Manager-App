package com.belek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainMenu extends JFrame {
    public void adminMainMenu() {
        JPanel adminMainMenu = new JPanel();
        this.setLayout(new BorderLayout());
        JButton students_database = new JButton("Students Database");
        students_database.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentDatabase studentDatabase = new StudentDatabase();

            }
        });
        JButton instructors_database = new JButton("Instructors Database");
        instructors_database.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                InstructorDatabase instructorDatabase = new InstructorDatabase();
            }
        });
        JButton cars_database = new JButton("Cars database");
        cars_database.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CarDatabase carDatabase = new CarDatabase();
            }
        });

        adminMainMenu.add(students_database); adminMainMenu.add(instructors_database); adminMainMenu.add(cars_database);
        this.add(adminMainMenu, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    public AdminMainMenu(){
        super("Main menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        adminMainMenu();
        pack();
    }
}
