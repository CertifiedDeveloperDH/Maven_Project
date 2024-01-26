package com.company.ClinicaOdontologicaB.model;

import javax.persistence.*;

@Entity
@Table(name="Direcciones")
public class Direccion {
    @Id
    @SequenceGenerator(name = "secuencia_direccion", sequenceName = "secuencia_direccion",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_direccion")
    @Column(name = "id")
    private Long id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
