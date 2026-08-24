package com.explicacionD1.projectD1Campuslands.controller;

import com.explicacionD1.projectD1Campuslands.dto.request.VentaRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.VentaResponse;
import com.explicacionD1.projectD1Campuslands.service.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
@Validated
public class VentaController {
    private final VentaService ventaService;
    @PostMapping
    public ResponseEntity<VentaResponse> crear(@Valid @RequestBody VentaRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponse>> listar(){
        return ResponseEntity.ok(ventaService.obtenerTodas());
    }

    @GetMapping("/mayorOIgualQue")
    public ResponseEntity<List<VentaResponse>> filtrarPorTotalMayorOIgualQue(@RequestParam BigDecimal total){
        return ResponseEntity.ok(ventaService.buscarPorTotalMayorQue(total));
    }
    @GetMapping("/filtroEntreFechas")
    public ResponseEntity<List<VentaResponse>> filtrarEntreFechas(@RequestParam String fechaInicio,@RequestParam String fechaFin){
        return ResponseEntity.ok(ventaService.filtrarEntreFechas(fechaInicio, fechaFin));
    }
    //http://localhost:8080/api/productos/2
    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(ventaService.obtenerPorId(id));
    }
    //http://localhost:8080/api/productos/2
    @PutMapping("/{id}")
    public ResponseEntity<VentaResponse> actualizar(@PathVariable Long id, @Valid @RequestBody VentaRequest dto){
        return ResponseEntity.ok(ventaService.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        ventaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
