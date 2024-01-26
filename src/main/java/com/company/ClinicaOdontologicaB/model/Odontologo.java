package com.company.ClinicaOdontologicaB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="Odontologos")
public class Odontologo {
    @Id
    @SequenceGenerator(name = "secuencia_odontologo",sequenceName = "secuencia_odontologo",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_odontologo")
    @Column(name = "id_odontologo")
    private Long id;
    private String numeroMatricula;
    private String nombre;
    private String apellido;
    @OneToMany(mappedBy = "odontologo")
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
        turnos.forEach(turno-> turno.setOdontologo(this));
    }
}
