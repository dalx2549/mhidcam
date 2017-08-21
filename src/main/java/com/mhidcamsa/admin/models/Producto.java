package com.mhidcamsa.admin.models;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Producto {

    private String id;
    private String marca;
    private String tipo;
    private BigDecimal precio;
    private double volumen;
    private boolean liquido;
    private BigDecimal stockLibras;

    public Producto(String marca, String tipo, BigDecimal precio, double volumen, boolean liquido, BigDecimal stockLibras) {

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        this.id = uuidString;
        this.marca = marca;
        this.tipo = tipo;
        this.precio = precio;
        this.volumen = volumen;
        this.liquido = liquido;
        this.stockLibras = stockLibras;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public boolean isLiquido() {
        return liquido;
    }

    public void setLiquido(boolean liquido) {
        this.liquido = liquido;
    }

    public BigDecimal getStockLibras() {
        return stockLibras;
    }

    public void setStockLibras(BigDecimal stockLibras) {
        this.stockLibras = stockLibras;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "ID:" + id +
                ", Marca: " + marca + '\'' +
                ", Tipo: " + tipo + '\'' +
                ", Precio: " + precio +
                ", Volumen: " + volumen +
                ", Liquido; " + liquido +
                '}';
    }
}
