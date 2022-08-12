package br.com.sosvip.apigerenciamentopedidos.dto;

import br.com.sosvip.apigerenciamentopedidos.model.Order;

import java.sql.Timestamp;

public class OrderDTOSqsRequest {

    private Long clientId;
    private Double totalAmount;
    private String description;
    private Timestamp date;

    private String clientEmail;

    public OrderDTOSqsRequest(){}

    public OrderDTOSqsRequest(Long clientId, Double totalAmount, String description, Timestamp date, String clientEmail) {
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

    @Override
    public String toString() {
        return "OrderDTOSqsRequest{" +
                "clientId=" + clientId +
                ", totalAmount=" + totalAmount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", clientEmail='" + clientEmail + '\'' +
                '}';
    }
}

