package com.mhidcamsa.admin.models;


import java.math.BigDecimal;
import java.util.UUID;

public class Vitamina extends Producto{

    public Vitamina(String marca, String tipo, BigDecimal precio, double volumen, boolean liquido, BigDecimal stockLibras) {
        super(marca, tipo, precio, volumen, liquido, stockLibras);
    }
}
