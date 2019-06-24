package com.equipos.equipos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Integer fundacion;
    @ToString.Exclude  // uso esto en la salida toString para evitar que me cargue toda la lista cada vez q quiera mostrar el equipo
    @OneToMany(fetch = FetchType.EAGER ,orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "equipo") // mapeo el lado propietario osea el equipo q es el q tiene los jugadores
    private List<Jugador> jugadores;

}
