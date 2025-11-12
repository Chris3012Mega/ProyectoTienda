package com.example.demo.Modelo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recibo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private Double total;
    private LocalDateTime fecha = LocalDateTime.now();

    private String direccionEnvio;
    private String ciudad;
    private String telefono;
    private String referencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.EN_PROCESO;

    public enum Estado {
        EN_PROCESO, EN_CAMINO, COMPLETADO
    }
}
