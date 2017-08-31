package com.mhidcamsa.admin.models.gastos;

import java.util.UUID;
import java.math.BigDecimal;

public class GastosManoObra {

    private String id;
    private String idPeriodo;
    private String descripcion;
    private BigDecimal valor;

    public GastosManoObra(String idPeriodo, String descripcion, BigDecimal valor) {

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        this.id = uuidString;
        this.idPeriodo = idPeriodo;
        this.descripcion = descripcion;
        this.valor = valor;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
