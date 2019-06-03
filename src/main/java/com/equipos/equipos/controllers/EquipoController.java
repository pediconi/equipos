package com.equipos.equipos.controllers;

import com.equipos.equipos.models.Equipo;
import com.equipos.equipos.models.EquipoDTO;
import com.equipos.equipos.models.Jugador;
import com.equipos.equipos.models.JugadorDTO;
import com.equipos.equipos.repositories.EquipoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/equipo")  // le indico que es una controladora osea cuando ponga localhost/persona viene a esta clase
@RestController
public class EquipoController {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private EquipoRepository equipoRepository;   // usa el equipoRepository q encuentre en el contexto

    @PostMapping("")     // a la persona que recibe este metodo x post la guarda en la lista
    public void addEquipo(@RequestBody Equipo e) {
        equipoRepository.save(e);
    }

    @GetMapping("")   // peticiones get(desde postman) envia una peticion y llama a este metodo
    public List<EquipoDTO> getAll(){

        List<EquipoDTO> equiposDTO = new ArrayList<>();
        EquipoDTO eDTO;

        for (Equipo e : equipoRepository.findAll()){

            eDTO = modelMapper.map(e, EquipoDTO.class);   // mapeo el equipo

            for(Jugador j : e.getJugadores()){   // para cada e del primer for
                eDTO.getJugadores().set(e.getJugadores().indexOf(j), modelMapper.map(j, JugadorDTO.class) );
                //seteo para CADA jugador de eDTO el mapeo a jDTO
            }

            equiposDTO.add(eDTO);
        }
        return equiposDTO;
    }


    @GetMapping("/{id}")
    public EquipoDTO getById(@PathVariable Integer id){
        EquipoDTO eDTO;
        Optional<Equipo> e = equipoRepository.findById(id);
        eDTO = e.map(equipo -> modelMapper.map(equipo, EquipoDTO.class)).orElse(null);

        assert eDTO != null;
        for(Jugador j : e.get().getJugadores()){
            eDTO.getJugadores().set(e.get().getJugadores().indexOf(j), modelMapper.map(j, JugadorDTO.class) );
            // seteo para CADA jugador de eDTO el mapeo a jDTO
        }
        return eDTO;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        equipoRepository.deleteById(id);
    }
}
