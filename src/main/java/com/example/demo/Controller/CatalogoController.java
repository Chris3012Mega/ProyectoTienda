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
public class CatalogoController {

    private final CompraService compraService;
    private final UsuarioService usuarioService;


    public CatalogoController(CompraService compraService, UsuarioService usuarioService) {
        this.compraService = compraService;
        this.usuarioService = usuarioService;
    }


    @GetMapping("/catalogo")
    public String verCatalogo(Model model) {

        List<Compra> productos = compraService.listarTodos();
        model.addAttribute("productos", productos);
        return "catalogo";
    }


    @GetMapping("/productos/nuevo")
    public String nuevoProducto(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        model.addAttribute("compra", new Compra());
        return "nuevo_producto";
    }


    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Compra compra, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        compraService.guardarProducto(compra, usuario.getId());
        return "redirect:/catalogo";
    }
}
