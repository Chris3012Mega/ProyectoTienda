package com.example.demo.Modelo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    private String contraseña;
    private String telefono;

    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.USUARIO; // Por defecto, usuario normal

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public enum Rol {
        ADMIN,
        USUARIO
    }
}
