package com.explicacionD1.projectD1Campuslands.service;

import com.explicacionD1.projectD1Campuslands.dto.request.DetalleVentaRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.DetalleVentaResponse;
import com.explicacionD1.projectD1Campuslands.model.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {
    DetalleVentaResponse crear(DetalleVentaRequest dto);
    DetalleVentaResponse actualizar(Long id, DetalleVentaRequest dto);
    void eliminar(Long id);
    List<DetalleVentaResponse> listarTodos();
    DetalleVentaResponse buscarPorId(Long id);
    List<DetalleVentaResponse> buscarPorIdProducto(Long id);
    List<DetalleVentaResponse> buscarPorIdVenta(Long id);
    List<DetalleVentaResponse> filtrarPorCantidadesMenorOIgualQue(Double cantidad);
}
