    package com.example.demo.Modelo.Repo;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Modifying;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;
    import org.springframework.transaction.annotation.Transactional;
    import com.example.demo.Modelo.Entity.Recibo;

    import java.util.List;
    import java.util.Map;

    @Repository
    public interface PedidoProveedorRepository extends JpaRepository<Recibo, Long> {

        // Procedimiento para listar pedidos del vendedor
        @Query(value = "CALL ver_pedidos_por_proveedor(:proveedor)", nativeQuery = true)
        List<Map<String, Object>> verPedidosPorProveedor(@Param("proveedor") String proveedor);


    }
