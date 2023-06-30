package com.example.FINALANSALDIFRANCO.controller;

import com.example.FINALANSALDIFRANCO.dto.TurnoDTO;
import com.example.FINALANSALDIFRANCO.entity.Turno;
import com.example.FINALANSALDIFRANCO.exceptions.BadRequestException;
import com.example.FINALANSALDIFRANCO.exceptions.ResourceNotFoundException;
import com.example.FINALANSALDIFRANCO.service.OdontologoService;
import com.example.FINALANSALDIFRANCO.service.TurnoService;
import com.example.FINALANSALDIFRANCO.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno (@RequestBody Turno turno) throws BadRequestException {
        if (odontologoService.buscarOdontologo(turno.getOdontologo().getId()) != null && pacienteService.buscarPaciente(turno.getPaciente().getId()) != null){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            throw new BadRequestException("El ID del Odontologo o del paciente no pueden ser nulos");
        }
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos (){
            return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
                return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno (@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó correctamente el turno con ID: " + id);
        } else {
            throw new ResourceNotFoundException("No se encontró el ID: " + id);
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorId(turno.getId());
        if (turnoBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Se actualizó correctamente el turno con ID: " + turno.getId());
        } else {
            return ResponseEntity.badRequest().body("No existe o no se puede actualizar el turno con ID: " + turno.getId());
        }
    }
}
