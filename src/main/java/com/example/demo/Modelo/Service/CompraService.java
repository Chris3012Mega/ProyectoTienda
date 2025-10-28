package com.example.demo.Modelo.Service;

import com.example.demo.Modelo.Entity.Compra;
import com.example.demo.Modelo.Repo.CompraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Transactional
    public void guardarProducto(Compra compra, Long usuarioId) {
        // Llamamos al procedimiento almacenado
        compraRepository.registrarCompra(
                compra.getProveedor(),
                compra.getProducto(),
                compra.getCantidad(),
                compra.getCostoUnitario(),
                usuarioId
        );
    }

    public List<Compra> listarProductosUsuario(Long usuarioId) {
        return compraRepository.verComprasUsuario(usuarioId);
    }
}
