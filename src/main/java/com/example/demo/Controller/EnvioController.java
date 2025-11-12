package com.example.demo.Controller;

import com.example.demo.Modelo.Entity.ItemCarrito;
import com.example.demo.Modelo.Repo.ReciboRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EnvioController {

    private final ReciboRepository reciboRepository;

    public EnvioController(ReciboRepository reciboRepository) {
        this.reciboRepository = reciboRepository;
    }

    // ✅ Mostrar formulario de envío
    @GetMapping("/envio")
    public String mostrarFormularioEnvio(Model model) {
        return "envio";
    }

    // ✅ Procesar formulario de envío y guardar en BD
    @PostMapping("/envio")
    public String procesarEnvio(@RequestParam String nombre,
                                @RequestParam String direccion,
                                @RequestParam String ciudad,
                                @RequestParam String telefono,
                                @RequestParam(required = false) String referencia,
                                HttpSession session,
                                Model model) {

        // 🟢 Recuperar usuario logueado y carrito
        var usuario = (com.example.demo.Modelo.Entity.Usuario) session.getAttribute("usuarioLogueado");
        var carrito = (List<ItemCarrito>) session.getAttribute("carrito");

        // 🟢 Calcular total (por si no está guardado en sesión)
        double total = 0.0;
        if (carrito != null) {
            total = carrito.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
        }

        if (usuario == null || carrito == null || carrito.isEmpty()) {
            return "redirect:/carrito";
        }

        // ✅ Registrar el recibo con la información de envío (usa tu procedimiento almacenado)
        reciboRepository.registrarRecibo(
                usuario.getId(),
                total,
                direccion,
                ciudad,
                telefono,
                referencia
        );

        // 🟡 Obtener el ID del último recibo registrado (forma rápida con SQL AUTO_INCREMENT)
        // Nota: si quieres hacerlo más preciso, crea un método en el repositorio para obtenerlo
        Long ultimoReciboId = reciboRepository.findAll()
                .stream()
                .mapToLong(r -> r.getId())
                .max()
                .orElse(0L);

        // ✅ Registrar detalle de cada producto comprado
        for (ItemCarrito item : carrito) {
            reciboRepository.registrarDetalleRecibo(
                    ultimoReciboId,
                    item.getProductoId(),
                    item.getCantidad(),
                    item.getPrecioUnitario(),
                    item.getSubtotal()
            );
        }

        // 🧹 Limpiar carrito y total
        session.removeAttribute("carrito");
        session.removeAttribute("totalCarrito");

        // ✅ Mensaje de confirmación
        model.addAttribute("mensaje", "✅ Pedido confirmado con éxito. En breve será enviado.");
        model.addAttribute("nombre", nombre);
        model.addAttribute("direccion", direccion);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("telefono", telefono);
        model.addAttribute("referencia", referencia);
        model.addAttribute("total", total);

        return "confirmacion-envio";
    }
}
