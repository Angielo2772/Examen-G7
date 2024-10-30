package com.codigo.Tarea_API.controller;

import com.codigo.Tarea_API.entity.EmpresaEntity;
import com.codigo.Tarea_API.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaEntity> obtenerEmpresa(@PathVariable Long id) {
        EmpresaEntity empresa = empresaService.obtenerEmpresaPorId(id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @GetMapping
    public List<EmpresaEntity> obtenerTodasLasEmpresas() {
        return empresaService.obtenerTodasLasEmpresas();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaEntity> actualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaEntity empresa) {
        EmpresaEntity empresaActualizada = empresaService.actualizarEmpresa(id, empresa);
        return new ResponseEntity<>(empresaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        empresaService.eliminarEmpresa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
