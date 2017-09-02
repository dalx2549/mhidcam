package com.mhidcamsa.admin.views.periodos;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import com.mhidcamsa.admin.controllers.BalanceadoController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PeriodosForm {

    static final BigDecimal LIBRAS = new BigDecimal(2.20);

    private JPanel Periodos;
    private JComboBox comboBoxBalanceado;
    private JFormattedTextField formattedTextCantBalanceado;
    private JComboBox comboBox1;
    private JFormattedTextField formattedTextField2;
    private JComboBox comboBox2;
    private JFormattedTextField formattedTextField3;
    private JComboBox comboBox3;
    private JFormattedTextField formattedTextField4;
    private JFormattedTextField formattedTextField5;
    private JComboBox comboBox4;
    private JButton buttonAgregarBalanceado;
    private JFormattedTextField formattedTextField6;
    private JTextArea textArea1;
    private JButton buttonAgregarFertilizante;
    private JButton buttonAgregarBacterias;
    private JTable tableGastos;
    private JButton buttonAgregarDesinfectante;
    private JButton buttonAgregarVitamina;
    private JLabel labelBalanceado;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JComboBox comboBox5;
    private JFormattedTextField formattedTextField1;
    private JButton buttonAgregarCombustible;
    private JButton buttonAgregarGastoV;


    private BigDecimal subTotalBalanceado;
    private String[] ids;
    private BigDecimal[] precioUnit;
    private int filaSeleccionada;

    public PeriodosForm() {

        setButtonIcons();

        updateBalanceadoComboBox();

        eliminarButton.setEnabled(false);

        DefaultTableModel tableModel = new DefaultTableModel();
        String[] columnNames = {"id","Producto", "Nombre / Tipo", "Cantidad", "Precio Unit.","Subtotal"};
        for (int i = 0; i < columnNames.length; i++){

            tableModel.addColumn(columnNames[i]);

        }

        tableGastos.setModel(tableModel);

        subTotalBalanceado = new BigDecimal(0.00);

/*        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("====================================");
                System.out.println(comboBoxBalanceado.getSelectedIndex());
                System.out.println(ids[comboBoxBalanceado.getSelectedIndex()]);
                System.out.println(comboBoxBalanceado.getSelectedItem());
                System.out.println("====================================");
            }
        });*/

        tableGastos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()){

                    filaSeleccionada = tableGastos.getSelectedRow();
                    eliminarButton.setEnabled(true);

                    if (tableModel.getRowCount() == 0){

                        eliminarButton.setEnabled(false);

                    }

                }

            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (filaSeleccionada > -1){

                    if(tableModel.getValueAt(filaSeleccionada,1) == "Balanceado"){

                        subTotalBalanceado = subTotalBalanceado.subtract(new BigDecimal(tableModel.getValueAt(filaSeleccionada, 5).toString()));

                        labelBalanceado.setText(subTotalBalanceado.toString());

                    }

                    tableModel.removeRow(filaSeleccionada);

                }

            }
        });

        buttonAgregarBalanceado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object [] row = new Object[tableGastos.getColumnCount()];

                row[0] = ids [comboBoxBalanceado.getSelectedIndex()];
                row[1] = "Balanceado";
                row[2] = comboBoxBalanceado.getSelectedItem();
                row[3] = formattedTextCantBalanceado.getText();
                row[4] = precioUnit[comboBoxBalanceado.getSelectedIndex()];
                row[5] = precioUnit[comboBoxBalanceado.getSelectedIndex()].multiply(new BigDecimal(formattedTextCantBalanceado.getText()));

                tableModel.addRow(row);

                subTotalBalanceado = subTotalBalanceado.add(new BigDecimal(row[5].toString()));
                labelBalanceado.setText(subTotalBalanceado.toString());

            }
        });
    }

    private void setButtonIcons(){

        IconFontSwing.register(FontAwesome.getIconFont());

        Icon agregarIcon = IconFontSwing.buildIcon(FontAwesome.PLUS_SQUARE_O, 20);
        Icon guardarIcon = IconFontSwing.buildIcon(FontAwesome.FLOPPY_O, 32);
        Icon eliminarIcon = IconFontSwing.buildIcon(FontAwesome.TRASH_O, 20);

        buttonAgregarBalanceado.setIcon(agregarIcon);
        buttonAgregarBacterias.setIcon(agregarIcon);
        buttonAgregarFertilizante.setIcon(agregarIcon);
        buttonAgregarDesinfectante.setIcon(agregarIcon);
        buttonAgregarVitamina.setIcon(agregarIcon);
        buttonAgregarCombustible.setIcon(agregarIcon);
        buttonAgregarGastoV.setIcon(agregarIcon);

        guardarButton.setIcon(guardarIcon);
        eliminarButton.setIcon(eliminarIcon);

    }

    private void updateBalanceadoComboBox(){

        Object [][] balanceados;

        BalanceadoController balanceadoController = new BalanceadoController();

        balanceados = balanceadoController.getAllData();

        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

        String value;

        ids = new String[balanceados.length];
        precioUnit = new BigDecimal[balanceados.length];

        BigDecimal precio;
        BigDecimal volumen;

        for (int i = 0; i < balanceados.length; i++){

            value = balanceados[i][1].toString() + " " + balanceados[i][2];

            ids[i] = balanceados[i][0].toString();

            precio = new BigDecimal(balanceados[i][5].toString());
            volumen = new BigDecimal(balanceados[i][4].toString());

            precio = precio.divide(LIBRAS, 2, RoundingMode.HALF_UP);

            precioUnit[i] = precio.divide(volumen, 2, RoundingMode.HALF_UP);

            comboBoxModel.addElement(value);
            System.out.println(ids[i]);

        }

        comboBoxBalanceado.setModel(comboBoxModel);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Periodos");
        frame.setContentPane(new PeriodosForm().Periodos);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }
}
