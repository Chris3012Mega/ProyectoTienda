package com.example.demo.Controller;

import com.example.demo.Modelo.Entity.Compra;
import com.example.demo.Modelo.Entity.Usuario;
import com.example.demo.Modelo.Service.CompraService;
import com.example.demo.Modelo.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class CatalogoController {

    private final CompraService compraService;
    private final UsuarioService usuarioService;

    // Inyección vía constructor
    public CatalogoController(CompraService compraService, UsuarioService usuarioService) {
        this.compraService = compraService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/catalogo")
    public String verCatalogo(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/usuario/login";

        List<Compra> productos = compraService.listarProductosUsuario(usuario.getId());
        model.addAttribute("productos", productos);
        return "catalogo";
    }

    @GetMapping("/nuevo")
    public String nuevoProducto(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/usuario/login";

        model.addAttribute("compra", new Compra());
        return "nuevo_producto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Compra compra, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/usuario/login";

        // Asegúrate que el método en el servicio reciba un objeto Compra
        compraService.guardarProducto(compra, usuario.getId());
        return "redirect:/productos/catalogo";
    }
}
