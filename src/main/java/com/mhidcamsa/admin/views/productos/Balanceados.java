package com.mhidcamsa.admin.views.productos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.math.BigDecimal;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import com.mhidcamsa.admin.controllers.BalanceadoController;
import com.mhidcamsa.admin.controllers.ProductosController;
import com.mhidcamsa.admin.models.Balanceado;

public class Balanceados {

    private JTextField txtfMarca;
    private JTextField txtfTipo;
    private JTextField txtfPrecio;
    private JCheckBox líquidoCheckBox;
    private JTable tableBalanceado;
    private JPanel formContainer;
    private JButton btGuardar;
    private JButton btModificar;
    private JButton btEliminar;
    private JTextField txtProt;
    private JTextField txtfVolumen;
    private JLabel labelKg;
    private JLabel jLabelRequired;
    private JButton btnLimpiar;
    private JTextField txtfStock;

    private int filaTabla;
    private String modId;

    public Balanceados() {

        updateTabla();

        btGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BigDecimal precio = new BigDecimal(txtfPrecio.getText());

                Double volumen = Double.parseDouble(txtfVolumen.getText());

                int prot = Integer.parseInt(txtProt.getText());

                BigDecimal stock = new BigDecimal(txtfStock.getText());

                Balanceado balanceado = new Balanceado(txtfMarca.getText(), txtfTipo.getText(),
                        precio, volumen, líquidoCheckBox.isSelected(), prot, stock);

                //Send field data to DB
                BalanceadoController.insertBalanceado(balanceado);

                System.out.println("It works!");

                updateTabla();

            }
        });


        btModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BigDecimal precio = new BigDecimal(txtfPrecio.getText());
                BigDecimal stock = new BigDecimal(txtfStock.getText());

                Balanceado balanceadoMod = new Balanceado(
                        txtfMarca.getText(),
                        txtfTipo.getText(),
                        precio,
                        Double.parseDouble(txtfVolumen.getText()),
                        líquidoCheckBox.isSelected(),
                        Integer.parseInt(txtProt.getText()),
                        stock
                );

                balanceadoMod.setId(modId);

                BalanceadoController.updateBalanceado(balanceadoMod);

                updateTabla();

            }
        });

        líquidoCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (líquidoCheckBox.isSelected()) {

                    labelKg.setText("Litros");

                } else {

                    labelKg.setText("Kg");

                }

            }
        });


        tableBalanceado.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {//This line prevents double events

                    filaTabla = tableBalanceado.getSelectedRow();

                    if (filaTabla > -1) {

                        txtfMarca.setText(String.valueOf(tableBalanceado.getValueAt(filaTabla, 1)));
                        txtfTipo.setText(String.valueOf(tableBalanceado.getValueAt(filaTabla, 2)));

                        String prot = String.valueOf(tableBalanceado.getValueAt(filaTabla, 3))
                                .replace("%", "");

                        txtProt.setText(prot);

                        String vol = String.valueOf(tableBalanceado.getValueAt(filaTabla, 4));

                        String aux = "";

                        if (vol.contains(" l")) {

                            aux = vol.replace(" l", "");

                        } else if (vol.contains(" kg")) {

                            aux = vol.replace(" kg", "");

                        }

                        txtfVolumen.setText(aux);

                        txtfPrecio.setText(String.valueOf(tableBalanceado.getValueAt(filaTabla, 5)));

                        Boolean bool = false;

                        if (tableBalanceado.getValueAt(filaTabla, 6).equals("1")) {
                            bool = true;
                        } else if (tableBalanceado.getValueAt(filaTabla, 6).equals("0")) {
                            bool = false;
                        }

                        líquidoCheckBox.setSelected(bool);

                        modId = String.valueOf(tableBalanceado.getValueAt(filaTabla, 0));
                        System.out.println(modId);

                    }

                }

            }
        });


        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clearFields();

            }
        });

        btEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (modId != null){

                    int option = JOptionPane.showConfirmDialog(formContainer, "Realmente desea eliminar este registro?",
                            "Confirmar Acción", JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    if (option == 0){

                        ProductosController.deleteProducto(modId, "balanceado");

                        updateTabla();

                        clearFields();

                    }

                }

            }
        });
    }

    // Clear all the form's text fields with empty strings
    private void clearFields(){

        txtfMarca.setText("");
        txtfPrecio.setText("");
        txtfTipo.setText("");
        txtfVolumen.setText("");
        txtProt.setText("");
        txtfStock.setText("");
        líquidoCheckBox.setSelected(false);

    }

    private void updateTabla() {

        String[] colNames = {"id", "Marca", "Tipo", "Proteína", "Volumen", "Precio", "lq", "Stock"};

        BalanceadoController balanceadoController = new BalanceadoController();

        Object[][] dataBalanceado;

        // Stores balanceado's ResultSet into a vector object
        dataBalanceado = balanceadoController.getAllData();

        // Casts liquido column
        for (int i = 0; i < dataBalanceado.length; i++) {

            dataBalanceado[i][3] = dataBalanceado[i][3] + "%";

            if (dataBalanceado[i][6].equals("1")) {

                dataBalanceado[i][4] = dataBalanceado[i][4] + " l";

            } else if (dataBalanceado[i][6].equals("0")) {

                dataBalanceado[i][4] = dataBalanceado[i][4] + " kg";

            }

        }

        // Set table model with balanceado data and column names
        DefaultTableModel datos = new DefaultTableModel(dataBalanceado, colNames);

        tableBalanceado.setModel(datos);

        TableColumn idHidden = tableBalanceado.getColumn("id");
        idHidden.setPreferredWidth(0);
        idHidden.setMinWidth(0);
        idHidden.setMaxWidth(0);

        TableColumn lqHidden = tableBalanceado.getColumn("lq");
        lqHidden.setPreferredWidth(0);
        lqHidden.setMinWidth(0);
        lqHidden.setMaxWidth(0);

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Balanceados");
        frame.setContentPane(new Balanceados().formContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

                System.out.println("Funciona esta mierda");

            }

            @Override
            public void windowClosing(WindowEvent e) {

                System.out.println("También funciona esta otra mierda");

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }


        });

    }


}
