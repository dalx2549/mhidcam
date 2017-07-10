package com.mhidcamsa.admin.models;

import java.math.BigDecimal;
import java.util.UUID;

public class Desinfectante extends Producto {

    public Desinfectante(String marca, String tipo, BigDecimal precio, double volumen, boolean liquido) {
        super(marca, tipo, precio, volumen, liquido);
    }
}
