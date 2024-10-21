package com.codigo.Examen_G7.service.impl;

import com.codigo.Examen_G7.entity.PersonaEntity;
import com.codigo.Examen_G7.repository.PersonaRepository;
import com.codigo.Examen_G7.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    private PersonaServiceImpl(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaEntity guardarPersona(PersonaEntity personaEntity) {

        PersonaEntity persona = personaRepository.findByDni(personaEntity.getDni()). orElse(null);

        if (persona != null) throw new NoSuchElementException("La persona con ese DNI ya existe");

        persona.setEstado("ACTIVO");

        if (personaEntity.getPedidos() != null) {
            personaEntity.getPedidos().forEach(pedidos -> pedidos.setEstado("PENDIENTE"));
        }

        return personaRepository.save(personaEntity);
    }

    @Override
    public PersonaEntity obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id).orElseThrow(() ->
                    new NoSuchElementException("Persona no encontrada"));
    }

    @Override
    public List<PersonaEntity> obtenerTodasLasPersonas() {
        return personaRepository.findAllByEstado("ACTIVO");
    }

    @Override
    public PersonaEntity obtenerPersonaPorDni(String dni) {
        return personaRepository.findByDni(dni).orElseThrow(() ->
                new NoSuchElementException("La persona con este DNI no fue encontrada"));
    }

    @Override
    public PersonaEntity actualizarPersona(Long id, PersonaEntity personaActual) {

        PersonaEntity personaExistente = obtenerPersonaPorId(id);

        personaExistente.setNombre(personaActual.getNombre());
        personaExistente.setDireccionEntity(personaActual.getDireccionEntity());
        personaExistente.setDni(personaActual.getDni());
        personaActual.setId(personaExistente.getId());

        if (personaActual.getPedidos() != null) {
            personaActual.getPedidos().forEach(order -> {
                if (order.getId() == null) {
                    order.setEstado("PENDIENTE");
                } else {
                    order.setEstado("PROCESADO");
                }

                order.setPersona(personaExistente);
            });
        }

        personaActual.setPedidos(personaActual.getPedidos());

        return personaRepository.save(personaExistente);
    }

    @Override
    public void eliminarPersona(Long id) {

        PersonaEntity personaExistente = obtenerPersonaPorId(id);

        personaExistente.setEstado("INACTIVO");

        personaExistente.getPedidos().forEach(pedidos -> pedidos.setEstado("ELIMINADOS"));

        personaRepository.save(personaExistente);
    }
}
