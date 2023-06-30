package com.example.FINALANSALDIFRANCO.service;

import com.example.FINALANSALDIFRANCO.dto.TurnoDTO;
import com.example.FINALANSALDIFRANCO.entity.Odontologo;
import com.example.FINALANSALDIFRANCO.entity.Paciente;
import com.example.FINALANSALDIFRANCO.entity.Turno;
import com.example.FINALANSALDIFRANCO.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public List<TurnoDTO> listarTurnos(){
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        List<TurnoDTO> turnoDtoLista = new ArrayList<>();
        for (Turno turno: turnosEncontrados) {
            turnoDtoLista.add(turnoAturnoDTO(turno));
        }
        return turnoDtoLista;
    }

    public Optional<TurnoDTO> buscarPorId(Long id){
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if(turnoBuscado.isPresent()){
            return Optional.of(turnoAturnoDTO(turnoBuscado.get()));
        } else {
            return Optional.empty();
        }
    }

    public TurnoDTO guardarTurno(Turno turno){
        Turno turnoGuardado = turnoRepository.save((turno));
        return turnoAturnoDTO(turnoGuardado);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public void actualizarTurno(TurnoDTO turnoDTO){
        turnoRepository.save(turnoDtoAturno(turnoDTO));
    }

    private TurnoDTO turnoAturnoDTO(Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());

        return turnoDTO;
    }

    private Turno turnoDtoAturno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();

        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());

        return turno;
    }

}
