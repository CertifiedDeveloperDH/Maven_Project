package com.company.ClinicaOdontologicaB;

import com.company.ClinicaOdontologicaB.dto.PacienteDTO;
import com.company.ClinicaOdontologicaB.exception.ResourceNotFoundException;
import com.company.ClinicaOdontologicaB.model.Direccion;
import com.company.ClinicaOdontologicaB.service.IPacienteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {
    IPacienteService pacienteService;

    @Autowired
    public PacienteServiceTest(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    PacienteDTO pacienteDTO;
    Direccion direccion;
    @BeforeEach
    void doBefore(){
        pacienteDTO = new PacienteDTO();
        direccion = new Direccion();
        pacienteDTO.setNombre("NombreA");
        pacienteDTO.setApellido("ApellidoA");
        pacienteDTO.setDni("1234");
        direccion.setCalle("CalleA");
        direccion.setNumero(1234);
        direccion.setLocalidad("LocalidadA");
        direccion.setProvincia("ProvinciaA");
        pacienteDTO.setDomicilio(direccion);
        pacienteDTO.setFecha_ingreso(LocalDate.of(2000,1,1));
    }
    @Test
    @Order(2)
    void dBuscar() {
        PacienteDTO pacienteDTOB = pacienteService.buscar(1L).orElse(null);
        assertNotNull(pacienteDTOB);
    }

    @Test
    @Order(1)
    void eRegistrar() {
        pacienteService.registrar(pacienteDTO);

        PacienteDTO pacienteDTOB = pacienteService.buscar(1L).orElse(null);

        assertNotNull(pacienteDTOB);
    }

    @Test
    @Order(5)
    void aEliminar() throws ResourceNotFoundException {
        pacienteService.eliminar(1L);
        assertNull(pacienteService.buscar(1L).orElse(null));
    }

    @Test
    @Order(4)
    void bActualizar() throws ResourceNotFoundException {
        pacienteService.registrar(pacienteDTO);
        pacienteDTO.setNombre("NombreB");
        pacienteDTO.setApellido("ApellidoB");
        pacienteDTO.setFecha_ingreso(LocalDate.of(2001,1,1));
        direccion.setCalle("CalleB");
        direccion.setLocalidad("LocalidadB");
        pacienteDTO.setDomicilio(direccion);
        pacienteDTO.setId(1L);

        assertNotNull(pacienteService.actualizar(pacienteDTO));
    }

    @Test
    @Order(3)
    void cBuscarTodos() {
        assertFalse(pacienteService.buscarTodos().isEmpty());
        assertNotNull(pacienteService.buscarTodos());
    }
}