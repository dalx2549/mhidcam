package com.mhidcamsa.admin.views;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import com.mhidcamsa.admin.controllers.PiscinasController;
import com.mhidcamsa.admin.models.Piscina;
import com.mhidcamsa.admin.views.*;
import com.mhidcamsa.admin.views.productos.Balanceados;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class Main {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton balanceadosButton;
    private JButton bacteriasButton;
    private JButton fertilizantesButton;
    private JButton combustiblesButton;
    private JButton desinfectantesButton;
    private JButton vitaminasButton;
    private JButton buttonPiscina1;
    private JButton buttonPiscina2;
    private JButton buttonPiscina3;
    private JButton buttonPiscina4;
    private JButton buttonPiscina5;
    private JButton buttonPiscina6;
    private JButton buttonPiscina7;
    private JButton buttonPiscina8;
    private JButton buttonPiscina9;
    private JPanel panelPiscina1;
    private JLabel labelHa1;
    private JLabel labelHa2;
    private JLabel labelHa3;
    private JLabel labelHa4;
    private JLabel labelHa5;
    private JLabel labelHa6;
    private JLabel labelHa7;
    private JLabel labelHa8;
    private JLabel labelHa9;
    private JLabel labelEs1;
    private JLabel labelEs2;
    private JLabel labelEs3;
    private JLabel labelEs4;
    private JLabel labelEs5;
    private JLabel labelEs6;
    private JLabel labelEs7;
    private JLabel labelEs8;
    private JLabel labelEs9;


    public Main(){

        setIcons();

        try {
            setTamanos();
            setEstados();
        }catch (SQLException e){
            e.printStackTrace();
        }


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

        buttonPiscina1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Corridas corridas = new Corridas("1");
                corridas.setContentPane(corridas.Corridas);
                corridas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                corridas.pack();
                corridas.setVisible(true);

            }
        });

        buttonPiscina2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });
    }

    private void setIcons(){

        IconFontSwing.register(FontAwesome.getIconFont());

        Icon corridasIcon = IconFontSwing.buildIcon(FontAwesome.LIST_ALT, 32);

        buttonPiscina1.setIcon(corridasIcon);
        buttonPiscina2.setIcon(corridasIcon);
        buttonPiscina3.setIcon(corridasIcon);
        buttonPiscina4.setIcon(corridasIcon);
        buttonPiscina5.setIcon(corridasIcon);
        buttonPiscina6.setIcon(corridasIcon);
        buttonPiscina7.setIcon(corridasIcon);
        buttonPiscina8.setIcon(corridasIcon);
        buttonPiscina9.setIcon(corridasIcon);

    }

    private void setTamanos() throws SQLException{

        String[] tamanos = PiscinasController.getTamanos();

        labelHa1.setText(tamanos[0]);
        labelHa2.setText(tamanos[1]);
        labelHa3.setText(tamanos[2]);
        labelHa4.setText(tamanos[3]);
        labelHa5.setText(tamanos[4]);
        labelHa6.setText(tamanos[5]);
        labelHa7.setText(tamanos[6]);
        labelHa8.setText(tamanos[7]);
        labelHa9.setText(tamanos[8]);

    }

    private void setEstados() throws SQLException{

        String[] estados = PiscinasController.getEstados();

        labelEs1.setText(estados[0]);
        labelEs2.setText(estados[1]);
        labelEs3.setText(estados[2]);
        labelEs4.setText(estados[3]);
        labelEs5.setText(estados[4]);
        labelEs6.setText(estados[5]);
        labelEs7.setText(estados[6]);
        labelEs8.setText(estados[7]);
        labelEs9.setText(estados[8]);

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
