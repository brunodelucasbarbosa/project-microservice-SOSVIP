package br.com.sosvip.apigerenciamentoclientes.dto;

import java.sql.Timestamp;

public class OrderDTOSqsRequest {

    private Long clientId;
    private Double totalAmount;
    private String description;
    private Timestamp date;
    private String clientEmail;

    public OrderDTOSqsRequest(Long clientId, Double totalAmount, String description, Timestamp date, String clientEmail) {
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.description = description;
        this.date = date;
        this.clientEmail = clientEmail;
    }

    public OrderDTOSqsRequest() {

    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
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
