package com.example.FINALANSALDIFRANCO;

import com.example.FINALANSALDIFRANCO.entity.Odontologo;
import com.example.FINALANSALDIFRANCO.service.OdontologoService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarOdontologo(){
        Odontologo odontologoAguardar = new Odontologo("nombreOdontologo","apellidoOdontologo", "MAT123");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAguardar);
        assertEquals(2, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarOdontologo(){
        Long idAbuscar = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(idAbuscar);
        assertTrue(odontologoBuscado.isPresent());
    }

    @Test
    @Order(3)
    void actualizarOdontologo(){
        Odontologo odontologoAactualizar = new Odontologo("nombreOdontologo1","apellidoOdontologo1", "MAT234");
        odontologoService.actualizarOdontologo(odontologoAactualizar);
        assertEquals("apellidoOdontologo1", odontologoService.buscarOdontologo(1L).get().getApellido());
    }

    @Test
    @Order(4)
    void buscarTodosLosOdontologos(){
        List<Odontologo> listaEncontrada = odontologoService.listarOdontologos();
        assertEquals(1, listaEncontrada.size());
    }

    @Test
    @Order(5)
    void buscarOdontologoPorMatricula(){
        String matricula = "MAT123";
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        assertNotNull(odontologoBuscado.isPresent());
    }

    @Test
    @Order(6)
    void eliminarOdontologo(){
        Long idAeliminar = 1L;
        odontologoService.eliminarOdontologo(idAeliminar);
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologo(idAeliminar);
        assertFalse(odontologoEliminado.isPresent());
    }
}