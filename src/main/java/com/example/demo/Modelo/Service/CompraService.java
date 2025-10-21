package com.example.demo.Modelo.Service;

import com.example.demo.Modelo.Entity.Compra;
import com.example.demo.Modelo.Repo.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public void registrarCompra(Compra compra) {
        compraRepository.registrarCompra(
                compra.getProveedor(),
                compra.getProducto(),
                compra.getCantidad(),
                compra.getCostoUnitario(),
                compra.getFechaCompra(),
                compra.getUsuario().getId()
        );
    }

    public List<Compra> verComprasDeUsuario(Long usuarioId) {
        return compraRepository.verComprasUsuario(usuarioId);
    }

    public List<Object[]> verClientesDeVendedor(String proveedor) {
        return compraRepository.verClientesVendedor(proveedor);
    }
}
