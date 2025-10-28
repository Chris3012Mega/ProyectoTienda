package com.example.demo.Modelo.Service;

import com.example.demo.Modelo.Entity.Usuario;
import com.example.demo.Modelo.Repo.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarUsuario(Usuario usuario) {
        usuarioRepository.registrarUsuario(
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getContraseña(),
                usuario.getTelefono()
        );
    }

    public Usuario iniciarSesion(String correo, String contraseña) {
        return usuarioRepository.iniciarSesion(correo, contraseña);
    }

    // 🔹 Nuevo método
    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}


