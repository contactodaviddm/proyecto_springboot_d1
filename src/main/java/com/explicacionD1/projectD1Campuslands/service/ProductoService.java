package com.explicacionD1.projectD1Campuslands.service;

import com.explicacionD1.projectD1Campuslands.dto.request.ProductoRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.ProductoResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoService {
    ProductoResponse guardar(ProductoRequest dto);
    List<ProductoResponse> obtenerTodas();
    ProductoResponse obtenerrPorId(Long id);
    ProductoResponse actualizarProducto(Long id, ProductoRequest dto);
    void eliminarProducto(Long id);
    List<ProductoResponse> buscarPorNombre(String nombre);
    List<ProductoResponse> filtrarPrecioVentaMayorOIgualQue(BigDecimal precio);
    List<ProductoResponse> filtrarPrecioVentaMenorOIgualQue(BigDecimal precio);
    List<ProductoResponse> filtrarPrecioVentaEntre(BigDecimal precio1, BigDecimal precio2);
    List<ProductoResponse> filtrarPorNombreYPrecioVentaMayorOIgualQue(String nombre, BigDecimal precio);
}
