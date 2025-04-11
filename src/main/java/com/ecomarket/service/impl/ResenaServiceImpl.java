// service/impl/ResenaServiceImpl.java
package com.ecomarket.service.impl;

import com.ecomarket.dto.resena.ResenaDTO;
import com.ecomarket.dto.resena.ResenaResponseDTO;
import com.ecomarket.mapper.ResenaMapper;
import com.ecomarket.model.Producto;
import com.ecomarket.model.Resena;
import com.ecomarket.model.Usuario;
import com.ecomarket.repository.ProductoRepository;
import com.ecomarket.repository.ResenaRepository;
import com.ecomarket.repository.UsuarioRepository;
import com.ecomarket.service.ResenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResenaServiceImpl implements ResenaService {

    private final ResenaRepository resenaRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ResenaMapper mapper;

    @Override
    public void crearResena(ResenaDTO dto) {
        Optional<Producto> producto = productoRepository.findById(dto.getProductoId());
        Optional<Usuario> usuario = usuarioRepository.findById(dto.getUsuarioId());

        if (producto.isEmpty() || usuario.isEmpty()) {
            throw new RuntimeException("Producto o Usuario no encontrado");
        }

        Resena resena = Resena.builder()
                .producto(producto.get())
                .usuario(usuario.get())
                .calificacion(dto.getCalificacion())
                .comentario(dto.getComentario())
                .build();

        resenaRepository.save(resena);
    }

    @Override
    public List<ResenaResponseDTO> obtenerResenasPorProducto(Integer productoId) {
        return resenaRepository.findByProductoId(productoId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
