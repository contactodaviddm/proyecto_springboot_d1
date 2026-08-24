package com.explicacionD1.projectD1Campuslands.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DetalleVentaRequest(
        @NotNull(message = "La venta no puede ser nula")
        @Positive(message = "El codigo de venta debe ser positivo")
        Long ventaId,
        @NotNull(message = "El producto no puede ser nula")
        @Positive(message = "El codigo de producto debe ser positivo")
        Long productoId,
        @NotNull(message = "La cantidad no puede estar nulo")
        @Positive(message = "La cantidad debe ser positia")
        @Digits(integer = 10, fraction = 2, message = "la cantidad debe tener maximo 10 digitos y 2 decimales")
        Double cantidad,
        @NotNull(message = "El subtotal no puede estar nulo")
        @Positive(message = "El subtotal debe ser positivo")
        @Digits(integer = 10, fraction = 2, message = "El subtotal debe tener maximo 10 digitos y 2 decimales")
        BigDecimal subtotal
) {
}
