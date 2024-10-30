package com.codigo.Tarea_API.service.impl;

import com.codigo.Tarea_API.client.SunatClient;
import com.codigo.Tarea_API.aggregates.constans.Constans;
import com.codigo.Tarea_API.entity.EmpresaEntity;
import com.codigo.Tarea_API.repository.EmpresaRepository;
import com.codigo.Tarea_API.aggregates.response.SunatResponse;
import com.codigo.Tarea_API.service.EmpresaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    private final SunatClient sunatClient;

    @Value("${token.api}")
    private String token;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, SunatClient sunatClient) {
        this.empresaRepository = empresaRepository;
        this.sunatClient = sunatClient;
    }

    @Override
    public EmpresaEntity guardar(String ruc) {

        EmpresaEntity empresa = getEntity(ruc);

        if (Objects.nonNull(empresa)) {
            return empresaRepository.save(empresa);
        } else {
            return null;
        }
    }

    @Override
    public EmpresaEntity obtenerEmpresaPorId(Long id) {
        return empresaRepository.findById(id).orElseThrow(()->new NoSuchElementException("La empresa no existe"));
    }

    @Override
    public List<EmpresaEntity> obtenerTodasLasEmpresas() {
        return empresaRepository.findAll();
    }

    @Override
    public EmpresaEntity actualizarEmpresa(Long id, EmpresaEntity empresa) {
        EmpresaEntity empresaExistente = obtenerEmpresaPorId(id);
        empresaExistente.setRazonSocial(empresa.getRazonSocial());
        empresaExistente.setTipoDocumento(empresa.getTipoDocumento());
        empresaExistente.setNumeroDocumento(empresa.getNumeroDocumento());
        empresaExistente.setEstado(empresa.getEstado());
        empresaExistente.setCondicion(empresa.getCondicion());
        empresaExistente.setDireccion(empresa.getDireccion());
        empresaExistente.setUbigeo(empresa.getUbigeo());
        empresaExistente.setViaTipo(empresa.getViaTipo());
        empresaExistente.setViaNombre(empresa.getViaNombre());
        empresaExistente.setZonaCodigo(empresa.getZonaCodigo());
        empresaExistente.setZonaTipo(empresa.getZonaTipo());
        empresaExistente.setNumero(empresa.getNumero());
        empresaExistente.setInterior(empresa.getInterior());
        empresaExistente.setLote(empresa.getLote());
        empresaExistente.setDpto(empresa.getDpto());
        empresaExistente.setManzana(empresa.getManzana());
        empresaExistente.setKilometro(empresa.getKilometro());
        empresaExistente.setDistrito(empresa.getDistrito());
        empresaExistente.setProvincia(empresa.getProvincia());
        empresaExistente.setDepartamento(empresa.getDepartamento());
        empresaExistente.setEsAgenteRetencion(empresa.isEsAgenteRetencion());
        empresaExistente.setTipo(empresa.getTipo());
        empresaExistente.setActividadEconomica(empresa.getActividadEconomica());
        empresaExistente.setNumeroTrabajadores(empresa.getNumeroTrabajadores());
        empresaExistente.setTipoFacturacion(empresa.getTipoFacturacion());
        empresaExistente.setTipoContabilidad(empresa.getTipoContabilidad());
        empresaExistente.setComercioExterior(empresa.getComercioExterior());
        empresaExistente.setUserCreated(Constans.USER_CREATED);
        empresaExistente.setDateCreated(new Timestamp(System.currentTimeMillis()));

        return empresaRepository.save(empresaExistente);
    }

    @Override
    public void eliminarEmpresa(Long id) {
        EmpresaEntity empresaExistente = obtenerEmpresaPorId(id);
        empresaRepository.delete(empresaExistente);
    }

    private EmpresaEntity getEntity(String ruc){
        EmpresaEntity empresaEntity = new EmpresaEntity();

        SunatResponse datosSunat = executionSunat(ruc);

        if (Objects.nonNull(datosSunat)){
            empresaEntity.setRazonSocial(datosSunat.razonSocial);
            empresaEntity.setTipoDocumento(datosSunat.tipoDocumento);
            empresaEntity.setNumeroDocumento(datosSunat.numeroDocumento);
            empresaEntity.setEstado(datosSunat.estado);
            empresaEntity.setCondicion(datosSunat.condicion);
            empresaEntity.setDireccion(datosSunat.direccion);
            empresaEntity.setUbigeo(datosSunat.ubigeo);
            empresaEntity.setViaTipo(datosSunat.viaTipo);
            empresaEntity.setViaNombre(datosSunat.viaNombre);
            empresaEntity.setZonaCodigo(datosSunat.zonaCodigo);
            empresaEntity.setZonaTipo(datosSunat.zonaTipo);
            empresaEntity.setNumero(datosSunat.numero);
            empresaEntity.setInterior(datosSunat.interior);
            empresaEntity.setLote(datosSunat.lote);
            empresaEntity.setDpto(datosSunat.dpto);
            empresaEntity.setManzana(datosSunat.manzana);
            empresaEntity.setKilometro(datosSunat.kilometro);
            empresaEntity.setDistrito(datosSunat.distrito);
            empresaEntity.setProvincia(datosSunat.provincia);
            empresaEntity.setDepartamento(datosSunat.departamento);
            empresaEntity.setEsAgenteRetencion(datosSunat.esAgenteRetencion);
            empresaEntity.setTipo(datosSunat.tipo);
            empresaEntity.setActividadEconomica(datosSunat.actividadEconomica);
            empresaEntity.setNumeroTrabajadores(datosSunat.numeroTrabajadores);
            empresaEntity.setTipoFacturacion(datosSunat.tipoFacturacion);
            empresaEntity.setTipoContabilidad(datosSunat.tipoContabilidad);
            empresaEntity.setComercioExterior(datosSunat.comercioExterior);
            empresaEntity.setUserCreated(Constans.USER_CREATED);
            empresaEntity.setDateCreated(new Timestamp(System.currentTimeMillis()));
        }

        return empresaEntity;
    }

    private SunatResponse executionSunat(String ruc) {
        String tokenOk = Constans.BEARER + token;
        return sunatClient.getEmpresa(ruc, tokenOk);
    }
}
