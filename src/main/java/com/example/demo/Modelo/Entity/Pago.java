package com.example.demo.Modelo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Tipo tipo; // VENTA o COMPRA

    private Long referenciaId;
    private double monto;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago = MetodoPago.EFECTIVO;

    private LocalDateTime fechaPago = LocalDateTime.now();

    public enum Tipo {
        VENTA, COMPRA
    }

    public enum MetodoPago {
        EFECTIVO, TARJETA, TRANSFERENCIA
    }
}