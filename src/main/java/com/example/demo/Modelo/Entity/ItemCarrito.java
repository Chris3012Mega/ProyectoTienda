package com.example.demo.Modelo.Entity;

public class ItemCarrito {
    private Long productoId;
    private String nombre;
    private Double precioUnitario;
    private Integer cantidad;
    private Double subtotal;

    public ItemCarrito(Long productoId, String nombre, Double precioUnitario, Integer cantidad) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.subtotal = precioUnitario * cantidad;
    }

    public Long getProductoId() { return productoId; }
    public String getNombre() { return nombre; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public Integer getCantidad() { return cantidad; }
    public Double getSubtotal() { return subtotal; }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        this.subtotal = this.precioUnitario * cantidad;
    }
}

