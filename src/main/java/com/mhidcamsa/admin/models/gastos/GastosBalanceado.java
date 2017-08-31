package com.mhidcamsa.admin.models.gastos;

import java.math.BigDecimal;
import java.util.UUID;


public class GastosBalanceado {

    private String id;
    private String idPeriodo;
    private String idBalanceado;
    private BigDecimal cantidad;
    private BigDecimal precioKilogramo;
    private BigDecimal subtotal;

    public GastosBalanceado(String idPeriodo, String idBalanceado, BigDecimal cantidad, BigDecimal precioKilogramo, BigDecimal subtotal) {

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        this.id = uuidString;
        this.idPeriodo = idPeriodo;
        this.idBalanceado = idBalanceado;
        this.cantidad = cantidad;
        this.precioKilogramo = precioKilogramo;
        this.subtotal = subtotal;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(String idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getIdBalanceado() {
        return idBalanceado;
    }

    public void setIdBalanceado(String idBalanceado) {
        this.idBalanceado = idBalanceado;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioKilogramo() {
        return precioKilogramo;
    }

    public void setPrecioKilogramo(BigDecimal precioKilogramo) {
        this.precioKilogramo = precioKilogramo;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
