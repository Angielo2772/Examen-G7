package com.codigo.Examen_G7.controller;

import com.codigo.Examen_G7.entity.PedidosEntity;
import com.codigo.Examen_G7.service.PedidosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos/v1")
public class PedidosController {

    private final PedidosService pedidoService;

    public PedidosController(PedidosService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/crearPedidos/{personaId}")
    public ResponseEntity<List<PedidosEntity>> crearPedidos(@PathVariable Long personaId,
                                                            @RequestBody List<PedidosEntity> pedidos) {
        List<PedidosEntity> nuevosPedidos = pedidoService.guardarPedidos(personaId, pedidos);
        return new ResponseEntity<>(nuevosPedidos, HttpStatus.CREATED);
    }

    @GetMapping("/buscarTodos/{estado}")
    public ResponseEntity<List<PedidosEntity>> obtenerTodosLosPedidos(@PathVariable String estado) {
        List<PedidosEntity> pedidos = pedidoService.obtenerTodosLosPedidos(estado);
        return new ResponseEntity<>(pedidos,HttpStatus.OK);
    }

    @PutMapping("/actualizarPedido/{id}/cliente/{personaId}")
    public ResponseEntity<PedidosEntity> actualizarPedido(@PathVariable Long id,
                                                         @PathVariable Long personaId,
                                                         @RequestBody PedidosEntity pedido) {
        PedidosEntity pedidoActualizado = pedidoService.actualizarPedido(id,personaId ,pedido);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarPedido/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
