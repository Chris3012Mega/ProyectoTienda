package com.example.demo.Modelo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String proveedor;
    private String producto;
    private int cantidad;
    private double costoUnitario;
    private LocalDate fechaCompra;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}