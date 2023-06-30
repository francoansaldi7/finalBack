package com.example.FINALANSALDIFRANCO.controller;

import com.example.FINALANSALDIFRANCO.entity.Paciente;
import com.example.FINALANSALDIFRANCO.exceptions.BadRequestException;
import com.example.FINALANSALDIFRANCO.exceptions.ResourceNotFoundException;
import com.example.FINALANSALDIFRANCO.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/guardar")
    public ResponseEntity<Paciente> registrarPaciente (@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el paciente con ID: " + id);
        } else {
            throw new ResourceNotFoundException("No se encontró el ID: " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes (){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            throw new BadRequestException("El ID ingresado no es correcto");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente (@RequestBody Paciente paciente) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado - " + paciente.getNombre() + " " + paciente.getApellido());
        } else {
            throw new ResourceNotFoundException("Paciente no encontrado" + paciente.getId() + "Nombre: " + paciente.getNombre());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail (@PathVariable String email){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
