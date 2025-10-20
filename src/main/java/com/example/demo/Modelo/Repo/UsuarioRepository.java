package com.example.demo.Modelo.Repo;

import com.example.demo.Modelo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Procedure(procedureName = "registrar_usuario")
    void registrarUsuario(
            @Param("p_nombre") String nombre,
            @Param("p_correo") String correo,
            @Param("p_contraseña") String contraseña,
            @Param("p_telefono") String telefono
    );

    @Procedure(procedureName = "iniciar_sesion")
    Usuario iniciarSesion(
            @Param("p_correo") String correo,
            @Param("p_contraseña") String contraseña
    );
}