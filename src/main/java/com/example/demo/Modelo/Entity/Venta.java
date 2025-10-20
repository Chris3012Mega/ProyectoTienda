package com.example.demo.Modelo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producto;
    private int cantidad;
    private double precioUnitario;
    private LocalDate fechaVenta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // quién realizó la venta
}
