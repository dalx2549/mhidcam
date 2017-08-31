package com.mhidcamsa.admin.models.productos;

import java.util.UUID;
import java.math.BigDecimal;

public class Combustible {

    private String id;
    private String tipo;
    private BigDecimal precioGalon;

    public Combustible(String tipo, BigDecimal precioGalon) {

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        this.id = uuidString;
        this.tipo = tipo;
        this.precioGalon = precioGalon;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecioGalon() {
        return precioGalon;
    }

    public void setPrecioGalon(BigDecimal precioGalon) {
        this.precioGalon = precioGalon;
    }
}
