package com.codigo.Tarea_API.repository;

import com.codigo.Tarea_API.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
    EmpresaEntity findByNumeroDocumento(String numeroDocumento);
}
