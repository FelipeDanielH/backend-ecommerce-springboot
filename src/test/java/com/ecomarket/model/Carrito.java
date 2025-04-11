package com.ecomarket.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CarritoTest {



        @Test
        void testCarritoBuilderAndGetters() {
            Usuario comprador = Usuario.builder()
                    .id(1)
                    .nombre("Juan")
                    .email("juan@example.com")
                    .password("secreto123")
                    .tipo(null)
                    .build();

            Usuario vendedor = Usuario.builder()
                    .id(2)
                    .nombre("Vendedor")
                    .email("vend@example.com")
                    .password("vendedor123")
                    .tipo(null)
                    .build();

            Producto producto = Producto.builder()
                    .id(1)
                    .nombre("Producto Test")
                    .precio(BigDecimal.valueOf(10.99))
                    .vendedor(vendedor)
                    .build();

            Carrito carrito = Carrito.builder()
                    .id(10)
                    .usuario(comprador)
                    .build();

            CarritoDetalle detalle = CarritoDetalle.builder()
                    .id(100)
                    .producto(producto)
                    .cantidad(2)
                    .build();

            // Add the detalle to the carrito
            carrito.setDetalles(Collections.singletonList(detalle));

            // Validations
            assertEquals(10, carrito.getId());
            assertEquals(comprador, carrito.getUsuario());
            assertNotNull(carrito.getDetalles());
            assertEquals(1, carrito.getDetalles().size());
            assertEquals(detalle, carrito.getDetalles().get(0));
            assertNotNull(carrito.getDetalles().get(0).getProducto());
            assertEquals("Producto Test", carrito.getDetalles().get(0).getProducto().getNombre());

            assertNotNull(carrito.getFechaCreacion());
            assertTrue(carrito.getFechaCreacion().isBefore(LocalDateTime.now().plusSeconds(1)));
        }


    @Test
    void testSettersAndNoArgsConstructor() {
        Carrito carrito = new Carrito();

        Usuario usuario = Usuario.builder()
                .id(2)
                .nombre("Maria")
                .email("maria@example.com")
                .password("password")
                .tipo(null)
                .build();

        carrito.setId(5);
        carrito.setUsuario(usuario);
        carrito.setFechaCreacion(LocalDateTime.of(2023, 12, 1, 10, 0));
        carrito.setDetalles(Collections.emptyList());

        assertEquals(5, carrito.getId());
        assertEquals(usuario, carrito.getUsuario());
        assertEquals("Maria", carrito.getUsuario().getNombre());
        assertEquals(LocalDateTime.of(2023, 12, 1, 10, 0), carrito.getFechaCreacion());
        assertNotNull(carrito.getDetalles());
        assertTrue(carrito.getDetalles().isEmpty());
    }
}