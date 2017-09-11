package com.mhidcamsa.admin.views;

import com.github.lgooddatepicker.components.DatePicker;
import com.mhidcamsa.admin.controllers.CorridasController;
import com.mhidcamsa.admin.views.periodos.PeriodosForm;
import com.mhidcamsa.admin.models.Corrida;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Corridas extends JFrame{

    private JTable tableCorridas;
    private JButton eliminarButton;
    private JButton finalizarCorridaButton;
    private JButton verDetallesButton;
    private JTextField textField1;
    private JButton crearButton;
    public JPanel Corridas;
    private DatePicker fechaInicioPicker;
    private JLabel labelPiscina;

    private String idPiscina;

    public Corridas(String idPiscina) {

        this.idPiscina = idPiscina;

        try {
            updateTabla();
        }catch (SQLException e){
            e.printStackTrace();
        }

        labelPiscina.setText(idPiscina);

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(fechaInicioPicker.getDate().toString());

                Date date = Date.valueOf(fechaInicioPicker.getDate().toString());

                Corrida newCorrida = new Corrida(date, true, idPiscina);

                try {
                    CorridasController.newCorrida(newCorrida);

                    updateTabla();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }



            }
        });

    }

    private void updateTabla () throws SQLException{

        String[] colNames = {"id","Fecha Inicio","Fecha Fin", "Estado", "Piscina" };

        CorridasController corridasController = new CorridasController();

        Object[][] dataCorridas;

        dataCorridas = corridasController.getCorridas(idPiscina);

        DefaultTableModel datosCorrida = new DefaultTableModel(dataCorridas, colNames);

        tableCorridas.setModel(datosCorrida);

        TableColumn idHidden = tableCorridas.getColumn("id");
        idHidden.setPreferredWidth(0);
        idHidden.setMinWidth(0);
        idHidden.setMaxWidth(0);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Corridas");
        frame.setContentPane(new Corridas("0").Corridas);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }
}
