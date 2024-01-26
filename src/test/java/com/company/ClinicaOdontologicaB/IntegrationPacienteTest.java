package com.company.ClinicaOdontologicaB;

import com.company.ClinicaOdontologicaB.dto.PacienteDTO;
import com.company.ClinicaOdontologicaB.model.Direccion;
import com.company.ClinicaOdontologicaB.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
//import org.junit.Test;
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
public class IntegrationPacienteTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PacienteService pacienteService;
    private ObjectMapper objectMapper;

    public PacienteDTO cargarDataSet() {
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
        return pacienteService.registrar(paciente);
    }
    public PacienteDTO cargarDataSetB() {
        Direccion domicilio = new Direccion();
        domicilio.setCalle("Av Santa fe");
        domicilio.setNumero(444);
        domicilio.setLocalidad("CABACambiada");
        domicilio.setProvincia("Buenos Aires");
        domicilio.setId(1L);
        PacienteDTO paciente = new PacienteDTO();
        paciente.setNombre("SantiagoCambiado");
        paciente.setApellido("PazCambiado");
        paciente.setDni("88888888Cambiado");
        paciente.setFecha_ingreso(LocalDate.of(2001,1,1));
        paciente.setDomicilio(domicilio);
        paciente.setId(1L);
        return paciente;
    }
    @Test
    @Order(2)
    public void dBuscarPaciente() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(1)
    public void eRegistrarPaciente() throws Exception {
        PacienteDTO paciente = cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ClinicaOdontologicaB.util.Jsons.asJsonString(paciente)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(5)
    public void aEliminarPaciente() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(4)
    public void bActualizarPaciente() throws Exception {
        PacienteDTO pacienteB = cargarDataSetB();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.put("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ClinicaOdontologicaB.util.Jsons.asJsonString(pacienteB)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
    @Test
    @Order(3)
    public void cListarPacientes() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
