package com.example.demo.Modelo.Repo;

import com.example.demo.Modelo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Procedure(procedureName = "registrar_usuario")
    void registrarUsuario(
            String p_nombre,
            String p_correo,
            String p_contraseña,
            String p_telefono
    );

    @Query(value = "CALL iniciar_sesion(:p_correo, :p_contraseña)", nativeQuery = true)
    Usuario iniciarSesion(@Param("p_correo") String correo, @Param("p_contraseña") String contraseña);

    // 🔹 Nuevo método para buscar por correo
    Usuario findByCorreo(String correo);
}

