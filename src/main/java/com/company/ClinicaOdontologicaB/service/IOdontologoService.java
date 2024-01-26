package com.company.ClinicaOdontologicaB.service;


import com.company.ClinicaOdontologicaB.dto.OdontologoDTO;
import com.company.ClinicaOdontologicaB.exception.ResourceNotFoundException;

import java.util.Optional;
import java.util.Set;

public interface IOdontologoService {
    public Optional<OdontologoDTO> buscar(Long id);
    public OdontologoDTO registrar(OdontologoDTO odontologoDTO);
    public void eliminar(Long id) throws ResourceNotFoundException;
    public OdontologoDTO actualizar(OdontologoDTO odontologoDTO) throws ResourceNotFoundException;
    public Set<OdontologoDTO> buscarTodos();
}
