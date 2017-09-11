package com.mhidcamsa.admin.models;

import java.util.UUID;
import java.sql.Date;

public class Corrida {

    private String id;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Boolean activa;
    private String id_piscina;

    public Corrida(Date fecha_inicio, Date fecha_fin, Boolean activa, String id_piscina) {

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        this.id = uuidString;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activa = activa;
        this.id_piscina = id_piscina;

    }

    public Corrida(String id, Date fecha_inicio, Date fecha_fin, Boolean activa, String id_piscina) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activa = activa;
        this.id_piscina = id_piscina;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public String getId_piscina() {
        return id_piscina;
    }

    public void setId_piscina(String id_piscina) {
        this.id_piscina = id_piscina;
    }
}
