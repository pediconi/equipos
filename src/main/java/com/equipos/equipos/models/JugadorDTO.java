package com.equipos.equipos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorDTO {

    //private Integer id;
    private Integer numero;
    private String nombre;
    private String apellido;
    private String posicion;
    private String equipoNombre;        //va a buscar el nombre a la clase equipo
    private Integer equipoFundacion;

}
