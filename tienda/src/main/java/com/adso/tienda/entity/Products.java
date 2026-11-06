package com.adso.tienda.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity 
@Data 
@Table (name ="products")
public class Products {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank @Size(max = 150)
    @Column (name = "name")
    private String name;

    @Size(max = 500)
    @Column (name ="description")
    private String description;

    @NotNull @Positive
    @Column (name = "price")
    private BigDecimal price;

    @NotNull @PositiveOrZero
    @Column (name = "stock")
    private Integer stock;

    @NotNull
    @Column (name = "category_id")
    private Long idCategory;

    @NotNull
    @Column (name = "seller_id")
    private Long idSeller;
}
