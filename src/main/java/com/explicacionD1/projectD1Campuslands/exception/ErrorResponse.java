package com.explicacionD1.projectD1Campuslands.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp, int status, String message, String errorCode
) {
}
