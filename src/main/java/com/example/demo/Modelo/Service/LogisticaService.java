package com.example.demo.Modelo.Service;

import com.example.demo.Modelo.Repo.LogisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class LogisticaService {

    @Autowired
    private LogisticaRepository logisticaRepository;

    // Registrar un nuevo movimiento logístico
    public void registrarMovimiento(Long compraId, String tipo, String producto, int cantidad, LocalDate fechaMovimiento) {
        logisticaRepository.registrarLogistica(compraId, tipo, producto, cantidad, java.sql.Date.valueOf(fechaMovimiento));
    }

    // Ver historial de logística del usuario comprador
    public List<Object[]> obtenerHistorialUsuario(Long usuarioId) {
        return logisticaRepository.verHistorialUsuario(usuarioId);
    }

    // Ver historial de logística del vendedor
    public List<Object[]> obtenerHistorialVendedor(String proveedor) {
        return logisticaRepository.verHistorialVendedor(proveedor);
    }

    // Actualizar el estado de un movimiento
    public void actualizarEstado(Long id, String nuevoEstado) {
        logisticaRepository.actualizarEstado(id, nuevoEstado);
    }
}