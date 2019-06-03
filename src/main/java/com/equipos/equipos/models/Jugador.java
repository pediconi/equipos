package com.equipos.equipos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Min(0)
    @Max(99)
    private Integer numero;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    private String posicion;

    @JsonIgnore  // ignoro al equipo en la serializacion asi no lo tengo q cargar en postman , si no que lo busco del repo
    @ManyToOne(fetch = FetchType.LAZY)   // lazy trae solo el proxy eager trae tode
    @JoinColumn(name = "equipo_id")    // foreign key tabla_ id referenciado
    private Equipo equipo;
}
