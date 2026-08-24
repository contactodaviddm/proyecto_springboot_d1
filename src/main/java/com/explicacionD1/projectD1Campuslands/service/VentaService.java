package com.explicacionD1.projectD1Campuslands.service;


import com.explicacionD1.projectD1Campuslands.dto.request.VentaRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.VentaResponse;

import java.math.BigDecimal;
import java.util.List;

public interface VentaService {
    VentaResponse guardar(VentaRequest dto);
    List<VentaResponse> obtenerTodas();
    VentaResponse obtenerPorId(Long id);
    VentaResponse actualizar(Long id, VentaRequest dto);
    void eliminar(Long id);
    List<VentaResponse> buscarPorTotalMayorQue(BigDecimal total);
    List<VentaResponse> filtrarEntreFechas(String fechaInicio, String fechaFin);
}
