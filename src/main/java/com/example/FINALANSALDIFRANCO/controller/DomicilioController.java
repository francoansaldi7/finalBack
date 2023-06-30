package com.example.FINALANSALDIFRANCO.controller;

import com.example.FINALANSALDIFRANCO.entity.Domicilio;
import com.example.FINALANSALDIFRANCO.exceptions.BadRequestException;
import com.example.FINALANSALDIFRANCO.exceptions.ResourceNotFoundException;
import com.example.FINALANSALDIFRANCO.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    
    @Autowired
    private DomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<Domicilio> guardarDomicilio(@RequestBody Domicilio domicilio){
        return ResponseEntity.ok(domicilioService.guardarDomicilio(domicilio));
    }

    @GetMapping
    public ResponseEntity<List<Domicilio>> listarDomicilios(){
        return ResponseEntity.ok(domicilioService.listarDomicilios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> buscarDomicilioPorId(@PathVariable Long id) throws BadRequestException {
        Optional<Domicilio> domicilioBuscado = domicilioService.buscarDomicilio(id);
        if (domicilioBuscado.isPresent()){
            return ResponseEntity.ok(domicilioBuscado.get());
        } else {
            throw new BadRequestException("El ID ingresado no es correcto");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarDomicilio(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Domicilio> domicilioBuscado = domicilioService.buscarDomicilio(id);
        if (domicilioBuscado.isPresent()){
            domicilioService.eliminarDomicilio(id);
            return ResponseEntity.ok("Se eliminó el domicilio con ID: " + id + " correctamente");
        } else {
            throw new ResourceNotFoundException("No se encontró el ID: " + id);
        }
    }
}

