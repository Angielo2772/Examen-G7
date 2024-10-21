package com.codigo.Examen_G7.service;

import com.codigo.Examen_G7.entity.PedidosEntity;

import java.util.List;

public interface PedidosService {

    List<PedidosEntity> guardarPedidos(Long personaId, List<PedidosEntity> pedidoEntities);
    PedidosEntity obtenerPedidoPorId(Long id);
    List<PedidosEntity> obtenerTodosLosPedidos(String estado);
    PedidosEntity actualizarPedido(Long id, Long idPersona, PedidosEntity pedidosEntity);
    void eliminarPedido(Long id);
}
