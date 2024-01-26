package com.company.ClinicaOdontologicaB.service;

import com.company.ClinicaOdontologicaB.dto.PacienteDTO;
import com.company.ClinicaOdontologicaB.exception.ResourceNotFoundException;

import java.util.Optional;
import java.util.Set;

public interface IPacienteService {
    public Optional<PacienteDTO> buscar(Long id);
    public PacienteDTO registrar(PacienteDTO pacienteDTO);
    public void eliminar(Long id) throws ResourceNotFoundException;
    public PacienteDTO actualizar(PacienteDTO pacienteDTO) throws ResourceNotFoundException;
    public Set<PacienteDTO> buscarTodos();

}
