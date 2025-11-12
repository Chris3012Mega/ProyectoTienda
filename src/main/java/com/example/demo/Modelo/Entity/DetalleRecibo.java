package com.example.demo.Modelo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;



@Entity
@Table(name = "detalle_recibo")
public class DetalleRecibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "recibo_id", nullable = false)
    private Recibo recibo;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Compra producto;
}
