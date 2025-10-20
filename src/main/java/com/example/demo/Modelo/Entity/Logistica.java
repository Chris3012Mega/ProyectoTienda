package com.example.demo.Modelo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Logistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Tipo tipo; // ENTRADA o SALIDA

    private String producto;
    private int cantidad;
    private LocalDate fechaMovimiento;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.EN_PROCESO;

    public enum Tipo {
        ENTRADA, SALIDA
    }

    public enum Estado {
        EN_PROCESO, COMPLETADO, CANCELADO
    }
}
