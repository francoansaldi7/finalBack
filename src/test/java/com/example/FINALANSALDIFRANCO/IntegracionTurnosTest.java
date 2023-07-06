package com.example.FINALANSALDIFRANCO;

import com.example.FINALANSALDIFRANCO.dto.TurnoDTO;
import com.example.FINALANSALDIFRANCO.entity.Domicilio;
import com.example.FINALANSALDIFRANCO.entity.Odontologo;
import com.example.FINALANSALDIFRANCO.entity.Paciente;
import com.example.FINALANSALDIFRANCO.entity.Turno;
import com.example.FINALANSALDIFRANCO.service.OdontologoService;
import com.example.FINALANSALDIFRANCO.service.PacienteService;
import com.example.FINALANSALDIFRANCO.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;

    public void cargarDatos(){
        Paciente pacienteAgregado = pacienteService.guardarPaciente(new Paciente("testN", "testA", "1234567", LocalDate.of(2022,06, 07),
                new Domicilio("CalleTest", "1234", "LocalidadTest", "ProvinciaTest"), "mail@test.com"));
        Odontologo odontologoAgregado = odontologoService.guardarOdontologo(new Odontologo("nombreOdontologo", "apellidoOdontologo", "123456"));
        TurnoDTO turnoAgregado = turnoService.guardarTurno(new Turno(pacienteAgregado, odontologoAgregado, LocalDate.of(2023,07,07)));
    }

    @Test
    public void ListarTurnosTest() throws Exception{
        cargarDatos();
        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }
}
