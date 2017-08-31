package com.mhidcamsa.admin.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mhidcamsa.admin.views.*;
import com.mhidcamsa.admin.views.productos.Balanceados;

public class Main {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton balanceadosButton;
    private JButton bacteriasButton;
    private JButton fertilizantesButton;
    private JButton combustiblesButton;
    private JButton desinfectantesButton;
    private JButton vitaminasButton;


    public Main(){


        balanceadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Balanceados.main(null);

            }
        });
    }


    public static void main(String[] args) {


        JFrame frame = new JFrame("Main");

        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

    }
}
