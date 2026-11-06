package com.adso.tienda.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.adso.tienda.enums.OrdesStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Data 
@Table (name = "orders")
public class Ordes {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "client_id")
    private Long clientId;

    @Column (name = "product_id")
    private Long idProduct;

    @Column (name = "quantity")
    private Integer quantity;

    @Column (name ="total")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column (name ="status")
    private OrdesStatusEnum status;

    @CreationTimestamp 
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
