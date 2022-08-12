package com.desafio.m2.exception;

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
