package com.codigo.Examen_G7.service.impl;

import com.codigo.Examen_G7.entity.PedidosEntity;
import com.codigo.Examen_G7.entity.PersonaEntity;
import com.codigo.Examen_G7.repository.PedidosRepository;
import com.codigo.Examen_G7.repository.PersonaRepository;
import com.codigo.Examen_G7.service.PedidosService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidosServiceImpl implements PedidosService {

    private final PedidosRepository pedidosRepository;
    private final PersonaRepository personaRepository;

    public PedidosServiceImpl(PedidosRepository pedidosRepository, PersonaRepository personaRepository) {
        this.pedidosRepository = pedidosRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public List<PedidosEntity> guardarPedidos(Long personaId, List<PedidosEntity> pedidoEntities) {
        PersonaEntity personaExistente = personaRepository.findById(personaId).orElseThrow(() ->
                new NoSuchElementException("Error persona no existe"));

        for (PedidosEntity pedido : pedidoEntities) {
            pedido.setPersona(personaExistente);

            pedido.setEstado("PENDIENTE");
        }
        return pedidosRepository.saveAll(pedidoEntities);
    }

    @Override
    public PedidosEntity obtenerPedidoPorId(Long id) {
        return pedidosRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Pedido no encontrado"));
    }

    @Override
    public List<PedidosEntity> obtenerTodosLosPedidos(String estado) {
        return pedidosRepository.findAllByEstado(estado);
    }

    @Override
    public PedidosEntity actualizarPedido(Long id, Long idPersona, PedidosEntity pedidosEntity) {
        PedidosEntity pedidoExistente = obtenerPedidoPorId(id);
        PersonaEntity personaExistente = personaRepository.findById(idPersona)
                .orElseThrow(()-> new NoSuchElementException("No se encontró esta persona"));

        pedidoExistente.setDescripcion(pedidosEntity.getDescripcion());
        pedidoExistente.setEstado(pedidosEntity.getEstado());
        pedidoExistente.setPersona(personaExistente);

        return pedidosRepository.save(pedidoExistente);
    }

    @Override
    public void eliminarPedido(Long id) {
        PedidosEntity pedidoExistente = obtenerPedidoPorId(id);

        pedidoExistente.setEstado("ELIMINADO");

        pedidosRepository.delete(pedidoExistente);
    }
}
