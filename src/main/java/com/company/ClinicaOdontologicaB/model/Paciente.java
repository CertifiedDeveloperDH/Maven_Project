package com.company.ClinicaOdontologicaB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pacientes")
public class Paciente {
    @Id
    @SequenceGenerator(name="secuencia_paciente",sequenceName = "secuencia_paciente", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "secuencia_paciente")
    @Column(name = "id_paciente")
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fecha_ingreso;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="direccion_id", referencedColumnName = "id")
    private Direccion domicilio;
    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Direccion getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Direccion domicilio) {
        this.domicilio = domicilio;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
        turnos.forEach(turno-> turno.setPaciente(this));
    }
}
