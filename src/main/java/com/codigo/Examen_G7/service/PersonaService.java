package com.codigo.Examen_G7.service;

import com.codigo.Examen_G7.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {

    PersonaEntity guardarPersona(PersonaEntity personaEntity);
    PersonaEntity obtenerPersonaPorId(Long id);
    List<PersonaEntity> obtenerTodasLasPersonas();
    PersonaEntity obtenerPersonaPorDni(String dni);
    PersonaEntity actualizarPersona(Long id, PersonaEntity personaEntity);
    void eliminarPersona(Long id);
}
