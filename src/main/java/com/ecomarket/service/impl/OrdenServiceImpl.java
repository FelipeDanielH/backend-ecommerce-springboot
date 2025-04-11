package com.ecomarket.service.impl;

import com.ecomarket.dto.DetalleOrdenDTO;
import com.ecomarket.dto.OrdenDTO;
import com.ecomarket.model.DetalleOrden;
import com.ecomarket.model.Orden;
import com.ecomarket.model.Producto;
import com.ecomarket.model.Usuario;
import com.ecomarket.model.enums.EstadoOrden;
import com.ecomarket.repository.DetalleOrdenRepository;
import com.ecomarket.repository.OrdenRepository;
import com.ecomarket.repository.ProductoRepository;
import com.ecomarket.repository.UsuarioRepository;
import com.ecomarket.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetalleOrdenRepository detalleOrdenRepository;

    @Override
    @Transactional
    public OrdenDTO crearOrden(OrdenDTO ordenDTO) {
        // Buscar el usuario comprador
        Usuario comprador = usuarioRepository.findById(ordenDTO.getCompradorId())
                .orElseThrow(() -> new RuntimeException("Comprador no encontrado"));

        EstadoOrden estadoOrden;

        try {
            estadoOrden = EstadoOrden.valueOf(ordenDTO.getEstado());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado de orden inválido: " + ordenDTO.getEstado());
        }

        // Crear la entidad Orden
        Orden nuevaOrden = Orden.builder()
                .comprador(comprador)
                .total(ordenDTO.getTotal())
                .estado(estadoOrden)
                .detallesOrden(new ArrayList<>()) // Evita posibles NullPointerException
                .build();

        // Guardar la orden primero para obtener su ID
        nuevaOrden = ordenRepository.save(nuevaOrden);

        // Si hay detalles, procesarlos
        if (ordenDTO.getDetalles() != null && !ordenDTO.getDetalles().isEmpty()) {
            Orden finalNuevaOrden = nuevaOrden;
            List<DetalleOrden> detalles = ordenDTO.getDetalles().stream()
                    .map(dto -> {
                        Producto producto = productoRepository.findById(dto.getProductoId())
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                        return DetalleOrden.builder()
                                .orden(finalNuevaOrden)
                                .producto(producto)
                                .cantidad(dto.getCantidad())
                                .precioUnitario(dto.getPrecioUnitario())
                                .build();
                    })
                    .collect(Collectors.toList());

            // Guardar detalles en base de datos
            detalleOrdenRepository.saveAll(detalles);
            nuevaOrden.setDetallesOrden(detalles);
        }

        // Guardar nuevamente la orden con sus detalles
        nuevaOrden = ordenRepository.save(nuevaOrden);

        // Convertir a DTO antes de devolver
        return convertirAOrdenDTO(nuevaOrden);
    }

    @Override
    public OrdenDTO obtenerOrdenPorId(Integer id) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        return convertirAOrdenDTO(orden);
    }

    @Override
    public List<OrdenDTO> obtenerOrdenesPorUsuario(Integer compradorId) {
        return ordenRepository.findByCompradorId(compradorId)
                .stream()
                .map(this::convertirAOrdenDTO)
                .collect(Collectors.toList());
    }

    private OrdenDTO convertirAOrdenDTO(Orden orden) {
        List<DetalleOrdenDTO> detallesDTO = Optional.ofNullable(orden.getDetallesOrden())
                .orElse(List.of())
                .stream()
                .map(detalle -> new DetalleOrdenDTO(
                        detalle.getId(),
                        orden.getId(),
                        detalle.getProducto().getId(),
                        detalle.getProducto().getNombre(),
                        detalle.getCantidad(),
                        detalle.getPrecioUnitario()
                ))
                .collect(Collectors.toList());

        return OrdenDTO.builder()
                .id(orden.getId())
                .compradorId(orden.getComprador().getId())
                .total(orden.getTotal())
                .estado(orden.getEstado().name().toUpperCase())
                .detalles(detallesDTO)
                .fechaOrden(orden.getFechaOrden())
                .build();
    }
}
