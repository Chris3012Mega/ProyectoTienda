package com.example.demo.Modelo.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final HttpSession session;

    public SessionService(HttpSession session) {
        this.session = session;
    }

    // Guardar usuario en sesión al iniciar sesión
    public void setUsuarioLogueado(String correo, Long idUsuario) {
        session.setAttribute("usuarioCorreo", correo);
        session.setAttribute("usuarioId", idUsuario);
    }

    public Long getUsuarioLogueadoId() {
        return (Long) session.getAttribute("usuarioId");
    }

    public String getUsuarioLogueadoCorreo() {
        return (String) session.getAttribute("usuarioCorreo");
    }

    public boolean estaLogueado() {
        return session.getAttribute("usuarioId") != null;
    }

    public void cerrarSesion() {
        session.invalidate();
    }
}
