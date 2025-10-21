package com.example.demo.Modelo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(name = "referencia_id")
    private Long referenciaId;

    @Column(nullable = false)
    private double monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago = MetodoPago.EFECTIVO;

    @Column(name = "fecha_pago", columnDefinition = "DATETIME")
    private LocalDateTime fechaPago = LocalDateTime.now();


    public enum Tipo {
        VENTA, COMPRA
    }

    public enum MetodoPago {
        EFECTIVO, TARJETA, TRANSFERENCIA
    }
}
