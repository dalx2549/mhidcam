package com.mhidcamsa.admin.models;

import java.math.BigDecimal;

public class Piscina {

    private String id;
    private String numero;
    private BigDecimal hectareas;
    private Boolean estado;

    public Piscina(String id, String numero, BigDecimal hectareas, Boolean estado) {
        this.id = id;
        this.numero = numero;
        this.hectareas = hectareas;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getHectareas() {
        return hectareas;
    }

    public void setHectareas(BigDecimal hectareas) {
        this.hectareas = hectareas;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
