package com.company.ClinicaOdontologicaB.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Turnos")
public class Turno {
    @Id
    @SequenceGenerator(name = "secuencia_turno", sequenceName = "secuencia_turno",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_turno")
    private Long turnoId;
    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id_odontologo")
    private Odontologo odontologo;
    private LocalDate fechaHora;

    public Long getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Long turnoId) {
        this.turnoId = turnoId;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
    }
}
