package com.codigo.Tarea_API.service;

import com.codigo.Tarea_API.entity.EmpresaEntity;

import java.util.List;

public interface EmpresaService {

    EmpresaEntity guardar(String ruc);
    EmpresaEntity obtenerEmpresaPorId(Long id);
    List<EmpresaEntity> obtenerTodasLasEmpresas();
    EmpresaEntity actualizarEmpresa(Long id, EmpresaEntity empresa);
    void eliminarEmpresa(Long id);
}
