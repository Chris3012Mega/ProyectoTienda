package com.example.demo.Controller;

import com.example.demo.Modelo.Entity.Recibo;
import com.example.demo.Modelo.Repo.PedidoProveedorRepository;
import com.example.demo.Modelo.Repo.ReciboRepository;
import com.example.demo.Modelo.Entity.Usuario;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mis-ventas")
@RequiredArgsConstructor
public class PedidoProveedorController {

    private final PedidoProveedorRepository pedidoRepo;
    private final ReciboRepository reciboRepository;

    // ✅ Mostrar los pedidos recibidos (solo del proveedor logueado)
    @GetMapping
    public String verPedidosProveedor(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        String nombreProveedor = usuario.getNombre(); // nombre del proveedor logueado
        List<Map<String, Object>> pedidos = pedidoRepo.verPedidosPorProveedor(nombreProveedor);

        model.addAttribute("pedidos", pedidos);
        return "mis-ventas";
    }

    @PostMapping("/actualizar-estado/{id}")
    @Transactional
    public String actualizarEstado(@PathVariable("id") Long id,
                                   @RequestParam("nuevoEstado") String nuevoEstado,
                                   HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        // ✅ Convierte el texto a Enum
        Recibo.Estado estadoEnum = Recibo.Estado.valueOf(nuevoEstado);

        // ✅ Actualiza el estado con el Enum
        reciboRepository.actualizarEstadoRecibo(id, estadoEnum);

        return "redirect:/mis-ventas";
    }

}
