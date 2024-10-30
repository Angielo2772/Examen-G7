package com.codigo.Tarea_API.controller;

import com.codigo.Tarea_API.entity.EmpresaEntity;
import com.codigo.Tarea_API.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<EmpresaEntity> guardarEmpresa(
            @RequestParam("ruc") String ruc){
        EmpresaEntity empresaEntity = empresaService.guardar(ruc);
    return new ResponseEntity<>(empresaEntity, HttpStatus.CREATED);
    }
}
