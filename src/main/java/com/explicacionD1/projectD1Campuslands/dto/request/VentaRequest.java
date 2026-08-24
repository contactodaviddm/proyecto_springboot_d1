package com.explicacionD1.projectD1Campuslands.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;

public record VentaRequest(
        @NotNull(message = "La fecha no puede ser nula")
        Date fecha ,
        @NotNull(message = "El total no puede estar nulo")
        @Positive(message = "El total debe ser positivo")
        @Digits(integer = 10, fraction = 2, message = "El total debe tener maximo 10 digitos y 2 decimales")
        BigDecimal total
) {
}
