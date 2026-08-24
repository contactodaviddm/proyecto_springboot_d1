package com.explicacionD1.projectD1Campuslands.dto.response;

import java.math.BigDecimal;

public record DetalleVentaResponse(
        Long id,
        VentaResponse venta,
        ProductoResponse producto,
        Double cantidad,
        BigDecimal subtotal
) {
}
