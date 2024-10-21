package com.codigo.Examen_G7.repository;

import com.codigo.Examen_G7.entity.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<PedidosEntity, Long> {
    List<PedidosEntity> findAllByEstado(String estado);
}
