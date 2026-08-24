package com.explicacionD1.projectD1Campuslands.controller;

import com.explicacionD1.projectD1Campuslands.dto.request.ProductoRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.ProductoResponse;
import com.explicacionD1.projectD1Campuslands.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Producto", description = "Procesa el CRUD de productos")
@RestController
//http://localhost:8080/api/productos
@RequestMapping("/api/producto")
@RequiredArgsConstructor
@Validated
public class ProductoController {
    private final ProductoService productoService;

    @Operation(summary = "Ingresa datos de productos", description = "Requiere un ResquestBody o un json para ingresar información.")
    @ApiResponses(
            value={
                    @ApiResponse(responseCode = "201", description = "Producto Creado exitosamente!"),
                    @ApiResponse(responseCode = "400", description = "Datos no validos/ body mal estructurado")
            }
    )
    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(dto));
    }

    @Operation(summary = "Obtiene todos los productos", description = "No requiere parametro alguno")
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listar(){
        return ResponseEntity.ok(productoService.obtenerTodas());
    }

    @Operation(summary = "Obtiene los productos filtrados por nombre",
            description = "Requiere una varible de busqueda de la siguiente forma http://localhost:8080/buscarPorNombre?nombre='Pera'")
    @GetMapping("/buscarPorNombre")
    public ResponseEntity<List<ProductoResponse>> mostrarPorNombre(
            @Parameter(description = "Nombre a filtrar", example = "Pizza")
            @RequestParam String nombre){
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    //http://localhost:8080/api/productos/2
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(
            @Parameter(description = "Id del producto a buscar", example = "1")
            @PathVariable Long id){
        return ResponseEntity.ok(productoService.obtenerrPorId(id));
    }
    //http://localhost:8080/api/productos/2
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest dto){
        return ResponseEntity.ok(productoService.actualizarProducto(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
