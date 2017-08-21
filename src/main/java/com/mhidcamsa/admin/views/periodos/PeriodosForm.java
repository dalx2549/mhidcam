package com.mhidcamsa.admin.views.periodos;

import com.github.lgooddatepicker.components.DatePicker;
import com.mhidcamsa.admin.controllers.BalanceadoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PeriodosForm {
    private JPanel Periodos;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JTextField textField1;
    private JTable tableBalanceados;
    private JButton agregarButton;


    public PeriodosForm() {

        updateTablaBalanceado();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



    }

    private void updateTablaBalanceado(){

        String[] colNames = {"id","Marca", "Tipo", "Precio/Libra"};

        BalanceadoController balanceadoController = new BalanceadoController();

        Object[][] dataBal;

        dataBal = balanceadoController.getAllData();

        for (int i = 0; i < dataBal.length; i++){

            BigDecimal precio = new BigDecimal(dataBal[i][5].toString());
            double peso = Double.parseDouble(dataBal[i][4].toString());

            BigDecimal pesoBig = new BigDecimal(peso);

            BigDecimal precioResult = precio.divide(pesoBig);

            precioResult = precioResult.setScale(2, RoundingMode.CEILING);

            dataBal[i][3] = String.valueOf(precioResult);

            System.out.println(precioResult);

        }

        DefaultTableModel datosBalanceado = new DefaultTableModel(dataBal, colNames);

        tableBalanceados.setModel(datosBalanceado);

        TableColumn idHidden = tableBalanceados.getColumn("id");
        idHidden.setPreferredWidth(0);
        idHidden.setMinWidth(0);
        idHidden.setMaxWidth(0);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PeriodosForm");
        frame.setContentPane(new PeriodosForm().Periodos);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
