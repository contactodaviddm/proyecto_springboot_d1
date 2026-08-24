package com.explicacionD1.projectD1Campuslands.controller;

import com.explicacionD1.projectD1Campuslands.dto.request.DetalleVentaRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.DetalleVentaResponse;
import com.explicacionD1.projectD1Campuslands.service.DetalleVentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http:localhost/api/detalle
@RequestMapping("/api/detalle")
@RequiredArgsConstructor
@Validated
public class DetalleVentaController {
    private final DetalleVentaService detalleVentaService;
    @PostMapping
    public ResponseEntity<DetalleVentaResponse> crear(@Valid @RequestBody DetalleVentaRequest dto){
        System.out.println("ENTRA");
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleVentaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaResponse>> listar(){
        return ResponseEntity.ok(detalleVentaService.listarTodos());
    }
    //http://localhost:8080/api/detalle/2
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaResponse> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorId(id));
    }
    //http://localhost:8080/api/detalle/2
    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaResponse> actualizar(@PathVariable Long id,@Valid @RequestBody DetalleVentaRequest dto){
        return ResponseEntity.ok(detalleVentaService.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        detalleVentaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //http://localhost:8080/api/detalle/producto/2
    @GetMapping("/producto/{id}")
    public ResponseEntity<List<DetalleVentaResponse>> listarPorIdProducto(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorIdProducto(id));
    }

    @GetMapping("/venta/{id}")
    public ResponseEntity<List<DetalleVentaResponse>> listarPorIdVenta(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorIdVenta(id));
    }

    //http://localhost:8080/api/detalle/filtro?cantidades=3&stock=5
    @GetMapping("/filtro")
    public ResponseEntity<List<DetalleVentaResponse>> listarPorCantidadesMenorQue(@RequestParam Double cantidades){
        return ResponseEntity.ok(detalleVentaService.filtrarPorCantidadesMenorOIgualQue(cantidades));
    }
}