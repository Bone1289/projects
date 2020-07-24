package com.spring.boot.api.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "invoice_item")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Double getPrice() {
        return this.quantity.doubleValue() * product.getPrice();
    }

    public Double calculateInvoice() {
        return quantity.doubleValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
