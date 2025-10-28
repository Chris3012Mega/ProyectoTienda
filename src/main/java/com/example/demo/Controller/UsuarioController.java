package com.example.demo.Controller;

import com.example.demo.Modelo.Entity.Usuario;
import com.example.demo.Modelo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private final sha256 sha = new sha256(); // Clase para SHA-256

    // 📌 Formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrar"; // nombre del HTML
    }

    // 📌 Guardar nuevo usuario
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        // Validar si el correo ya existe
        if (usuarioService.buscarPorCorreo(usuario.getCorreo()) != null) {
            model.addAttribute("error", "El correo ya está registrado");
            return "registrar";
        }

        // Guardar usuario llamando al procedimiento (no aplicar SHA en Java)
        usuarioService.registrarUsuario(usuario);

        model.addAttribute("mensaje", "Usuario registrado correctamente");
        return "login";
    }

    // 📌 Mostrar login
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    // 📌 Iniciar sesión
    @PostMapping("/login")
    public String iniciarSesion(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        Usuario usuarioBD = usuarioService.buscarPorCorreo(usuario.getCorreo());

        if (usuarioBD != null) {
            // Generar hash SHA-256 de la contraseña ingresada
            String hashIngresado = sha.sha256(usuario.getContraseña());

            // Comparar con el hash que está en la DB
            if (hashIngresado.equals(usuarioBD.getContraseña())) {
                session.setAttribute("usuarioLogueado", usuarioBD);
                return "redirect:/";
            }
        }

        model.addAttribute("error", "❌ Correo o contraseña incorrectos");
        return "login";
    }

    // 📌 Cerrar sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/usuario/login";
    }
}
