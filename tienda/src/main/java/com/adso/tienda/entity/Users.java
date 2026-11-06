package com.adso.tienda.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity 
@Data 
@Table (name = "users")
public class Users {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank (message = "el campo no pude ir nullo")
    @Size(max = 50)
    @Column (name = "username")
    private String userName;

    @NotBlank
    @Email (message = "el campo no pude ir nullo ")
    @Size(max = 255)
    @Column (name = "email")
    private String email;

    @NotBlank (message = "el campo no pude ir nullo")
    @Column (name = "password")
    private String password;

    @NotNull
    @Column (name = "active")
    private Boolean active;

    @NotNull (message = "el campo va nullo")
    private Long rolId;

}
