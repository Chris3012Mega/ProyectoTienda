package com.example.demo.Modelo.Repo;

import com.example.demo.Modelo.Entity.Compra;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Modifying
    @Transactional
    @Query(value = "CALL registrar_compra(:proveedor, :producto, :cantidad, :costoUnitario, :usuarioId)", nativeQuery = true)
    void registrarCompra(@Param("proveedor") String proveedor,
                         @Param("producto") String producto,
                         @Param("cantidad") int cantidad,
                         @Param("costoUnitario") double costoUnitario,
                         @Param("usuarioId") Long usuarioId);

    @Query("SELECT c FROM Compra c WHERE c.usuario.id = :usuarioId")
    List<Compra> verComprasUsuario(@Param("usuarioId") Long usuarioId);
}
