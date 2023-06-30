package com.example.FINALANSALDIFRANCO.controller;

import com.example.FINALANSALDIFRANCO.entity.Odontologo;
import com.example.FINALANSALDIFRANCO.exceptions.BadRequestException;
import com.example.FINALANSALDIFRANCO.exceptions.ResourceNotFoundException;
import com.example.FINALANSALDIFRANCO.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo (@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Long id) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            throw new BadRequestException("El ID ingresado no es correcto");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Se elimino el odontologo con ID: " + id + " correctamente");
        } else {
            throw new ResourceNotFoundException("No se encontró el ID: " + id);
        }
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@PathVariable String matricula){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
