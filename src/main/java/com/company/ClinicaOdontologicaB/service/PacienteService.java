package com.company.ClinicaOdontologicaB.service;

import com.company.ClinicaOdontologicaB.dto.PacienteDTO;
import com.company.ClinicaOdontologicaB.exception.ResourceNotFoundException;
import com.company.ClinicaOdontologicaB.model.Paciente;
import com.company.ClinicaOdontologicaB.repository.IPacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService implements IPacienteService {
    private final static Logger logger = Logger.getLogger(PacienteService.class);
    private IPacienteRepository pacienteRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository, ObjectMapper objectMapper) {
        this.pacienteRepository = pacienteRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<PacienteDTO> buscar(Long id){
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        PacienteDTO pacienteDTO = null;
        if (paciente.isPresent()){
            pacienteDTO = objectMapper.convertValue(paciente, PacienteDTO.class);
        }
        logger.info("Paciente encontrado");
        return pacienteDTO != null ? Optional.of(pacienteDTO):Optional.empty();
    }

    @Override
    public PacienteDTO registrar(PacienteDTO pacienteDTO) {
        Paciente paciente = objectMapper.convertValue(pacienteDTO, Paciente.class);
        logger.info("Paciente registrado");
        return objectMapper.convertValue(pacienteRepository.save(paciente), PacienteDTO.class);
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        if(buscar(id).isEmpty()){
            throw new ResourceNotFoundException("No existe paciente con id: " + id);
        }
        logger.info("Paciente eliminado");
        pacienteRepository.deleteById(id);
    }

    @Override
    public PacienteDTO actualizar(PacienteDTO pacienteDTO) throws ResourceNotFoundException{
        if (buscar(pacienteDTO.getId()).isEmpty()){
            throw new ResourceNotFoundException("No existe paciente con id: "+ pacienteDTO.getId());
        }
        Paciente paciente = objectMapper.convertValue(pacienteDTO, Paciente.class);
        logger.info("Paciente actualizado");
        return objectMapper.convertValue(pacienteRepository.save(paciente), PacienteDTO.class);
    }

    @Override
    public Set<PacienteDTO> buscarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteDTO> pacientesDTO = new HashSet<>();
        for(Paciente paciente:pacientes){
            pacientesDTO.add(objectMapper.convertValue(paciente, PacienteDTO.class));
        }
        logger.info("Listado de todos los pacientes");
        return pacientesDTO;
    }
}
