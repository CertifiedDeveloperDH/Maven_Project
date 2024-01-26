package com.company.ClinicaOdontologicaB;

import ClinicaOdontologicaB.util.Jsons;
import com.company.ClinicaOdontologicaB.dto.OdontologoDTO;
import com.company.ClinicaOdontologicaB.model.Odontologo;
import com.company.ClinicaOdontologicaB.service.OdontologoService;
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


//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegracionOdontologoTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private ObjectMapper objectMapper;

    public Odontologo cargarDataSet(){
        Odontologo od = new Odontologo();
        od.setNombre("Juan");
        od.setApellido("Ramirez");
        od.setNumeroMatricula("348971960");
        return od;
    }

    public Odontologo cargarDataSetB(){
        Odontologo od = new Odontologo();
        od.setId(1L);
        od.setNombre("JuanCambiado");
        od.setApellido("RamirezCambiado");
        od.setNumeroMatricula("348971960Cambiado");
        return od;
    }

    @Test
    @Order(2)
    public void dBuscarOdontologo() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(1)
    public void eRegistrarOdontologo() throws Exception {
        Odontologo od = cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Jsons.asJsonString(od)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(5)
    public void aEliminarOdontologo() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(4)
    public void bActualizarOdontologo() throws Exception {
        Odontologo odB = cargarDataSetB();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.put("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(odB)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(3)
    public void cListarOdontologos() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
