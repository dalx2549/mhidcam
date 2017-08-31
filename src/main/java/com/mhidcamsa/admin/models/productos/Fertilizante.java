package com.mhidcamsa.admin.models.productos;


import java.math.BigDecimal;

public class Fertilizante extends Producto {

    public Fertilizante(String marca, String tipo, BigDecimal precio, double volumen, boolean liquido, BigDecimal stockLibras) {
        super(marca, tipo, precio, volumen, liquido, stockLibras);
    }
}
