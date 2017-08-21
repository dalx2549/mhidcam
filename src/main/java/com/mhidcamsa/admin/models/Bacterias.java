package com.mhidcamsa.admin.models;


import java.math.BigDecimal;
import java.util.UUID;

public class Bacterias extends Producto {

    public Bacterias(String marca, String tipo, BigDecimal precio, double volumen, boolean liquido, BigDecimal stockLibras) {
        super(marca, tipo, precio, volumen, liquido, stockLibras);
    }
}
