package com.example.demo.Modelo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String proveedor;

    @Column(nullable = false, length = 100)
    private String producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "costo_unitario", nullable = false)
    private double costoUnitario;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}