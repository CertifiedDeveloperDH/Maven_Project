package com.company.ClinicaOdontologicaB.controller;

import com.company.ClinicaOdontologicaB.dto.PacienteDTO;
import com.company.ClinicaOdontologicaB.exception.ResourceNotFoundException;
import com.company.ClinicaOdontologicaB.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscar(@PathVariable("id") Long id){
        PacienteDTO pacienteDTO = pacienteService.buscar(id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteDTO);
    }
    @PostMapping
    public ResponseEntity<PacienteDTO> registrar(@RequestBody PacienteDTO pacienteDTO){
        return ResponseEntity.ok(pacienteService.registrar(pacienteDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) throws ResourceNotFoundException {
        pacienteService.eliminar(id);
        ResponseEntity response = null;
        response = ResponseEntity.status(HttpStatus.OK).body("Paciente eliminado");
        return response;
    }
    @GetMapping
    public ResponseEntity<Collection<PacienteDTO>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.buscarTodos());
    }
    @PutMapping()
    public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException{
        ResponseEntity<PacienteDTO> response = null;
        response = ResponseEntity.ok(pacienteService.actualizar(pacienteDTO));
        return response;
    }
}
