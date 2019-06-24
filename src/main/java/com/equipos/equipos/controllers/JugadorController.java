package com.equipos.equipos.controllers;

import com.equipos.equipos.models.Equipo;
import com.equipos.equipos.models.Jugador;
import com.equipos.equipos.models.JugadorDTO;
import com.equipos.equipos.repositories.EquipoRepository;
import com.equipos.equipos.repositories.JugadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/jugador")  // le indico que es una controladora osea cuando ponga localhost/persona viene a esta clase
@RestController
public class JugadorController {

    private final String EQUIPO_NOT_FOUND = "Equipo no encontrado";
    private final String JUGADOR_NOT_FOUND = "Jugador con id: %s no existe";
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private EquipoRepository equipoRepository;

    @PostMapping("/{id_equipo}")     // a la persona que recibe este metodo x post la guarda en la lista
    public void addJugador(@PathVariable Integer id_equipo, @RequestBody Jugador j) {

        Equipo e = equipoRepository.findById(id_equipo) // me traigo el equipo lo busco x id
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(EQUIPO_NOT_FOUND,id_equipo)));

        j.setEquipo(e);   // como en el json cuando cargo un jugador no pongo su equipo(x el json ignore de la clase jugador), lo tengo q setear aca
        e.getJugadores().add(j);     // a ese equipo le añado el jugador a su lista y guardo

        jugadorRepository.save(j);
        equipoRepository.save(e);
    }

    @GetMapping("")   // peticiones get(desde postman) envia una peticion y llama a este metodo
    public List<JugadorDTO> getAll(){

        List<JugadorDTO> jugadoresDTO = new ArrayList<>();
        for (Jugador j : jugadorRepository.findAll()){               // levanto los jugadores de la bd y los mapeo para mostrarlos
            jugadoresDTO.add(modelMapper.map(j , JugadorDTO.class));
        }
        return jugadoresDTO;
    }

    @GetMapping("/{id}")
    public JugadorDTO getJugadorById(@PathVariable Integer id){
        Optional<Jugador> j = jugadorRepository.findById(id);
        return j.map(jugador -> modelMapper.map(jugador, JugadorDTO.class)).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(JUGADOR_NOT_FOUND,id))); // aca lanzar excepcion en el else
    }

    /* ES LO MISMO QUE LO DE ARRIBA:
    @GetMapping("/{id}")
    public JugadorDTO getJugadorById(@PathVariable Integer id){
        Optional<Jugador> j = jugadorRepository.findById(id);

        if (j.isPresent()){
            return modelMapper.map(j.get() , JugadorDTO.class);
        }
        return null;
    }*/

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        jugadorRepository.deleteById(id);
    }

}
