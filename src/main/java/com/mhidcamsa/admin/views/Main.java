package com.mhidcamsa.admin.views;

import javax.swing.*;
import java.awt.*;
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
    private JButton button2;
    private JButton button1;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;


    public Main(){


        balanceadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Balanceados balanceados = new Balanceados("Funciona");
                balanceados.setContentPane(balanceados.formContainer);
                balanceados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                balanceados.pack();
                balanceados.setVisible(true);

            }
        });
    }


    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame("Main");

        //Splash s = new Splash();

        //Thread t = Thread.currentThread();

        //s.setVisible(true);
        //t.sleep(5000);


        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();

        //s.dispose();

        //SwingUtilities.invokeLater(new Runnable() {
          //  @Override
            //public void run() {



                frame.setVisible(true);


        //   }
        //});



    }
}
