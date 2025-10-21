package com.example.demo.Modelo.Repo;

import com.example.demo.Modelo.Entity.Logistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticaRepository extends JpaRepository<Logistica, Long> {

    // Registrar un movimiento logístico
    @Procedure(procedureName = "registrar_logistica")
    void registrarLogistica(
            @Param("p_compra_id") Long compraId,
            @Param("p_tipo") String tipo,
            @Param("p_producto") String producto,
            @Param("p_cantidad") int cantidad,
            @Param("p_fecha_movimiento") java.sql.Date fechaMovimiento
    );

    // Ver historial logístico del usuario comprador
    @Procedure(procedureName = "ver_historial_logistica_usuario")
    List<Object[]> verHistorialUsuario(@Param("p_usuario_id") Long usuarioId);

    // Ver historial logístico del vendedor
    @Procedure(procedureName = "ver_historial_logistica_vendedor")
    List<Object[]> verHistorialVendedor(@Param("p_proveedor") String proveedor);

    // Actualizar estado del movimiento logístico
    @Procedure(procedureName = "actualizar_estado_logistica")
    void actualizarEstado(
            @Param("p_id") Long id,
            @Param("p_nuevo_estado") String nuevoEstado
    );
}