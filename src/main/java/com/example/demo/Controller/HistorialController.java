package com.example.demo.Controller;

import com.example.demo.Modelo.Entity.Recibo;
import com.example.demo.Modelo.Repo.ReciboRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistorialController {

    private final ReciboRepository reciboRepository;

    public HistorialController(ReciboRepository reciboRepository) {
        this.reciboRepository = reciboRepository;
    }

    // ✅ Mostrar historial del usuario logueado
    @GetMapping("/historial")
    public String verHistorial(Model model, HttpSession session) {
        var usuario = (com.example.demo.Modelo.Entity.Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        List<Recibo> pedidos = reciboRepository.findByUsuarioId(usuario.getId());
        model.addAttribute("pedidos", pedidos);
        return "historial";
    }
}
