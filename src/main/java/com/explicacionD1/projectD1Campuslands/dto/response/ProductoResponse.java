package com.explicacionD1.projectD1Campuslands.dto.response;

import java.math.BigDecimal;

public record ProductoResponse(
        Long id,
        String nombre,
        BigDecimal precioCompra,
        BigDecimal precioVenta
) {
}
