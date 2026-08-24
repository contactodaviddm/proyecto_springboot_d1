package com.explicacionD1.projectD1Campuslands.dto.response;

import java.math.BigDecimal;
import java.util.Date;

public record VentaResponse(
        Long id,
        Date fecha,
        BigDecimal total
) {
}
