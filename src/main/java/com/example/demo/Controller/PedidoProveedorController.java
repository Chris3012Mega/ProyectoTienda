package com.example.demo.Controller;

import com.example.demo.Modelo.Repo.PedidoProveedorRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class PedidoProveedorController {

    private final PedidoProveedorRepository pedidoRepo;

    public PedidoProveedorController(PedidoProveedorRepository pedidoRepo) {
        this.pedidoRepo = pedidoRepo;
    }

    // ✅ Ver los pedidos recibidos (para el vendedor logueado)
    @GetMapping("/mis-ventas")
    public String verPedidosProveedor(HttpSession session, Model model) {
        var usuario = (com.example.demo.Modelo.Entity.Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        String nombreProveedor = usuario.getNombre(); // nombre del proveedor logueado
        List<Map<String, Object>> pedidos = pedidoRepo.verPedidosPorProveedor(nombreProveedor);

        model.addAttribute("pedidos", pedidos);
        return "mis-ventas";
    }
}
