package com.codigo.Examen_G7.repository;

import com.codigo.Examen_G7.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {
    List<PersonaEntity> findAllByEstado(String estado);
    Optional<PersonaEntity> findByDni(String dni);
}
