package br.com.sosvip.apigerenciamentopedidos.dto;

import br.com.sosvip.apigerenciamentopedidos.model.Order;

import java.sql.Timestamp;

public class OrderDTORequest {

    private Long clientId;
    private Double totalAmount;
    private String description;
    private Timestamp date;

    public OrderDTORequest(){}

    public OrderDTORequest(Order order) {
        this.clientId = order.getClientId();
        this.totalAmount = order.getTotalAmount();
        this.description = order.getDescription();
        this.date = order.getDate();
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
}
