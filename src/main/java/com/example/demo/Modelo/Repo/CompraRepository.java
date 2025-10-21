package com.example.demo.Modelo.Repo;

import com.example.demo.Modelo.Entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Procedure(procedureName = "registrar_compra")
    void registrarCompra(
            @Param("p_proveedor") String proveedor,
            @Param("p_producto") String producto,
            @Param("p_cantidad") int cantidad,
            @Param("p_costo_unitario") double costoUnitario,
            @Param("p_fecha_compra") LocalDate fechaCompra,
            @Param("p_usuario_id") Long usuarioId
    );

    @Query(value = "CALL ver_compras_usuario(:p_usuario_id)", nativeQuery = true)
    List<Compra> verComprasUsuario(@Param("p_usuario_id") Long usuarioId);

    @Query(value = "CALL ver_clientes_vendedor(:p_proveedor)", nativeQuery = true)
    List<Object[]> verClientesVendedor(@Param("p_proveedor") String proveedor);
}
