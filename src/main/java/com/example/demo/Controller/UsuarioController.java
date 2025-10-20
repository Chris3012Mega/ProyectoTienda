package com.example.demo.Controller;


import com.example.demo.Modelo.Entity.Usuario;
import com.example.demo.Modelo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        usuarioService.registrarUsuario(usuario);
        model.addAttribute("mensaje", "Usuario registrado correctamente");
        return "login";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(@ModelAttribute Usuario usuario, Model model) {
        Usuario usuarioLogueado = usuarioService.iniciarSesion(usuario.getCorreo(), usuario.getContraseña());
        if (usuarioLogueado != null) {
            model.addAttribute("usuario", usuarioLogueado);
            return "bienvenida"; // vista que mostrarás al iniciar sesión
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }
}

