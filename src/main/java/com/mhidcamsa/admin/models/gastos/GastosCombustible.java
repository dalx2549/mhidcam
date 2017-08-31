package com.mhidcamsa.admin.models.gastos;

import java.util.UUID;
import java.math.BigDecimal;

public class GastosCombustible {

    private String id;
    private String idPeriodo;
    private String idCombustible;
    private BigDecimal cantidad;
    private BigDecimal costo;

    public GastosCombustible(String idPeriodo, String idCombustible, BigDecimal cantidad, BigDecimal costo) {

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        this.id = uuidString;
        this.idPeriodo = idPeriodo;
        this.idCombustible = idCombustible;
        this.cantidad = cantidad;
        this.costo = costo;

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

    public String getIdCombustible() {
        return idCombustible;
    }

    public void setIdCombustible(String idCombustible) {
        this.idCombustible = idCombustible;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
}
