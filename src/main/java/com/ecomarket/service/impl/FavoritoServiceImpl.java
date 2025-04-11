package com.ecomarket.service.impl;

import com.ecomarket.dto.favorito.FavoritoDTO;
import com.ecomarket.dto.favorito.FavoritoDetalleDTO;
import com.ecomarket.model.Favorito;
import com.ecomarket.model.Producto;
import com.ecomarket.model.Usuario;
import com.ecomarket.repository.FavoritoRepository;
import com.ecomarket.repository.ProductoRepository;
import com.ecomarket.repository.UsuarioRepository;
import com.ecomarket.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritoServiceImpl implements FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    @Override
    public FavoritoDTO agregarFavorito(Integer usuarioId, Integer productoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (favoritoRepository.existsByUsuarioIdAndProductoId(usuarioId, productoId)) {
            throw new RuntimeException("El producto ya está en favoritos");
        }

        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setProducto(producto);

        Favorito savedFavorito = favoritoRepository.save(favorito);
        return new FavoritoDTO(savedFavorito.getId(), usuarioId, productoId, savedFavorito.getFechaAgregado().toString());
    }

    @Override
    public void eliminarFavorito(Integer usuarioId, Integer productoId) {
        if (!favoritoRepository.existsByUsuarioIdAndProductoId(usuarioId, productoId)) {
            throw new RuntimeException("El producto no está en favoritos");
        }
        favoritoRepository.deleteByUsuarioIdAndProductoId(usuarioId, productoId);
    }

    @Override
    public List<FavoritoDTO> obtenerFavoritos(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return favoritoRepository.findByUsuario(usuario)
                .stream()
                .map(fav -> new FavoritoDTO(fav.getId(), usuarioId, fav.getProducto().getId(), fav.getFechaAgregado().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public List<FavoritoDetalleDTO> obtenerFavoritosConDetalle(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return favoritoRepository.findByUsuario(usuario)
                .stream()
                .map(fav -> new FavoritoDetalleDTO(
                        fav.getProducto().getId(),
                        fav.getProducto().getNombre(),
                        fav.getProducto().getDescripcion(),
                        fav.getProducto().getPrecio(),
                        fav.getFechaAgregado().toString()
                ))
                .collect(Collectors.toList());
    }
}
