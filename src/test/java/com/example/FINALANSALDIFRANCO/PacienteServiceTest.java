package com.example.FINALANSALDIFRANCO;

import com.example.FINALANSALDIFRANCO.entity.Domicilio;
import com.example.FINALANSALDIFRANCO.entity.Paciente;
import com.example.FINALANSALDIFRANCO.service.PacienteService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    @Test
    @Order(1)
    void guardarPaciente(){
        Paciente pacienteAguardar = new Paciente("pacienteNombre", "pacienteApellido", "123456", LocalDate.of(2023, 4, 20), new Domicilio("calleTest", "123", "localidadTest", "provinciaTest"), "emailTest@test.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAguardar);
        assertEquals(1L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarPaciente(){
        Long idAbuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idAbuscar);
        assertTrue(pacienteBuscado.isPresent());
    }

    @Test
    @Order(3)
    void actualizarPaciente(){
        Paciente pacienteAactualizar = new Paciente("pacienteNombre1", "pacienteApellido1", "234567", LocalDate.of(2023, 2, 15), new Domicilio("calleTest1", "1234", "localidadTest1", "provinciaTest1"), "emailTest1@test.com");
        pacienteService.actualizarPaciente(pacienteAactualizar);
        assertEquals("pacienteApellido1", pacienteService.buscarPaciente(1L).get().getApellido());
    }

    @Test
    @Order(4)
    void buscarTodosLosPacientes(){
        List<Paciente> listaEncontrada = pacienteService.listarPacientes();
        assertEquals(1, listaEncontrada.size());
    }

    @Test
    @Order(5)
    void buscarPorEmail(){
        String email = "test@test.com";
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
        assertTrue(pacienteBuscado.isPresent());
    }

    @Test
    @Order(6)
    void eliminarPaciente(){
        Long idAeliminar = 1L;
        pacienteService.eliminarPaciente(idAeliminar);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPaciente(idAeliminar);
        assertFalse(pacienteEliminado.isPresent());
    }
}