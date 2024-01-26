package com.company.ClinicaOdontologicaB;

import com.company.ClinicaOdontologicaB.dto.OdontologoDTO;
import com.company.ClinicaOdontologicaB.dto.PacienteDTO;
import com.company.ClinicaOdontologicaB.dto.TurnoDTO;
import com.company.ClinicaOdontologicaB.exception.BadRequestException;
import com.company.ClinicaOdontologicaB.model.Direccion;
import com.company.ClinicaOdontologicaB.model.Odontologo;
import com.company.ClinicaOdontologicaB.model.Paciente;
import com.company.ClinicaOdontologicaB.service.OdontologoService;
import com.company.ClinicaOdontologicaB.service.PacienteService;
import com.company.ClinicaOdontologicaB.service.TurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegracionTurnosTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public TurnoDTO cargarDataSet() throws BadRequestException {
        Direccion domicilio = new Direccion();
        domicilio.setCalle("Av Santa fe");
        domicilio.setNumero(444);
        domicilio.setLocalidad("CABA");
        domicilio.setProvincia("Buenos Aires");
        PacienteDTO paciente = new PacienteDTO();
        paciente.setNombre("Santiago");
        paciente.setApellido("Paz");
        paciente.setDni("88888888");
        paciente.setFecha_ingreso(LocalDate.of(2000,1,1));
        paciente.setDomicilio(domicilio);
        OdontologoDTO odontologo = new OdontologoDTO();
        odontologo.setNombre("Santiago");
        odontologo.setApellido("Paz");
        odontologo.setNumeroMatricula("3455647");
        PacienteDTO pacienteAgregado = pacienteService.registrar(paciente);
        OdontologoDTO odontologoAgregado = odontologoService.registrar(odontologo);
        TurnoDTO turno = new TurnoDTO();
        turno.setPaciente(objectMapper.convertValue(pacienteAgregado, Paciente.class));
        turno.setOdontologo(objectMapper.convertValue(odontologoAgregado, Odontologo.class));
        turno.setFechaHora(LocalDate.of(2000,1,1));
        TurnoDTO turnoAgregado = turnoService.registrar(turno);
        return turnoAgregado;
    }
    public TurnoDTO cargarDataSetB() throws BadRequestException {
        Direccion domicilio = new Direccion();
        domicilio.setCalle("Av Santa fe");
        domicilio.setNumero(444);
        domicilio.setLocalidad("CABA");
        domicilio.setProvincia("Buenos Aires");
        domicilio.setId(1L);
        PacienteDTO paciente = new PacienteDTO();
        paciente.setNombre("Santiago");
        paciente.setApellido("Paz");
        paciente.setDni("88888888");
        paciente.setFecha_ingreso(LocalDate.of(2000,1,1));
        paciente.setDomicilio(domicilio);
        paciente.setId(1L);
        OdontologoDTO odontologo = new OdontologoDTO();
        odontologo.setNombre("Santiago");
        odontologo.setApellido("Paz");
        odontologo.setNumeroMatricula("3455647");
        odontologo.setId(1L);
        PacienteDTO pacienteAgregado = pacienteService.registrar(paciente);
        OdontologoDTO odontologoAgregado = odontologoService.registrar(odontologo);
        TurnoDTO turno = new TurnoDTO();
        turno.setPaciente(objectMapper.convertValue(pacienteAgregado, Paciente.class));
        turno.setOdontologo(objectMapper.convertValue(odontologoAgregado, Odontologo.class));
        turno.setFechaHora(LocalDate.of(2001,1,1));
        turno.setTurnoId(1L);
        return turno;
    }

    @Test
    @Order(2)
    public void dBuscarTurno() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(1)
    public void eRegistrarTurno() throws Exception {
        TurnoDTO turno = cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ClinicaOdontologicaB.util.Jsons.asJsonString(turno)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(5)
    public void aEliminarTurno() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(4)
    public void bActualizarPaciente() throws Exception {
        TurnoDTO turnoB = cargarDataSetB();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.put("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ClinicaOdontologicaB.util.Jsons.asJsonString(turnoB)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
    @Test
    @Order(3)
    public void listarTurnos() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
