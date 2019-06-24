package com.equipos.equipos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDTO {

    private String nombre;
    private Integer fundacion;
    private List<JugadorDTO> jugadores;

}
