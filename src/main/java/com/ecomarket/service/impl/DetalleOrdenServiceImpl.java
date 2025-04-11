package com.ecomarket.service.impl;

import com.ecomarket.dto.DetalleOrdenDTO;
import com.ecomarket.model.DetalleOrden;
import com.ecomarket.model.Orden;
import com.ecomarket.model.Producto;
import com.ecomarket.model.Usuario;
import com.ecomarket.repository.DetalleOrdenRepository;
import com.ecomarket.repository.OrdenRepository;
import com.ecomarket.repository.ProductoRepository;
import com.ecomarket.service.DetalleOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetalleOrdenServiceImpl implements DetalleOrdenService {

    private final DetalleOrdenRepository detalleOrdenRepository;
    private final OrdenRepository ordenRepository;
    private final ProductoRepository productoRepository;

    /**
     * Obtiene todos los detalles de una orden dada.
     */
    @Override
    public List<DetalleOrdenDTO> obtenerDetallesPorOrdenId(Integer ordenId) {
        List<DetalleOrden> detalles = detalleOrdenRepository.findByOrdenId(ordenId);
        return detalles.stream()
                .map(this::convertirADTO) // 🔥 Usamos el método para mayor claridad
                .collect(Collectors.toList());
    }

    /**
     * Guarda un detalle de orden en la base de datos.
     */
    @Override
    public DetalleOrdenDTO guardarDetalleOrden(DetalleOrdenDTO detalleOrdenDTO) {
        Orden orden = ordenRepository.findById(detalleOrdenDTO.getOrdenId())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        Producto producto = productoRepository.findById(detalleOrdenDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setOrden(orden);
        detalleOrden.setProducto(producto);
        detalleOrden.setCantidad(detalleOrdenDTO.getCantidad());
        detalleOrden.setPrecioUnitario(detalleOrdenDTO.getPrecioUnitario());

        detalleOrden = detalleOrdenRepository.save(detalleOrden);
        return convertirADTO(detalleOrden); // 🔥 Reutilizamos el método
    }

    /**
     * Metodo auxiliar para convertir una entidad `DetalleOrden` a un `DetalleOrdenDTO`.
     */
    private DetalleOrdenDTO convertirADTO(DetalleOrden detalleOrden) {
        return new DetalleOrdenDTO(
                detalleOrden.getId(),
                detalleOrden.getOrden().getId(),
                detalleOrden.getProducto().getId(),
                detalleOrden.getProducto().getNombre(),
                detalleOrden.getCantidad(),
                detalleOrden.getPrecioUnitario()
        );
    }
}
