package com.example.demo.Controller;

import com.example.demo.Modelo.Entity.Compra;
import com.example.demo.Modelo.Entity.ItemCarrito;
import com.example.demo.Modelo.Service.CompraService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private final CompraService compraService;

    public CarritoController(CompraService compraService) {
        this.compraService = compraService;
    }

    // ✅ Agregar producto al carrito
    @PostMapping("/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id,
                                   @RequestParam("cantidad") int cantidad,
                                   HttpSession session) {

        Compra producto = compraService.buscarPorId(id);
        if (producto == null) {
            return "redirect:/catalogo";
        }

        // Obtener carrito de sesión
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        // Verificar si el producto ya existe en el carrito
        Optional<ItemCarrito> existente = carrito.stream()
                .filter(i -> i.getProductoId().equals(id))
                .findFirst();

        if (existente.isPresent()) {
            ItemCarrito item = existente.get();
            item.setCantidad(item.getCantidad() + cantidad);
        } else {
            carrito.add(new ItemCarrito(
                    producto.getId(),
                    producto.getProducto(),
                    producto.getCostoUnitario(),
                    cantidad
            ));
        }

        // Guardar carrito actualizado
        session.setAttribute("carrito", carrito);

        return "redirect:/carrito"; // Redirige a la vista del carrito
    }

    // ✅ Mostrar carrito
    @GetMapping
    public String verCarrito(HttpSession session, Model model) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null) carrito = new ArrayList<>();

        double total = carrito.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
        model.addAttribute("carrito", carrito);
        model.addAttribute("total", total);

        return "carrito";
    }

    // ✅ Eliminar producto del carrito
    @GetMapping("/eliminar/{id}")
    public String eliminarDelCarrito(@PathVariable Long id, HttpSession session) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito != null) {
            carrito.removeIf(item -> item.getProductoId().equals(id));
            session.setAttribute("carrito", carrito);
        }
        return "redirect:/carrito";
    }
    @GetMapping("/pago")
    public String mostrarOpcionesPago(HttpSession session, Model model) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        double total = 0;
        if (carrito != null) {
            total = carrito.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
        }
        model.addAttribute("total", total);
        return "pago";
    }


}
