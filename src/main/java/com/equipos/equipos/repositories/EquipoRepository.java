package com.equipos.equipos.repositories;

import com.equipos.equipos.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
}
