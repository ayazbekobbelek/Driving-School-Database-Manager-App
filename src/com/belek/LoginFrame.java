package com.belek;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

/*
 * The main window.
 */
public class LoginFrame extends JFrame {

    public void login() {
        JFrame f=new JFrame("Login");
        JLabel label1,label2;
        label1=new JLabel("Username");
        label1.setBounds(30,15, 100,30);

        label2=new JLabel("Password");
        label2.setBounds(30,50, 100,30);

        JTextField F_user = new JTextField();
        F_user.setBounds(110, 15, 200, 30);

        JPasswordField F_pass=new JPasswordField();
        F_pass.setBounds(110, 50, 200, 30);

        JButton login_but=new JButton("Login");
        login_but.setBounds(130,90,80,25);
        login_but.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){

                String username = F_user.getText();
                String password = F_pass.getText();

                if(username.equals("")) //When username field is empty
                {
                    JOptionPane.showMessageDialog(null,"Please enter username");
                }
                else if(password.equals("")) // When password field is empty
                {
                    JOptionPane.showMessageDialog(null,"Please enter password");
                }
                else { // If username and password are correct
                    if (username.equals("admin") && password.equals("admin")) {
                        f.dispose();
                        AdminMainMenu menu = new AdminMainMenu();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Incorrect username or password");
                    }

                }
            }
        });


        f.add(F_pass);
        f.add(login_but);
        f.add(F_user);
        f.add(label1);
        f.add(label2);

        f.setSize(400,180);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);

    }

    public LoginFrame() {
        super("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        login();
        pack();
    }


}


