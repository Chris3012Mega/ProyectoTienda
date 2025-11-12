package com.example.demo.Modelo.Repo;

import com.example.demo.Modelo.Entity.Recibo;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReciboRepository extends JpaRepository<Recibo, Long> {

    @Modifying
    @Transactional
    @Query(value = "CALL registrar_recibo(:usuarioId, :total, :direccionEnvio, :ciudad, :telefono, :referencia)", nativeQuery = true)
    void registrarRecibo(@Param("usuarioId") Long usuarioId,
                         @Param("total") Double total,
                         @Param("direccionEnvio") String direccionEnvio,
                         @Param("ciudad") String ciudad,
                         @Param("telefono") String telefono,
                         @Param("referencia") String referencia);

    @Modifying
    @Transactional
    @Query(value = "CALL registrar_detalle_recibo(:reciboId, :productoId, :cantidad, :precioUnitario, :subtotal)", nativeQuery = true)
    void registrarDetalleRecibo(@Param("reciboId") Long reciboId,
                                @Param("productoId") Long productoId,
                                @Param("cantidad") Integer cantidad,
                                @Param("precioUnitario") Double precioUnitario,
                                @Param("subtotal") Double subtotal);



    @Query("SELECT r FROM Recibo r WHERE r.usuarioId = :usuarioId ORDER BY r.fecha DESC")
    List<Recibo> findByUsuarioId(Long usuarioId);
}
