package br.com.sosvip.apigerenciamentopedidos.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestExceptionDetails {
    private int status;
    private String details;
    private LocalDateTime timestamp;
}
