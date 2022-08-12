package br.com.sosvip.apigerenciamentopedidos.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_client")
    private Long clientId;

    @NotNull
    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "date")
    private Timestamp date;


    @NotNull
    @Column(name = "status")
    private Boolean status;

    public Order(){

    }

    public Order(Long clientId, Double totalAmount, String description, Timestamp date, Boolean status) {
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public Order(Long clientId, Double totalAmount, String description, Timestamp date) {
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.description = description;
        this.date = date;
    }

    public Order(Long id, Long clientId, Double totalAmount, String description, Timestamp date, Boolean status) {
        this.id = id;
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
