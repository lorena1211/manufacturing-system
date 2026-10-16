package com.system.billing.backend.invoice;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(nullable = false)
    private Double subtotal;

    @Column(name = "total_taxes", nullable = false)
    private Double totalTaxes;

    @Column(name = "total_discounts", nullable = false)
    private Double totalDiscounts;

    @Column(nullable = false)
    private Double total;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(length = 20)
    private String status;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> items;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public Double getTotalTaxes() { return totalTaxes; }
    public void setTotalTaxes(Double t) { this.totalTaxes = t; }
    public Double getTotalDiscounts() { return totalDiscounts; }
    public void setTotalDiscounts(Double t) { this.totalDiscounts = t; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime f) { this.creationDate = f; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<InvoiceItem> getItems() { return items; }
    public void setItems(List<InvoiceItem> items) { this.items = items; }
}