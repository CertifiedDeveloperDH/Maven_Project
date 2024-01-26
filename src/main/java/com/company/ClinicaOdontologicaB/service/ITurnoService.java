package com.company.ClinicaOdontologicaB.service;

import com.company.ClinicaOdontologicaB.dto.TurnoDTO;
import com.company.ClinicaOdontologicaB.exception.BadRequestException;
import com.company.ClinicaOdontologicaB.exception.ResourceNotFoundException;

import java.util.Optional;
import java.util.Set;

public interface ITurnoService {
    public Optional<TurnoDTO> buscar(Long id);
    public TurnoDTO registrar(TurnoDTO turnoDTO) throws BadRequestException;
    public void eliminar(Long id) throws ResourceNotFoundException;
    public TurnoDTO actualizar(TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException;
    public Set<TurnoDTO> buscarTodos();
}
