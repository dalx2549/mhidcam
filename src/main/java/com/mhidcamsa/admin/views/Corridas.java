package com.mhidcamsa.admin.views;

import com.mhidcamsa.admin.views.periodos.PeriodosForm;

import javax.swing.*;

public class Corridas {

    private JTable table1;
    private JButton eliminarButton;
    private JButton button2;
    private JButton verDetallesButton;
    private JTextField textField1;
    private JButton crearButton;
    public JPanel Corridas;

    public Corridas() {


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Corridas");
        frame.setContentPane(new Corridas().Corridas);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }
}
