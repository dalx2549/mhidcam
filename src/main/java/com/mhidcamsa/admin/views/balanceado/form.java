package com.mhidcamsa.admin.views.balanceado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class form {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JCheckBox líquidoCheckBox;
    private JTable tableBalanceado;
    private JButton btGuardar;
    private JPanel formContainer;
    private JButton button1;
    private JButton button2;
    private JButton button3;


    public form() {



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("It works!");

            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("form");
        frame.setContentPane(new form().formContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
