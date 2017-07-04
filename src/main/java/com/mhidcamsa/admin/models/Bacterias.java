package com.mhidcamsa.admin.models;


import java.math.BigDecimal;
import java.util.UUID;

public class Bacterias extends Producto {

    public Bacterias(UUID id, String marca, String tipo, BigDecimal precio, double volumen, boolean liquido) {
        super(id, marca, tipo, precio, volumen, liquido);
    }

}
