package com.mhidcamsa.admin.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public class Periodo {

    private String id;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal totalCosto;
    private String corrida;

    public Periodo(Date fechaInicio, Date fechaFin, BigDecimal totalCosto, String corrida) {

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        this.id = uuidString;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalCosto = totalCosto;
        this.corrida = corrida;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getTotalCosto() {
        return totalCosto;
    }

    public void setTotalCosto(BigDecimal totalCosto) {
        this.totalCosto = totalCosto;
    }

    public String getCorrida() {
        return corrida;
    }

    public void setCorrida(String corrida) {
        this.corrida = corrida;
    }
}
