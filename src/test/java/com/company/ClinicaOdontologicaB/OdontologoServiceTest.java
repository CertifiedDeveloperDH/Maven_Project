package com.company.ClinicaOdontologicaB;

import com.company.ClinicaOdontologicaB.dto.OdontologoDTO;
import com.company.ClinicaOdontologicaB.exception.ResourceNotFoundException;
import com.company.ClinicaOdontologicaB.service.IOdontologoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    IOdontologoService odontologoService;

    @Autowired
    public OdontologoServiceTest(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    OdontologoDTO odontologoDTO;

    @BeforeEach
    void doBefore(){
        odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("NombreA");
        odontologoDTO.setApellido("ApellidoA");
        odontologoDTO.setNumeroMatricula("MatriculaA");
    }
    @Test
    @Order(2)
    void dBuscar() {
        OdontologoDTO odontologoDTOB = odontologoService.buscar(1L).orElse(null);
        assertNotNull(odontologoDTOB);
    }

    @Test
    @Order(1)
    void eRegistrar() {
        odontologoService.registrar(odontologoDTO);

        OdontologoDTO odontologoDTOB = odontologoService.buscar(1L).orElse(null);

        assertNotNull(odontologoDTOB);
    }

    @Test
    @Order(5)
    void aEliminar() throws ResourceNotFoundException {
        odontologoService.eliminar(1L);
        assertNull(odontologoService.buscar(1L).orElse(null));
    }

    @Test
    @Order(4)
    void bActualizar() throws ResourceNotFoundException {
        odontologoService.registrar(odontologoDTO);
        odontologoDTO.setNombre("NombreB");
        odontologoDTO.setApellido("ApellidoB");
        odontologoDTO.setNumeroMatricula("MatriculaB");
        odontologoDTO.setId(1L);

        assertNotNull(odontologoService.actualizar(odontologoDTO));
    }

    @Test
    @Order(3)
    void cBuscarTodos() {
        assertFalse(odontologoService.buscarTodos().isEmpty());
        assertNotNull(odontologoService.buscarTodos());
    }

}
