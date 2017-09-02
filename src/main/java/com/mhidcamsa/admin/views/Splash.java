package com.mhidcamsa.admin.views;

import javax.swing.*;
import java.awt.*;


public class Splash extends JFrame {

    private JLabel imglabel;
    private ImageIcon img;
    private JProgressBar pbar;
    Thread t = null;

    public Splash(){



        super("Splash");

        img = new ImageIcon("src/main/resources/img/logo.png");

        pbar = new JProgressBar();
        pbar.setMinimum(0);
        pbar.setMaximum(100);
        pbar.setStringPainted(true);
        pbar.setForeground(Color.LIGHT_GRAY);

        pbar.setPreferredSize(new Dimension(310, 50));
        pbar.setBounds(0, 290, 404, 20);

        add(pbar);

        setSize(404, 310);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        imglabel = new JLabel(img);

        imglabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        add(imglabel);
        setLayout(null);



        pbar.setValue(1);

        Thread t = new Thread(){

            public void run(){

                int i = 0;

                while (i < 100){

                    pbar.setValue(i);
                    try {

                        sleep(50);

                    }
                    catch (InterruptedException e){

                        e.printStackTrace();

                    }

                    i++;

                }

            }

        };

        t.start();

    }

}
