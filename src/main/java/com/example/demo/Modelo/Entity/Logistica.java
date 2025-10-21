package com.example.demo.Modelo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "logistica")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Logistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(nullable = false, length = 100)
    private String producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "fecha_movimiento")
    private LocalDate fechaMovimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.EN_PROCESO;

    public enum Tipo {
        ENTRADA, SALIDA
    }

    public enum Estado {
        EN_PROCESO, COMPLETADO, CANCELADO
    }
}
