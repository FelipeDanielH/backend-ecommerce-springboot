package com.ecomarket.service.impl;

import com.ecomarket.dto.OrdenDTO;
import com.ecomarket.model.Orden;
import com.ecomarket.model.Usuario;
import com.ecomarket.model.enums.EstadoOrden;
import com.ecomarket.repository.DetalleOrdenRepository;
import com.ecomarket.repository.OrdenRepository;
import com.ecomarket.repository.UsuarioRepository;
import com.ecomarket.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final UsuarioRepository usuarioRepository;

    private final DetalleOrdenRepository detalleOrdenRepository;

    @Transactional
    @Override
    public OrdenDTO crearOrden(OrdenDTO ordenDTO) {
        Usuario comprador = usuarioRepository.findById(ordenDTO.getCompradorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Orden orden = Orden.builder()
                .comprador(comprador)
                .total(ordenDTO.getTotal())
                .estado(EstadoOrden.PENDIENTE)
                .build();

        Orden ordenGuardada = ordenRepository.save(orden);
        return convertirAOrdenDTO(ordenGuardada);
    }

    @Override
    public OrdenDTO obtenerOrdenPorId(Integer id) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        return convertirAOrdenDTO(orden);
    }

    @Override
    public List<OrdenDTO> obtenerOrdenesPorUsuario(Integer compradorId) {
        List<Orden> ordenes = ordenRepository.findByCompradorId(compradorId);
        return ordenes.stream().map(this::convertirAOrdenDTO).collect(Collectors.toList());
    }

    private OrdenDTO convertirAOrdenDTO(Orden orden) {
        return OrdenDTO.builder()
                .id(orden.getId())
                .compradorId(orden.getComprador().getId())
                .total(orden.getTotal())
                .estado(orden.getEstado().name())
                .fechaOrden(orden.getFechaOrden())
                .build();
    }
}
