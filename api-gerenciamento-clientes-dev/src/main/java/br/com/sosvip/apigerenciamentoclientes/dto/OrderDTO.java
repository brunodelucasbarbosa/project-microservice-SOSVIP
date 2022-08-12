package br.com.sosvip.apigerenciamentoclientes.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class OrderDTO {
    @NotNull
    private Long clientId;
    @NotNull
    private Double totalAmount;

    private String description;
    @NotNull
    private Timestamp date;

    private String clientEmail;

    public OrderDTO(){}

    public OrderDTO(Long clientId, Double totalAmount, String description, Timestamp date) {
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.description = description;
        this.date = date;
    }

    public OrderDTO(Long clientId, Double totalAmount, String description, Timestamp date, String clientEmail) {
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.description = description;
        this.date = date;
        this.clientEmail = clientEmail;
    }

    public Long getClientId() {

        return clientId;
    }

    public Double getTotalAmount() {

        return totalAmount;
    }

    public String getDescription() {

        return description;
    }

    public Timestamp getDate() {

        return date;
    }

    public String getClientEmail() {
        return clientEmail;
    }
}

