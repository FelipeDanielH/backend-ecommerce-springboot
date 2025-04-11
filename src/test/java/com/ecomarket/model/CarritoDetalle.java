package com.ecomarket.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CarritoDetalleTest {

    @Test
    void testCarritoDetalleBuilderAndGetters() {
        // Create related entities
        Carrito carrito = Carrito.builder()
                .id(1)
                .build();

        Producto producto = Producto.builder()
                .id(1)
                .nombre("Producto Test")
                .precio(BigDecimal.valueOf(10.99))
                .build();

        // Create CarritoDetalle using the builder
        CarritoDetalle detalle = CarritoDetalle.builder()
                .id(100)
                .carrito(carrito)
                .producto(producto)
                .cantidad(5)
                .build();

        // Assertions
        assertEquals(100, detalle.getId());
        assertEquals(carrito, detalle.getCarrito());
        assertEquals(producto, detalle.getProducto());
        assertEquals(5, detalle.getCantidad());
        assertEquals("Producto Test", detalle.getProducto().getNombre());
        assertEquals(BigDecimal.valueOf(10.99), detalle.getProducto().getPrecio());
    }

    @Test
    void testCarritoDetalleSetters() {
        // Create related entities
        Carrito carrito = Carrito.builder()
                .id(2)
                .build();

        Producto producto = Producto.builder()
                .id(2)
                .nombre("Producto Updated")
                .precio(BigDecimal.valueOf(20.99))
                .build();

        // Create CarritoDetalle using the no-args constructor
        CarritoDetalle detalle = new CarritoDetalle();

        // Set values using setters
        detalle.setId(200);
        detalle.setCarrito(carrito);
        detalle.setProducto(producto);
        detalle.setCantidad(10);

        // Assertions
        assertEquals(200, detalle.getId());
        assertEquals(carrito, detalle.getCarrito());
        assertEquals(producto, detalle.getProducto());
        assertEquals(10, detalle.getCantidad());
        assertEquals("Producto Updated", detalle.getProducto().getNombre());
        assertEquals(BigDecimal.valueOf(20.99), detalle.getProducto().getPrecio());
    }

    @Test
    void testCarritoDetalleNoArgsConstructor() {
        // Create CarritoDetalle using the no-args constructor
        CarritoDetalle detalle = new CarritoDetalle();

        // Assertions
        assertNull(detalle.getId());
        assertNull(detalle.getCarrito());
        assertNull(detalle.getProducto());
        assertNull(detalle.getCantidad());
    }
}